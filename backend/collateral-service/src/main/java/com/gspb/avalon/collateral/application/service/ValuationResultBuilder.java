package com.gspb.avalon.collateral.application.service;

import com.gspb.avalon.collateral.domain.model.RiskLevel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Builder class for ValuationResult.
 */
public class ValuationResultBuilder {
    
    private BigDecimal estimatedValue;
    private BigDecimal haircut;
    private RiskLevel riskLevel;
    private LocalDateTime expirationDate;
    private String notes;
    
    public ValuationResultBuilder estimatedValue(BigDecimal estimatedValue) {
        this.estimatedValue = estimatedValue;
        return this;
    }
    
    public ValuationResultBuilder haircut(BigDecimal haircut) {
        this.haircut = haircut;
        return this;
    }
    
    public ValuationResultBuilder riskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
        return this;
    }
    
    public ValuationResultBuilder expirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }
    
    public ValuationResultBuilder notes(String notes) {
        this.notes = notes;
        return this;
    }
    
    public ValuationResult build() {
        ValuationResult result = new ValuationResult();
        result.setEstimatedValue(estimatedValue);
        result.setHaircut(haircut);
        result.setRiskLevel(riskLevel);
        result.setExpirationDate(expirationDate);
        result.setNotes(notes);
        return result;
    }
}
