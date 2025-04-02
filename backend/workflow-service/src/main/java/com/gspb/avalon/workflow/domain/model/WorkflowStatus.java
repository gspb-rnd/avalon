package com.gspb.avalon.workflow.domain.model;

/**
 * Enumeration of possible workflow statuses in the system.
 */
public enum WorkflowStatus {
    /**
     * Workflow is in progress.
     */
    IN_PROGRESS,
    
    /**
     * Workflow is completed.
     */
    COMPLETED,
    
    /**
     * Workflow is cancelled.
     */
    CANCELLED,
    
    /**
     * Workflow is suspended.
     */
    SUSPENDED,
    
    /**
     * Workflow has an error.
     */
    ERROR
}
