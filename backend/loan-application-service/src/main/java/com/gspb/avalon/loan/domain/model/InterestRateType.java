package com.gspb.avalon.loan.domain.model;

/**
 * Enumeration of possible interest rate types in the system.
 */
public enum InterestRateType {
    /**
     * Fixed interest rate.
     */
    FIXED,
    
    /**
     * Variable interest rate.
     */
    VARIABLE,
    
    /**
     * Hybrid interest rate (fixed for a period, then variable).
     */
    HYBRID
}
