package com.gspb.avalon.workflow.infrastructure.persistence;

import com.gspb.avalon.workflow.domain.model.Task;
import com.gspb.avalon.workflow.domain.model.Workflow;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Mapper between domain model and JPA entities for workflows.
 */
@Component
public class WorkflowMapper {
    
    /**
     * Maps a workflow domain model to a JPA entity.
     *
     * @param workflow The workflow domain model
     * @return The workflow JPA entity
     */
    public WorkflowJpaEntity toJpaEntity(Workflow workflow) {
        WorkflowJpaEntity entity = WorkflowJpaEntity.builder()
                .id(workflow.getId())
                .type(workflow.getType())
                .businessObjectId(workflow.getBusinessObjectId())
                .name(workflow.getName())
                .description(workflow.getDescription())
                .createdBy(workflow.getCreatedBy())
                .createdAt(workflow.getCreatedAt())
                .updatedAt(workflow.getUpdatedAt())
                .status(workflow.getStatus())
                .processInstanceId(workflow.getProcessInstanceId())
                .processDefinitionKey(workflow.getProcessDefinitionKey())
                .build();
        
        entity.setTasks(mapTasks(workflow.getTasks(), entity));
        
        return entity;
    }
    
    /**
     * Maps a workflow JPA entity to a domain model.
     *
     * @param entity The workflow JPA entity
     * @return The workflow domain model
     */
    public Workflow toDomainModel(WorkflowJpaEntity entity) {
        Workflow workflow = new Workflow(
                entity.getId(),
                entity.getType(),
                entity.getBusinessObjectId(),
                entity.getName(),
                entity.getDescription(),
                entity.getCreatedBy(),
                entity.getProcessDefinitionKey()
        );
        
        try {
            java.lang.reflect.Field statusField = Workflow.class.getDeclaredField("status");
            statusField.setAccessible(true);
            statusField.set(workflow, entity.getStatus());
            
            java.lang.reflect.Field processInstanceIdField = Workflow.class.getDeclaredField("processInstanceId");
            processInstanceIdField.setAccessible(true);
            processInstanceIdField.set(workflow, entity.getProcessInstanceId());
            
            java.lang.reflect.Field createdAtField = Workflow.class.getDeclaredField("createdAt");
            createdAtField.setAccessible(true);
            createdAtField.set(workflow, entity.getCreatedAt());
            
            java.lang.reflect.Field updatedAtField = Workflow.class.getDeclaredField("updatedAt");
            updatedAtField.setAccessible(true);
            updatedAtField.set(workflow, entity.getUpdatedAt());
            
            java.lang.reflect.Field tasksField = Workflow.class.getDeclaredField("tasks");
            tasksField.setAccessible(true);
            List<Task> tasks = entity.getTasks().stream()
                    .map(this::mapTask)
                    .collect(Collectors.toList());
            ((List<Task>) tasksField.get(workflow)).addAll(tasks);
            
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Error mapping workflow entity to domain model", e);
        }
        
        return workflow;
    }
    
    /**
     * Maps task domain models to JPA entities.
     *
     * @param tasks The task domain models
     * @param workflowEntity The workflow JPA entity
     * @return The task JPA entities
     */
    private List<TaskJpaEntity> mapTasks(List<Task> tasks, WorkflowJpaEntity workflowEntity) {
        return tasks.stream()
                .map(task -> TaskJpaEntity.builder()
                        .id(task.getId())
                        .workflow(workflowEntity)
                        .name(task.getName())
                        .description(task.getDescription())
                        .assignee(task.getAssignee())
                        .status(task.getStatus())
                        .priority(task.getPriority())
                        .createdAt(task.getCreatedAt())
                        .updatedAt(task.getUpdatedAt())
                        .dueDate(task.getDueDate())
                        .completedAt(task.getCompletedAt())
                        .completionNotes(task.getCompletionNotes())
                        .formKey(task.getFormKey())
                        .taskDefinitionKey(task.getTaskDefinitionKey())
                        .build())
                .collect(Collectors.toList());
    }
    
    /**
     * Maps a task JPA entity to a domain model.
     *
     * @param entity The task JPA entity
     * @return The task domain model
     */
    private Task mapTask(TaskJpaEntity entity) {
        Task task = new Task(
                entity.getId(),
                entity.getWorkflow().getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getAssignee(),
                entity.getPriority(),
                entity.getDueDate(),
                entity.getFormKey(),
                entity.getTaskDefinitionKey()
        );
        
        try {
            java.lang.reflect.Field statusField = Task.class.getDeclaredField("status");
            statusField.setAccessible(true);
            statusField.set(task, entity.getStatus());
            
            java.lang.reflect.Field createdAtField = Task.class.getDeclaredField("createdAt");
            createdAtField.setAccessible(true);
            createdAtField.set(task, entity.getCreatedAt());
            
            java.lang.reflect.Field updatedAtField = Task.class.getDeclaredField("updatedAt");
            updatedAtField.setAccessible(true);
            updatedAtField.set(task, entity.getUpdatedAt());
            
            java.lang.reflect.Field completedAtField = Task.class.getDeclaredField("completedAt");
            completedAtField.setAccessible(true);
            completedAtField.set(task, entity.getCompletedAt());
            
            java.lang.reflect.Field completionNotesField = Task.class.getDeclaredField("completionNotes");
            completionNotesField.setAccessible(true);
            completionNotesField.set(task, entity.getCompletionNotes());
            
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Error mapping task entity to domain model", e);
        }
        
        return task;
    }
}
