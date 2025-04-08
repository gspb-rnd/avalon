package com.gspb.avalon.collateral.application.service;

import com.gspb.avalon.collateral.domain.model.RiskLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Result object for valuation engine operations.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ValuationResult {
    private BigDecimal estimatedValue;
    private BigDecimal haircut;
    private RiskLevel riskLevel;
    private LocalDateTime expirationDate;
    private String notes;
    
    public BigDecimal getEstimatedValue() {
        return estimatedValue;
    }
    
    public void setEstimatedValue(BigDecimal estimatedValue) {
        this.estimatedValue = estimatedValue;
    }
    
    public BigDecimal getHaircut() {
        return haircut;
    }
    
    public void setHaircut(BigDecimal haircut) {
        this.haircut = haircut;
    }
    
    public RiskLevel getRiskLevel() {
        return riskLevel;
    }
    
    public void setRiskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
    }
    
    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }
    
    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public static ValuationResultBuilder builder() {
        return new ValuationResultBuilder();
    }
}
