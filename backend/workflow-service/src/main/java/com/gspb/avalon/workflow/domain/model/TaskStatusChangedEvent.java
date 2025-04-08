package com.gspb.avalon.workflow.domain.model;

import com.gspb.avalon.shared.domain.DomainEvent;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Domain event representing a task status change.
 */
@Getter
public class TaskStatusChangedEvent extends DomainEvent {
    
    private final UUID taskId;
    private final TaskStatus oldStatus;
    private final TaskStatus newStatus;
    private final String changedBy;
    
    /**
     * Creates a new task status changed event.
     *
     * @param taskId The task ID
     * @param oldStatus The old status
     * @param newStatus The new status
     * @param changedBy The user who changed the status
     */
    public TaskStatusChangedEvent(UUID taskId, TaskStatus oldStatus, TaskStatus newStatus, String changedBy) {
        super();
        this.taskId = taskId;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
        this.changedBy = changedBy;
    }
}
