package com.gspb.avalon.loan.domain.model;

/**
 * Enumeration of possible collateral types in the system.
 */
public enum CollateralType {
    /**
     * Investment portfolio (stocks, bonds, etc.).
     */
    INVESTMENT_PORTFOLIO,
    
    /**
     * Real estate property.
     */
    REAL_ESTATE,
    
    /**
     * Fine art.
     */
    FINE_ART,
    
    /**
     * Luxury vehicles.
     */
    LUXURY_VEHICLE,
    
    /**
     * Jewelry and precious metals.
     */
    JEWELRY,
    
    /**
     * Collectibles (rare coins, stamps, etc.).
     */
    COLLECTIBLES,
    
    /**
     * Business equity.
     */
    BUSINESS_EQUITY,
    
    /**
     * Intellectual property.
     */
    INTELLECTUAL_PROPERTY,
    
    /**
     * Cash or cash equivalents.
     */
    CASH_EQUIVALENT,
    
    /**
     * Other high-value assets.
     */
    OTHER
}
