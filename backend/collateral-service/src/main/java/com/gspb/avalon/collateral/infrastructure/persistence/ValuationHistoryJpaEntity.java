package com.gspb.avalon.collateral.infrastructure.persistence;

import com.gspb.avalon.collateral.domain.model.RiskLevel;
import com.gspb.avalon.collateral.domain.model.ValuationMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * JPA entity for persisting ValuationHistory value objects.
 */
@Entity
@Table(name = "valuation_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ValuationHistoryJpaEntity {
    
    @Id
    private UUID id;
    
    private UUID valuationId;
    
    @Column(precision = 19, scale = 4)
    private BigDecimal estimatedValue;
    
    @Column(precision = 5, scale = 2)
    private BigDecimal haircut;
    
    @Column(precision = 19, scale = 4)
    private BigDecimal adjustedValue;
    
    @Enumerated(EnumType.STRING)
    private RiskLevel riskLevel;
    
    @Enumerated(EnumType.STRING)
    private ValuationMethod valuationMethod;
    
    private LocalDateTime valuationDate;
    
    private String valuatedBy;
    
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public UUID getValuationId() {
        return valuationId;
    }
    
    public void setValuationId(UUID valuationId) {
        this.valuationId = valuationId;
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
    
    public ValuationMethod getValuationMethod() {
        return valuationMethod;
    }
    
    public void setValuationMethod(ValuationMethod valuationMethod) {
        this.valuationMethod = valuationMethod;
    }
    
    public LocalDateTime getValuationDate() {
        return valuationDate;
    }
    
    public void setValuationDate(LocalDateTime valuationDate) {
        this.valuationDate = valuationDate;
    }
    
    public String getValuatedBy() {
        return valuatedBy;
    }
    
    public void setValuatedBy(String valuatedBy) {
        this.valuatedBy = valuatedBy;
    }
    
    public static ValuationHistoryJpaEntityBuilder builder() {
        return new ValuationHistoryJpaEntityBuilder();
    }
}
