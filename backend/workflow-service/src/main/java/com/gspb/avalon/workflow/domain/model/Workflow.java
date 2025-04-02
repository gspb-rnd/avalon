package com.gspb.avalon.workflow.domain.model;

import com.gspb.avalon.shared.domain.AggregateRoot;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Aggregate root representing a workflow in the system.
 */
@Getter
public class Workflow extends AggregateRoot<UUID> {
    
    private final WorkflowType type;
    private final UUID businessObjectId;
    private final String name;
    private final String description;
    private final String createdBy;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private WorkflowStatus status;
    private String processInstanceId;
    private String processDefinitionKey;
    private final List<Task> tasks = new ArrayList<>();
    
    /**
     * Creates a new workflow.
     *
     * @param id The workflow ID
     * @param type The workflow type
     * @param businessObjectId The business object ID
     * @param name The workflow name
     * @param description The workflow description
     * @param createdBy The user who created the workflow
     * @param processDefinitionKey The process definition key
     */
    public Workflow(UUID id, WorkflowType type, UUID businessObjectId, String name, String description,
                   String createdBy, String processDefinitionKey) {
        super(id);
        this.type = type;
        this.businessObjectId = businessObjectId;
        this.name = name;
        this.description = description;
        this.createdBy = createdBy;
        this.processDefinitionKey = processDefinitionKey;
        this.status = WorkflowStatus.IN_PROGRESS;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
        
        registerEvent(new WorkflowCreatedEvent(id, type, businessObjectId, createdBy));
    }
    
    /**
     * Sets the process instance ID.
     *
     * @param processInstanceId The process instance ID
     */
    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
        this.updatedAt = LocalDateTime.now();
    }
    
    /**
     * Adds a task to the workflow.
     *
     * @param task The task to add
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        this.updatedAt = LocalDateTime.now();
    }
    
    /**
     * Completes the workflow.
     *
     * @param completedBy The user who completed the workflow
     */
    public void complete(String completedBy) {
        if (this.status != WorkflowStatus.IN_PROGRESS) {
            throw new IllegalStateException("Workflow must be in progress to be completed");
        }
        
        WorkflowStatus oldStatus = this.status;
        this.status = WorkflowStatus.COMPLETED;
        this.updatedAt = LocalDateTime.now();
        
        registerEvent(new WorkflowStatusChangedEvent(this.getId(), oldStatus, this.status, completedBy));
    }
    
    /**
     * Cancels the workflow.
     *
     * @param cancelledBy The user who cancelled the workflow
     */
    public void cancel(String cancelledBy) {
        if (this.status != WorkflowStatus.IN_PROGRESS && this.status != WorkflowStatus.SUSPENDED) {
            throw new IllegalStateException("Workflow must be in progress or suspended to be cancelled");
        }
        
        WorkflowStatus oldStatus = this.status;
        this.status = WorkflowStatus.CANCELLED;
        this.updatedAt = LocalDateTime.now();
        
        registerEvent(new WorkflowStatusChangedEvent(this.getId(), oldStatus, this.status, cancelledBy));
    }
    
    /**
     * Suspends the workflow.
     *
     * @param suspendedBy The user who suspended the workflow
     */
    public void suspend(String suspendedBy) {
        if (this.status != WorkflowStatus.IN_PROGRESS) {
            throw new IllegalStateException("Workflow must be in progress to be suspended");
        }
        
        WorkflowStatus oldStatus = this.status;
        this.status = WorkflowStatus.SUSPENDED;
        this.updatedAt = LocalDateTime.now();
        
        registerEvent(new WorkflowStatusChangedEvent(this.getId(), oldStatus, this.status, suspendedBy));
    }
    
    /**
     * Resumes the workflow.
     *
     * @param resumedBy The user who resumed the workflow
     */
    public void resume(String resumedBy) {
        if (this.status != WorkflowStatus.SUSPENDED) {
            throw new IllegalStateException("Workflow must be suspended to be resumed");
        }
        
        WorkflowStatus oldStatus = this.status;
        this.status = WorkflowStatus.IN_PROGRESS;
        this.updatedAt = LocalDateTime.now();
        
        registerEvent(new WorkflowStatusChangedEvent(this.getId(), oldStatus, this.status, resumedBy));
    }
    
    /**
     * Marks the workflow as having an error.
     *
     * @param errorBy The user who marked the workflow as having an error
     */
    public void markAsError(String errorBy) {
        WorkflowStatus oldStatus = this.status;
        this.status = WorkflowStatus.ERROR;
        this.updatedAt = LocalDateTime.now();
        
        registerEvent(new WorkflowStatusChangedEvent(this.getId(), oldStatus, this.status, errorBy));
    }
    
    /**
     * Gets the tasks in the workflow.
     *
     * @return An unmodifiable list of tasks
     */
    public List<Task> getTasks() {
        return Collections.unmodifiableList(tasks);
    }
    
    /**
     * Gets a task by ID.
     *
     * @param taskId The task ID
     * @return The task
     * @throws IllegalArgumentException if the task is not found
     */
    public Task getTask(UUID taskId) {
        return this.tasks.stream()
                .filter(task -> task.getId().equals(taskId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Task not found: " + taskId));
    }
}
