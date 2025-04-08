package com.gspb.avalon.collateral.infrastructure.persistence;

import com.gspb.avalon.collateral.domain.model.AssetClass;
import com.gspb.avalon.collateral.domain.model.RiskLevel;
import com.gspb.avalon.collateral.domain.model.ValuationMethod;
import com.gspb.avalon.collateral.domain.model.ValuationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * JPA entity for persisting CollateralValuation aggregate.
 */
@Entity
@Table(name = "collateral_valuations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CollateralValuationJpaEntity {
    
    @Id
    private UUID id;
    
    private UUID collateralId;
    
    private UUID loanApplicationId;
    
    @Enumerated(EnumType.STRING)
    private AssetClass assetClass;
    
    @Column(length = 500)
    private String assetDescription;
    
    @Enumerated(EnumType.STRING)
    private ValuationMethod valuationMethod;
    
    @Column(precision = 19, scale = 4)
    private BigDecimal estimatedValue;
    
    @Column(precision = 5, scale = 2)
    private BigDecimal haircut;
    
    @Column(precision = 19, scale = 4)
    private BigDecimal adjustedValue;
    
    @Enumerated(EnumType.STRING)
    private RiskLevel riskLevel;
    
    @Enumerated(EnumType.STRING)
    private ValuationStatus status;
    
    private LocalDateTime valuationDate;
    
    private LocalDateTime expirationDate;
    
    private String valuatedBy;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "valuation_id")
    private List<ValuationHistoryJpaEntity> valuationHistory = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "valuation_id")
    private List<ExternalValuationReferenceJpaEntity> externalReferences = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "valuation_id")
    private List<RiskFactorJpaEntity> riskFactors = new ArrayList<>();
    
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
    
    public List<ValuationHistoryJpaEntity> getValuationHistory() {
        return valuationHistory;
    }
    
    public void setValuationHistory(List<ValuationHistoryJpaEntity> valuationHistory) {
        this.valuationHistory = valuationHistory;
    }
    
    public List<ExternalValuationReferenceJpaEntity> getExternalReferences() {
        return externalReferences;
    }
    
    public void setExternalReferences(List<ExternalValuationReferenceJpaEntity> externalReferences) {
        this.externalReferences = externalReferences;
    }
    
    public List<RiskFactorJpaEntity> getRiskFactors() {
        return riskFactors;
    }
    
    public void setRiskFactors(List<RiskFactorJpaEntity> riskFactors) {
        this.riskFactors = riskFactors;
    }
    
    public static CollateralValuationJpaEntityBuilder builder() {
        return new CollateralValuationJpaEntityBuilder();
    }
}
