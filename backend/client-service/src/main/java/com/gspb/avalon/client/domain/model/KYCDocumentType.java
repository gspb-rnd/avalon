package com.gspb.avalon.client.domain.model;

/**
 * Enumeration of possible KYC document types.
 */
public enum KYCDocumentType {
    /**
     * Passport.
     */
    PASSPORT,
    
    /**
     * Driver's license.
     */
    DRIVERS_LICENSE,
    
    /**
     * National ID card.
     */
    NATIONAL_ID,
    
    /**
     * Utility bill for address verification.
     */
    UTILITY_BILL,
    
    /**
     * Bank statement for address verification.
     */
    BANK_STATEMENT,
    
    /**
     * Tax return.
     */
    TAX_RETURN,
    
    /**
     * Proof of income.
     */
    PROOF_OF_INCOME,
    
    /**
     * Source of wealth declaration.
     */
    SOURCE_OF_WEALTH,
    
    /**
     * Other document type.
     */
    OTHER
}
