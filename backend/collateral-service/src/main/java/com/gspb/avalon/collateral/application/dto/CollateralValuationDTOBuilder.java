package com.gspb.avalon.collateral.application.dto;

import com.gspb.avalon.collateral.domain.model.AssetClass;
import com.gspb.avalon.collateral.domain.model.RiskLevel;
import com.gspb.avalon.collateral.domain.model.ValuationMethod;
import com.gspb.avalon.collateral.domain.model.ValuationStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Builder class for CollateralValuationDTO.
 */
public class CollateralValuationDTOBuilder {
    
    private UUID id;
    private UUID collateralId;
    private UUID loanApplicationId;
    private AssetClass assetClass;
    private String assetDescription;
    private ValuationMethod valuationMethod;
    private BigDecimal estimatedValue;
    private BigDecimal haircut;
    private BigDecimal adjustedValue;
    private RiskLevel riskLevel;
    private ValuationStatus status;
    private LocalDateTime valuationDate;
    private LocalDateTime expirationDate;
    private String valuatedBy;
    private List<ValuationHistoryDTO> valuationHistory;
    private List<ExternalValuationReferenceDTO> externalReferences;
    private List<RiskFactorDTO> riskFactors;
    
    public CollateralValuationDTOBuilder id(UUID id) {
        this.id = id;
        return this;
    }
    
    public CollateralValuationDTOBuilder collateralId(UUID collateralId) {
        this.collateralId = collateralId;
        return this;
    }
    
    public CollateralValuationDTOBuilder loanApplicationId(UUID loanApplicationId) {
        this.loanApplicationId = loanApplicationId;
        return this;
    }
    
    public CollateralValuationDTOBuilder assetClass(AssetClass assetClass) {
        this.assetClass = assetClass;
        return this;
    }
    
    public CollateralValuationDTOBuilder assetDescription(String assetDescription) {
        this.assetDescription = assetDescription;
        return this;
    }
    
    public CollateralValuationDTOBuilder valuationMethod(ValuationMethod valuationMethod) {
        this.valuationMethod = valuationMethod;
        return this;
    }
    
    public CollateralValuationDTOBuilder estimatedValue(BigDecimal estimatedValue) {
        this.estimatedValue = estimatedValue;
        return this;
    }
    
    public CollateralValuationDTOBuilder haircut(BigDecimal haircut) {
        this.haircut = haircut;
        return this;
    }
    
    public CollateralValuationDTOBuilder adjustedValue(BigDecimal adjustedValue) {
        this.adjustedValue = adjustedValue;
        return this;
    }
    
    public CollateralValuationDTOBuilder riskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
        return this;
    }
    
    public CollateralValuationDTOBuilder status(ValuationStatus status) {
        this.status = status;
        return this;
    }
    
    public CollateralValuationDTOBuilder valuationDate(LocalDateTime valuationDate) {
        this.valuationDate = valuationDate;
        return this;
    }
    
    public CollateralValuationDTOBuilder expirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }
    
    public CollateralValuationDTOBuilder valuatedBy(String valuatedBy) {
        this.valuatedBy = valuatedBy;
        return this;
    }
    
    public CollateralValuationDTOBuilder valuationHistory(List<ValuationHistoryDTO> valuationHistory) {
        this.valuationHistory = valuationHistory;
        return this;
    }
    
    public CollateralValuationDTOBuilder externalReferences(List<ExternalValuationReferenceDTO> externalReferences) {
        this.externalReferences = externalReferences;
        return this;
    }
    
    public CollateralValuationDTOBuilder riskFactors(List<RiskFactorDTO> riskFactors) {
        this.riskFactors = riskFactors;
        return this;
    }
    
    public CollateralValuationDTO build() {
        CollateralValuationDTO dto = new CollateralValuationDTO();
        dto.setId(id);
        dto.setCollateralId(collateralId);
        dto.setLoanApplicationId(loanApplicationId);
        dto.setAssetClass(assetClass);
        dto.setAssetDescription(assetDescription);
        dto.setValuationMethod(valuationMethod);
        dto.setEstimatedValue(estimatedValue);
        dto.setHaircut(haircut);
        dto.setAdjustedValue(adjustedValue);
        dto.setRiskLevel(riskLevel);
        dto.setStatus(status);
        dto.setValuationDate(valuationDate);
        dto.setExpirationDate(expirationDate);
        dto.setValuatedBy(valuatedBy);
        dto.setValuationHistory(valuationHistory);
        dto.setExternalReferences(externalReferences);
        dto.setRiskFactors(riskFactors);
        return dto;
    }
}
