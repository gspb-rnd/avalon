package com.gspb.avalon.collateral.infrastructure.persistence;

import com.gspb.avalon.collateral.domain.model.AssetClass;
import com.gspb.avalon.collateral.domain.model.RiskLevel;
import com.gspb.avalon.collateral.domain.model.ValuationMethod;
import com.gspb.avalon.collateral.domain.model.ValuationStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Builder class for CollateralValuationJpaEntity.
 */
public class CollateralValuationJpaEntityBuilder {
    
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
    private List<ValuationHistoryJpaEntity> valuationHistory = new ArrayList<>();
    private List<ExternalValuationReferenceJpaEntity> externalReferences = new ArrayList<>();
    private List<RiskFactorJpaEntity> riskFactors = new ArrayList<>();
    
    public CollateralValuationJpaEntityBuilder id(UUID id) {
        this.id = id;
        return this;
    }
    
    public CollateralValuationJpaEntityBuilder collateralId(UUID collateralId) {
        this.collateralId = collateralId;
        return this;
    }
    
    public CollateralValuationJpaEntityBuilder loanApplicationId(UUID loanApplicationId) {
        this.loanApplicationId = loanApplicationId;
        return this;
    }
    
    public CollateralValuationJpaEntityBuilder assetClass(AssetClass assetClass) {
        this.assetClass = assetClass;
        return this;
    }
    
    public CollateralValuationJpaEntityBuilder assetDescription(String assetDescription) {
        this.assetDescription = assetDescription;
        return this;
    }
    
    public CollateralValuationJpaEntityBuilder valuationMethod(ValuationMethod valuationMethod) {
        this.valuationMethod = valuationMethod;
        return this;
    }
    
    public CollateralValuationJpaEntityBuilder estimatedValue(BigDecimal estimatedValue) {
        this.estimatedValue = estimatedValue;
        return this;
    }
    
    public CollateralValuationJpaEntityBuilder haircut(BigDecimal haircut) {
        this.haircut = haircut;
        return this;
    }
    
    public CollateralValuationJpaEntityBuilder adjustedValue(BigDecimal adjustedValue) {
        this.adjustedValue = adjustedValue;
        return this;
    }
    
    public CollateralValuationJpaEntityBuilder riskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
        return this;
    }
    
    public CollateralValuationJpaEntityBuilder status(ValuationStatus status) {
        this.status = status;
        return this;
    }
    
    public CollateralValuationJpaEntityBuilder valuationDate(LocalDateTime valuationDate) {
        this.valuationDate = valuationDate;
        return this;
    }
    
    public CollateralValuationJpaEntityBuilder expirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }
    
    public CollateralValuationJpaEntityBuilder valuatedBy(String valuatedBy) {
        this.valuatedBy = valuatedBy;
        return this;
    }
    
    public CollateralValuationJpaEntityBuilder valuationHistory(List<ValuationHistoryJpaEntity> valuationHistory) {
        this.valuationHistory = valuationHistory;
        return this;
    }
    
    public CollateralValuationJpaEntityBuilder externalReferences(List<ExternalValuationReferenceJpaEntity> externalReferences) {
        this.externalReferences = externalReferences;
        return this;
    }
    
    public CollateralValuationJpaEntityBuilder riskFactors(List<RiskFactorJpaEntity> riskFactors) {
        this.riskFactors = riskFactors;
        return this;
    }
    
    public CollateralValuationJpaEntity build() {
        CollateralValuationJpaEntity entity = new CollateralValuationJpaEntity();
        entity.setId(id);
        entity.setCollateralId(collateralId);
        entity.setLoanApplicationId(loanApplicationId);
        entity.setAssetClass(assetClass);
        entity.setAssetDescription(assetDescription);
        entity.setValuationMethod(valuationMethod);
        entity.setEstimatedValue(estimatedValue);
        entity.setHaircut(haircut);
        entity.setAdjustedValue(adjustedValue);
        entity.setRiskLevel(riskLevel);
        entity.setStatus(status);
        entity.setValuationDate(valuationDate);
        entity.setExpirationDate(expirationDate);
        entity.setValuatedBy(valuatedBy);
        entity.setValuationHistory(valuationHistory);
        entity.setExternalReferences(externalReferences);
        entity.setRiskFactors(riskFactors);
        return entity;
    }
}
