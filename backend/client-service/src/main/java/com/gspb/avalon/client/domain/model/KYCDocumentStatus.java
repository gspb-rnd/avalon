package com.gspb.avalon.client.domain.model;

/**
 * Enumeration of possible KYC document statuses.
 */
public enum KYCDocumentStatus {
    /**
     * Document is pending verification.
     */
    PENDING,
    
    /**
     * Document is being verified.
     */
    IN_PROGRESS,
    
    /**
     * Document has been verified.
     */
    VERIFIED,
    
    /**
     * Document has been rejected.
     */
    REJECTED,
    
    /**
     * Document has expired.
     */
    EXPIRED
}
