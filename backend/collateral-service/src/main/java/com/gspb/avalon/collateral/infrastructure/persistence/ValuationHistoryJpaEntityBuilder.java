package com.gspb.avalon.collateral.infrastructure.persistence;

import com.gspb.avalon.collateral.domain.model.RiskLevel;
import com.gspb.avalon.collateral.domain.model.ValuationMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Builder class for ValuationHistoryJpaEntity.
 */
public class ValuationHistoryJpaEntityBuilder {
    
    private UUID id;
    private UUID valuationId;
    private BigDecimal estimatedValue;
    private BigDecimal haircut;
    private BigDecimal adjustedValue;
    private RiskLevel riskLevel;
    private ValuationMethod valuationMethod;
    private LocalDateTime valuationDate;
    private String valuatedBy;
    
    public ValuationHistoryJpaEntityBuilder id(UUID id) {
        this.id = id;
        return this;
    }
    
    public ValuationHistoryJpaEntityBuilder valuationId(UUID valuationId) {
        this.valuationId = valuationId;
        return this;
    }
    
    public ValuationHistoryJpaEntityBuilder estimatedValue(BigDecimal estimatedValue) {
        this.estimatedValue = estimatedValue;
        return this;
    }
    
    public ValuationHistoryJpaEntityBuilder haircut(BigDecimal haircut) {
        this.haircut = haircut;
        return this;
    }
    
    public ValuationHistoryJpaEntityBuilder adjustedValue(BigDecimal adjustedValue) {
        this.adjustedValue = adjustedValue;
        return this;
    }
    
    public ValuationHistoryJpaEntityBuilder riskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
        return this;
    }
    
    public ValuationHistoryJpaEntityBuilder valuationMethod(ValuationMethod valuationMethod) {
        this.valuationMethod = valuationMethod;
        return this;
    }
    
    public ValuationHistoryJpaEntityBuilder valuationDate(LocalDateTime valuationDate) {
        this.valuationDate = valuationDate;
        return this;
    }
    
    public ValuationHistoryJpaEntityBuilder valuatedBy(String valuatedBy) {
        this.valuatedBy = valuatedBy;
        return this;
    }
    
    public ValuationHistoryJpaEntity build() {
        ValuationHistoryJpaEntity entity = new ValuationHistoryJpaEntity();
        entity.setId(id);
        entity.setValuationId(valuationId);
        entity.setEstimatedValue(estimatedValue);
        entity.setHaircut(haircut);
        entity.setAdjustedValue(adjustedValue);
        entity.setRiskLevel(riskLevel);
        entity.setValuationMethod(valuationMethod);
        entity.setValuationDate(valuationDate);
        entity.setValuatedBy(valuatedBy);
        return entity;
    }
}
