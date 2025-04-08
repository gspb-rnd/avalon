package com.gspb.avalon.collateral.domain.model;

import com.gspb.avalon.shared.domain.AggregateRoot;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Aggregate root representing a collateral valuation.
 */
@Getter
public class CollateralValuation extends AggregateRoot<UUID> {
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
    private List<ValuationHistory> valuationHistory;
    private List<ExternalValuationReference> externalReferences;
    private List<RiskFactor> riskFactors;

    public CollateralValuation(UUID id, UUID collateralId, UUID loanApplicationId, AssetClass assetClass, 
                              String assetDescription, ValuationMethod valuationMethod) {
        super(id);
        this.id = id;
        this.collateralId = collateralId;
        this.loanApplicationId = loanApplicationId;
        this.assetClass = assetClass;
        this.assetDescription = assetDescription;
        this.valuationMethod = valuationMethod;
        this.status = ValuationStatus.PENDING;
        this.valuationDate = LocalDateTime.now();
        this.valuationHistory = new ArrayList<>();
        this.externalReferences = new ArrayList<>();
        this.riskFactors = new ArrayList<>();
        
        registerEvent(new CollateralValuationCreatedEvent(id, collateralId, loanApplicationId, assetClass));
    }
    
    public void updateValuation(BigDecimal estimatedValue, BigDecimal haircut, RiskLevel riskLevel, 
                               String valuatedBy, LocalDateTime expirationDate) {
        this.estimatedValue = estimatedValue;
        this.haircut = haircut;
        this.adjustedValue = estimatedValue.multiply(BigDecimal.ONE.subtract(haircut.divide(new BigDecimal("100"))));
        this.riskLevel = riskLevel;
        this.valuatedBy = valuatedBy;
        this.expirationDate = expirationDate;
        this.status = ValuationStatus.COMPLETED;
        
        ValuationHistory historyEntry = new ValuationHistory(
            UUID.randomUUID(),
            this.id,
            this.estimatedValue,
            this.haircut,
            this.adjustedValue,
            this.riskLevel,
            this.valuationMethod,
            LocalDateTime.now(),
            this.valuatedBy
        );
        this.valuationHistory.add(historyEntry);
        
        registerEvent(new CollateralValuationUpdatedEvent(id, collateralId, estimatedValue, adjustedValue, status));
    }
    
    public void addExternalReference(String sourceSystem, String referenceId, String referenceUrl) {
        ExternalValuationReference reference = new ExternalValuationReference(
            UUID.randomUUID(),
            this.id,
            sourceSystem,
            referenceId,
            referenceUrl,
            LocalDateTime.now()
        );
        this.externalReferences.add(reference);
    }
    
    public void addRiskFactor(String factorName, String description, RiskLevel impact) {
        RiskFactor riskFactor = new RiskFactor(
            UUID.randomUUID(),
            this.id,
            factorName,
            description,
            impact,
            LocalDateTime.now()
        );
        this.riskFactors.add(riskFactor);
    }
    
    public void rejectValuation(String reason) {
        this.status = ValuationStatus.REJECTED;
        registerEvent(new CollateralValuationRejectedEvent(id, collateralId, reason));
    }
    
    public void requestManualReview(String reason) {
        this.status = ValuationStatus.REQUIRES_MANUAL_REVIEW;
        registerEvent(new CollateralValuationManualReviewRequestedEvent(id, collateralId, reason));
    }
    
    public boolean isExpired() {
        return expirationDate != null && LocalDateTime.now().isAfter(expirationDate);
    }
    
    @Override
    public UUID getId() {
        return id;
    }
    
    public UUID getCollateralId() {
        return collateralId;
    }
    
    public UUID getLoanApplicationId() {
        return loanApplicationId;
    }
    
    public AssetClass getAssetClass() {
        return assetClass;
    }
    
    public String getAssetDescription() {
        return assetDescription;
    }
    
    public ValuationMethod getValuationMethod() {
        return valuationMethod;
    }
    
    public BigDecimal getEstimatedValue() {
        return estimatedValue;
    }
    
    public BigDecimal getHaircut() {
        return haircut;
    }
    
    public BigDecimal getAdjustedValue() {
        return adjustedValue;
    }
    
    public RiskLevel getRiskLevel() {
        return riskLevel;
    }
    
    public ValuationStatus getStatus() {
        return status;
    }
    
    public LocalDateTime getValuationDate() {
        return valuationDate;
    }
    
    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }
    
    public String getValuatedBy() {
        return valuatedBy;
    }
    
    public List<ValuationHistory> getValuationHistory() {
        return valuationHistory;
    }
    
    public List<ExternalValuationReference> getExternalReferences() {
        return externalReferences;
    }
    
    public List<RiskFactor> getRiskFactors() {
        return riskFactors;
    }
}
