package com.gspb.avalon.loan.domain.model;

/**
 * Enumeration of possible loan application statuses in the system.
 */
public enum LoanStatus {
    /**
     * Loan application is in draft state.
     */
    DRAFT,
    
    /**
     * Loan application has been submitted for review.
     */
    SUBMITTED,
    
    /**
     * Loan application is under review.
     */
    UNDER_REVIEW,
    
    /**
     * Loan application has been approved.
     */
    APPROVED,
    
    /**
     * Loan application has been rejected.
     */
    REJECTED,
    
    /**
     * Loan application is pending additional documents.
     */
    PENDING_DOCUMENTS,
    
    /**
     * Loan application is pending signature.
     */
    PENDING_SIGNATURE,
    
    /**
     * Loan has been disbursed and is active.
     */
    ACTIVE,
    
    /**
     * Loan has been fully repaid and closed.
     */
    CLOSED,
    
    /**
     * Loan is in default.
     */
    DEFAULTED
}
