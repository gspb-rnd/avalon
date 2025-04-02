package com.gspb.avalon.document.domain.model;

/**
 * Enumeration of possible signature statuses in the system.
 */
public enum SignatureStatus {
    /**
     * Signature is pending.
     */
    PENDING,
    
    /**
     * Signature has been completed.
     */
    COMPLETED,
    
    /**
     * Signature has been declined.
     */
    DECLINED,
    
    /**
     * Signature has expired.
     */
    EXPIRED,
    
    /**
     * Signature has been cancelled.
     */
    CANCELLED
}
