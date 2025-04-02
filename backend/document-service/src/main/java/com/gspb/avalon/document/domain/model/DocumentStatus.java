package com.gspb.avalon.document.domain.model;

/**
 * Enumeration of possible document statuses in the system.
 */
public enum DocumentStatus {
    /**
     * Document is in draft state.
     */
    DRAFT,
    
    /**
     * Document has been generated.
     */
    GENERATED,
    
    /**
     * Document is pending signature.
     */
    PENDING_SIGNATURE,
    
    /**
     * Document has been signed.
     */
    SIGNED,
    
    /**
     * Document has been rejected.
     */
    REJECTED,
    
    /**
     * Document has expired.
     */
    EXPIRED,
    
    /**
     * Document has been archived.
     */
    ARCHIVED
}
