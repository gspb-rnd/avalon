package com.gspb.avalon.workflow.domain.model;

import com.gspb.avalon.shared.domain.DomainEvent;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Domain event representing a workflow creation.
 */
@Getter
public class WorkflowCreatedEvent extends DomainEvent {
    
    private final UUID workflowId;
    private final WorkflowType workflowType;
    private final UUID businessObjectId;
    private final String createdBy;
    
    /**
     * Creates a new workflow created event.
     *
     * @param workflowId The workflow ID
     * @param workflowType The workflow type
     * @param businessObjectId The business object ID
     * @param createdBy The user who created the workflow
     */
    public WorkflowCreatedEvent(UUID workflowId, WorkflowType workflowType, UUID businessObjectId, String createdBy) {
        super();
        this.workflowId = workflowId;
        this.workflowType = workflowType;
        this.businessObjectId = businessObjectId;
        this.createdBy = createdBy;
    }
}
