package com.gspb.avalon.workflow.application.service;

import com.gspb.avalon.workflow.application.dto.TaskDTO;
import com.gspb.avalon.workflow.application.dto.WorkflowDTO;
import com.gspb.avalon.workflow.domain.model.Task;
import com.gspb.avalon.workflow.domain.model.Workflow;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper between domain model and DTOs for workflows.
 */
@Component
public class WorkflowDTOMapper {
    
    /**
     * Maps a workflow domain model to a DTO.
     *
     * @param workflow The workflow domain model
     * @return The workflow DTO
     */
    public WorkflowDTO toDTO(Workflow workflow) {
        WorkflowDTO dto = WorkflowDTO.builder()
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
        
        dto.setTasks(mapTasks(workflow.getTasks()));
        
        return dto;
    }
    
    /**
     * Maps task domain models to DTOs.
     *
     * @param tasks The task domain models
     * @return The task DTOs
     */
    private List<TaskDTO> mapTasks(List<Task> tasks) {
        return tasks.stream()
                .map(this::mapTask)
                .collect(Collectors.toList());
    }
    
    /**
     * Maps a task domain model to a DTO.
     *
     * @param task The task domain model
     * @return The task DTO
     */
    private TaskDTO mapTask(Task task) {
        boolean isOverdue = task.getDueDate() != null && 
                LocalDateTime.now().isAfter(task.getDueDate()) && 
                task.getStatus() != com.gspb.avalon.workflow.domain.model.TaskStatus.COMPLETED && 
                task.getStatus() != com.gspb.avalon.workflow.domain.model.TaskStatus.CANCELLED;
        
        return TaskDTO.builder()
                .id(task.getId())
                .workflowId(task.getWorkflowId())
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
                .isOverdue(isOverdue)
                .build();
    }
}
