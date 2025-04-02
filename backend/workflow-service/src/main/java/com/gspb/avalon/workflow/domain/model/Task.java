package com.gspb.avalon.workflow.domain.model;

import com.gspb.avalon.shared.domain.Entity;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity representing a task in the system.
 */
@Getter
public class Task extends Entity<UUID> {
    
    private final UUID workflowId;
    private final String name;
    private final String description;
    private String assignee;
    private TaskStatus status;
    private TaskPriority priority;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime dueDate;
    private LocalDateTime completedAt;
    private String completionNotes;
    private String formKey;
    private String taskDefinitionKey;
    
    /**
     * Creates a new task.
     *
     * @param id The task ID
     * @param workflowId The workflow ID
     * @param name The task name
     * @param description The task description
     * @param assignee The assignee
     * @param priority The priority
     * @param dueDate The due date
     * @param formKey The form key
     * @param taskDefinitionKey The task definition key
     */
    public Task(UUID id, UUID workflowId, String name, String description, String assignee, TaskPriority priority,
               LocalDateTime dueDate, String formKey, String taskDefinitionKey) {
        super(id);
        this.workflowId = workflowId;
        this.name = name;
        this.description = description;
        this.assignee = assignee;
        this.priority = priority;
        this.dueDate = dueDate;
        this.formKey = formKey;
        this.taskDefinitionKey = taskDefinitionKey;
        this.status = TaskStatus.CREATED;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }
    
    /**
     * Assigns the task to a user.
     *
     * @param assignee The assignee
     * @param assignedBy The user who assigned the task
     */
    public void assign(String assignee, String assignedBy) {
        if (this.status == TaskStatus.COMPLETED || this.status == TaskStatus.CANCELLED) {
            throw new IllegalStateException("Cannot assign a completed or cancelled task");
        }
        
        TaskStatus oldStatus = this.status;
        this.assignee = assignee;
        this.status = TaskStatus.ASSIGNED;
        this.updatedAt = LocalDateTime.now();
        
        registerEvent(new TaskStatusChangedEvent(this.getId(), oldStatus, this.status, assignedBy));
    }
    
    /**
     * Starts the task.
     *
     * @param startedBy The user who started the task
     */
    public void start(String startedBy) {
        if (this.status != TaskStatus.ASSIGNED) {
            throw new IllegalStateException("Task must be assigned before it can be started");
        }
        
        TaskStatus oldStatus = this.status;
        this.status = TaskStatus.IN_PROGRESS;
        this.updatedAt = LocalDateTime.now();
        
        registerEvent(new TaskStatusChangedEvent(this.getId(), oldStatus, this.status, startedBy));
    }
    
    /**
     * Completes the task.
     *
     * @param completionNotes The completion notes
     * @param completedBy The user who completed the task
     */
    public void complete(String completionNotes, String completedBy) {
        if (this.status != TaskStatus.IN_PROGRESS && this.status != TaskStatus.ASSIGNED) {
            throw new IllegalStateException("Task must be in progress or assigned to be completed");
        }
        
        TaskStatus oldStatus = this.status;
        this.status = TaskStatus.COMPLETED;
        this.completionNotes = completionNotes;
        this.completedAt = LocalDateTime.now();
        this.updatedAt = this.completedAt;
        
        registerEvent(new TaskStatusChangedEvent(this.getId(), oldStatus, this.status, completedBy));
    }
    
    /**
     * Cancels the task.
     *
     * @param cancelledBy The user who cancelled the task
     */
    public void cancel(String cancelledBy) {
        if (this.status == TaskStatus.COMPLETED || this.status == TaskStatus.CANCELLED) {
            throw new IllegalStateException("Cannot cancel a completed or cancelled task");
        }
        
        TaskStatus oldStatus = this.status;
        this.status = TaskStatus.CANCELLED;
        this.updatedAt = LocalDateTime.now();
        
        registerEvent(new TaskStatusChangedEvent(this.getId(), oldStatus, this.status, cancelledBy));
    }
    
    /**
     * Suspends the task.
     *
     * @param suspendedBy The user who suspended the task
     */
    public void suspend(String suspendedBy) {
        if (this.status != TaskStatus.IN_PROGRESS && this.status != TaskStatus.ASSIGNED) {
            throw new IllegalStateException("Task must be in progress or assigned to be suspended");
        }
        
        TaskStatus oldStatus = this.status;
        this.status = TaskStatus.SUSPENDED;
        this.updatedAt = LocalDateTime.now();
        
        registerEvent(new TaskStatusChangedEvent(this.getId(), oldStatus, this.status, suspendedBy));
    }
    
    /**
     * Resumes the task.
     *
     * @param resumedBy The user who resumed the task
     */
    public void resume(String resumedBy) {
        if (this.status != TaskStatus.SUSPENDED) {
            throw new IllegalStateException("Task must be suspended to be resumed");
        }
        
        TaskStatus oldStatus = this.status;
        this.status = TaskStatus.IN_PROGRESS;
        this.updatedAt = LocalDateTime.now();
        
        registerEvent(new TaskStatusChangedEvent(this.getId(), oldStatus, this.status, resumedBy));
    }
    
    /**
     * Updates the priority of the task.
     *
     * @param priority The priority
     * @param updatedBy The user who updated the priority
     */
    public void updatePriority(TaskPriority priority, String updatedBy) {
        this.priority = priority;
        this.updatedAt = LocalDateTime.now();
    }
    
    /**
     * Updates the due date of the task.
     *
     * @param dueDate The due date
     * @param updatedBy The user who updated the due date
     */
    public void updateDueDate(LocalDateTime dueDate, String updatedBy) {
        this.dueDate = dueDate;
        this.updatedAt = LocalDateTime.now();
    }
    
    /**
     * Marks the task as having an error.
     *
     * @param errorBy The user who marked the task as having an error
     */
    public void markAsError(String errorBy) {
        TaskStatus oldStatus = this.status;
        this.status = TaskStatus.ERROR;
        this.updatedAt = LocalDateTime.now();
        
        registerEvent(new TaskStatusChangedEvent(this.getId(), oldStatus, this.status, errorBy));
    }
    
    /**
     * Registers a domain event.
     *
     * @param event The domain event
     */
    private void registerEvent(TaskStatusChangedEvent event) {
    }
}
