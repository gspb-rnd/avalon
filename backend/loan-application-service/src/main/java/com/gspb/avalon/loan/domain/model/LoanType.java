package com.gspb.avalon.loan.domain.model;

/**
 * Enumeration of possible loan types in the system.
 */
public enum LoanType {
    /**
     * Term loan with fixed repayment schedule.
     */
    TERM_LOAN,
    
    /**
     * Line of credit with revolving credit facility.
     */
    LINE_OF_CREDIT,
    
    /**
     * Bridge loan for short-term financing.
     */
    BRIDGE_LOAN,
    
    /**
     * Interest-only loan with balloon payment.
     */
    INTEREST_ONLY,
    
    /**
     * Lombard loan secured by liquid assets.
     */
    LOMBARD_LOAN,
    
    /**
     * Mortgage loan secured by real estate.
     */
    MORTGAGE,
    
    /**
     * Specialized loan for art or collectibles.
     */
    ART_LOAN,
    
    /**
     * Specialized loan for luxury assets.
     */
    LUXURY_ASSET_LOAN
}
