package com.gspb.avalon.workflow.domain.model;

/**
 * Enumeration of possible task statuses in the system.
 */
public enum TaskStatus {
    /**
     * Task is created.
     */
    CREATED,
    
    /**
     * Task is assigned.
     */
    ASSIGNED,
    
    /**
     * Task is in progress.
     */
    IN_PROGRESS,
    
    /**
     * Task is completed.
     */
    COMPLETED,
    
    /**
     * Task is cancelled.
     */
    CANCELLED,
    
    /**
     * Task is suspended.
     */
    SUSPENDED,
    
    /**
     * Task has an error.
     */
    ERROR
}
