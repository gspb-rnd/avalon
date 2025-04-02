package com.gspb.avalon.client.domain.model;

/**
 * Enumeration of possible client statuses in the system.
 */
public enum ClientStatus {
    /**
     * Client is pending KYC verification.
     */
    PENDING_KYC,
    
    /**
     * Client's KYC verification is in progress.
     */
    KYC_IN_PROGRESS,
    
    /**
     * Client's KYC verification has been completed successfully.
     */
    KYC_COMPLETED,
    
    /**
     * Client's KYC verification has failed.
     */
    KYC_FAILED,
    
    /**
     * Client is active and can apply for loans.
     */
    ACTIVE,
    
    /**
     * Client is inactive and cannot apply for loans.
     */
    INACTIVE,
    
    /**
     * Client has been blocked due to suspicious activity.
     */
    BLOCKED
}
