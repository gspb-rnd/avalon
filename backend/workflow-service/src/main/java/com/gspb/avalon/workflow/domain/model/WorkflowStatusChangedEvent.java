package com.gspb.avalon.workflow.domain.model;

import com.gspb.avalon.shared.domain.DomainEvent;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Domain event representing a workflow status change.
 */
@Getter
public class WorkflowStatusChangedEvent extends DomainEvent {
    
    private final UUID workflowId;
    private final WorkflowStatus oldStatus;
    private final WorkflowStatus newStatus;
    private final String changedBy;
    
    /**
     * Creates a new workflow status changed event.
     *
     * @param workflowId The workflow ID
     * @param oldStatus The old status
     * @param newStatus The new status
     * @param changedBy The user who changed the status
     */
    public WorkflowStatusChangedEvent(UUID workflowId, WorkflowStatus oldStatus, WorkflowStatus newStatus, String changedBy) {
        super();
        this.workflowId = workflowId;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
        this.changedBy = changedBy;
    }
}
