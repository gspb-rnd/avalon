package com.gspb.avalon.loan.domain.model;

import com.gspb.avalon.shared.domain.Entity;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Entity representing a collateral in the system.
 */
@Getter
public class Collateral extends Entity<UUID> {
    
    private final UUID loanApplicationId;
    private final CollateralType type;
    private final String description;
    private BigDecimal estimatedValue;
    private BigDecimal appraiserValue;
    private BigDecimal loanToValueRatio;
    private LocalDate valuationDate;
    private String appraiserName;
    private String documentationUrl;
    private boolean verified;
    
    /**
     * Creates a new collateral.
     *
     * @param id The collateral ID
     * @param loanApplicationId The loan application ID
     * @param type The collateral type
     * @param description The collateral description
     * @param estimatedValue The estimated value
     */
    public Collateral(UUID id, UUID loanApplicationId, CollateralType type, String description, BigDecimal estimatedValue) {
        super(id);
        this.loanApplicationId = loanApplicationId;
        this.type = type;
        this.description = description;
        this.estimatedValue = estimatedValue;
        this.loanToValueRatio = BigDecimal.ZERO;
        this.verified = false;
    }
    
    /**
     * Updates the collateral valuation.
     *
     * @param appraiserValue The appraiser value
     * @param appraiserName The appraiser name
     * @param valuationDate The valuation date
     * @param loanToValueRatio The loan-to-value ratio
     */
    public void updateValuation(BigDecimal appraiserValue, String appraiserName, LocalDate valuationDate, BigDecimal loanToValueRatio) {
        this.appraiserValue = appraiserValue;
        this.appraiserName = appraiserName;
        this.valuationDate = valuationDate;
        this.loanToValueRatio = loanToValueRatio;
    }
    
    /**
     * Adds documentation to the collateral.
     *
     * @param documentationUrl The documentation URL
     */
    public void addDocumentation(String documentationUrl) {
        this.documentationUrl = documentationUrl;
    }
    
    /**
     * Verifies the collateral.
     */
    public void verify() {
        if (this.appraiserValue == null || this.valuationDate == null) {
            throw new IllegalStateException("Collateral must be valued before verification");
        }
        
        this.verified = true;
    }
    
    /**
     * Gets the current value of the collateral.
     *
     * @return The current value
     */
    public BigDecimal getCurrentValue() {
        return this.appraiserValue != null ? this.appraiserValue : this.estimatedValue;
    }
}
