package com.gspb.avalon.collateral.application.dto;

import com.gspb.avalon.collateral.domain.model.AssetClass;
import com.gspb.avalon.collateral.domain.model.RiskLevel;
import com.gspb.avalon.collateral.domain.model.ValuationMethod;
import com.gspb.avalon.collateral.domain.model.ValuationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Data Transfer Object for CollateralValuation.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CollateralValuationDTO {
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
    
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public UUID getCollateralId() {
        return collateralId;
    }
    
    public void setCollateralId(UUID collateralId) {
        this.collateralId = collateralId;
    }
    
    public UUID getLoanApplicationId() {
        return loanApplicationId;
    }
    
    public void setLoanApplicationId(UUID loanApplicationId) {
        this.loanApplicationId = loanApplicationId;
    }
    
    public AssetClass getAssetClass() {
        return assetClass;
    }
    
    public void setAssetClass(AssetClass assetClass) {
        this.assetClass = assetClass;
    }
    
    public String getAssetDescription() {
        return assetDescription;
    }
    
    public void setAssetDescription(String assetDescription) {
        this.assetDescription = assetDescription;
    }
    
    public ValuationMethod getValuationMethod() {
        return valuationMethod;
    }
    
    public void setValuationMethod(ValuationMethod valuationMethod) {
        this.valuationMethod = valuationMethod;
    }
    
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
    
    public BigDecimal getAdjustedValue() {
        return adjustedValue;
    }
    
    public void setAdjustedValue(BigDecimal adjustedValue) {
        this.adjustedValue = adjustedValue;
    }
    
    public RiskLevel getRiskLevel() {
        return riskLevel;
    }
    
    public void setRiskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
    }
    
    public ValuationStatus getStatus() {
        return status;
    }
    
    public void setStatus(ValuationStatus status) {
        this.status = status;
    }
    
    public LocalDateTime getValuationDate() {
        return valuationDate;
    }
    
    public void setValuationDate(LocalDateTime valuationDate) {
        this.valuationDate = valuationDate;
    }
    
    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }
    
    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }
    
    public String getValuatedBy() {
        return valuatedBy;
    }
    
    public void setValuatedBy(String valuatedBy) {
        this.valuatedBy = valuatedBy;
    }
    
    public List<ValuationHistoryDTO> getValuationHistory() {
        return valuationHistory;
    }
    
    public void setValuationHistory(List<ValuationHistoryDTO> valuationHistory) {
        this.valuationHistory = valuationHistory;
    }
    
    public List<ExternalValuationReferenceDTO> getExternalReferences() {
        return externalReferences;
    }
    
    public void setExternalReferences(List<ExternalValuationReferenceDTO> externalReferences) {
        this.externalReferences = externalReferences;
    }
    
    public List<RiskFactorDTO> getRiskFactors() {
        return riskFactors;
    }
    
    public void setRiskFactors(List<RiskFactorDTO> riskFactors) {
        this.riskFactors = riskFactors;
    }
    
    public static CollateralValuationDTOBuilder builder() {
        return new CollateralValuationDTOBuilder();
    }
}
