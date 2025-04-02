package com.gspb.avalon.workflow.domain.model;

/**
 * Enumeration of possible workflow types in the system.
 */
public enum WorkflowType {
    /**
     * Loan application workflow.
     */
    LOAN_APPLICATION,
    
    /**
     * KYC verification workflow.
     */
    KYC_VERIFICATION,
    
    /**
     * Collateral valuation workflow.
     */
    COLLATERAL_VALUATION,
    
    /**
     * Document generation workflow.
     */
    DOCUMENT_GENERATION,
    
    /**
     * Document signature workflow.
     */
    DOCUMENT_SIGNATURE,
    
    /**
     * Loan disbursement workflow.
     */
    LOAN_DISBURSEMENT,
    
    /**
     * Loan closure workflow.
     */
    LOAN_CLOSURE
}
