package com.gspb.avalon.collateral.application.dto;

import com.gspb.avalon.collateral.domain.model.RiskLevel;
import com.gspb.avalon.collateral.domain.model.ValuationMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Builder class for ValuationHistoryDTO.
 */
public class ValuationHistoryDTOBuilder {
    
    private UUID id;
    private UUID valuationId;
    private BigDecimal estimatedValue;
    private BigDecimal haircut;
    private BigDecimal adjustedValue;
    private RiskLevel riskLevel;
    private ValuationMethod valuationMethod;
    private LocalDateTime valuationDate;
    private String valuatedBy;
    
    public ValuationHistoryDTOBuilder id(UUID id) {
        this.id = id;
        return this;
    }
    
    public ValuationHistoryDTOBuilder valuationId(UUID valuationId) {
        this.valuationId = valuationId;
        return this;
    }
    
    public ValuationHistoryDTOBuilder estimatedValue(BigDecimal estimatedValue) {
        this.estimatedValue = estimatedValue;
        return this;
    }
    
    public ValuationHistoryDTOBuilder haircut(BigDecimal haircut) {
        this.haircut = haircut;
        return this;
    }
    
    public ValuationHistoryDTOBuilder adjustedValue(BigDecimal adjustedValue) {
        this.adjustedValue = adjustedValue;
        return this;
    }
    
    public ValuationHistoryDTOBuilder riskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
        return this;
    }
    
    public ValuationHistoryDTOBuilder valuationMethod(ValuationMethod valuationMethod) {
        this.valuationMethod = valuationMethod;
        return this;
    }
    
    public ValuationHistoryDTOBuilder valuationDate(LocalDateTime valuationDate) {
        this.valuationDate = valuationDate;
        return this;
    }
    
    public ValuationHistoryDTOBuilder valuatedBy(String valuatedBy) {
        this.valuatedBy = valuatedBy;
        return this;
    }
    
    public ValuationHistoryDTO build() {
        ValuationHistoryDTO dto = new ValuationHistoryDTO();
        dto.setId(id);
        dto.setValuationId(valuationId);
        dto.setEstimatedValue(estimatedValue);
        dto.setHaircut(haircut);
        dto.setAdjustedValue(adjustedValue);
        dto.setRiskLevel(riskLevel);
        dto.setValuationMethod(valuationMethod);
        dto.setValuationDate(valuationDate);
        dto.setValuatedBy(valuatedBy);
        return dto;
    }
}
