package com.gspb.avalon.loan.domain.model;

/**
 * Enumeration of possible payment frequencies in the system.
 */
public enum PaymentFrequency {
    /**
     * Monthly payments.
     */
    MONTHLY,
    
    /**
     * Quarterly payments.
     */
    QUARTERLY,
    
    /**
     * Semi-annual payments.
     */
    SEMI_ANNUAL,
    
    /**
     * Annual payments.
     */
    ANNUAL,
    
    /**
     * Bullet payment (single payment at maturity).
     */
    BULLET
}
