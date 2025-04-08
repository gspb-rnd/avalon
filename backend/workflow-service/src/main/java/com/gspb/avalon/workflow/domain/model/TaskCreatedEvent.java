package com.gspb.avalon.workflow.domain.model;

import com.gspb.avalon.shared.domain.DomainEvent;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Domain event representing a task creation.
 */
@Getter
public class TaskCreatedEvent extends DomainEvent {
    
    private final UUID taskId;
    private final UUID workflowId;
    private final String taskName;
    private final String assignee;
    private final TaskPriority priority;
    private final String createdBy;
    
    /**
     * Creates a new task created event.
     *
     * @param taskId The task ID
     * @param workflowId The workflow ID
     * @param taskName The task name
     * @param assignee The assignee
     * @param priority The priority
     * @param createdBy The user who created the task
     */
    public TaskCreatedEvent(UUID taskId, UUID workflowId, String taskName, String assignee, TaskPriority priority, String createdBy) {
        super();
        this.taskId = taskId;
        this.workflowId = workflowId;
        this.taskName = taskName;
        this.assignee = assignee;
        this.priority = priority;
        this.createdBy = createdBy;
    }
}
