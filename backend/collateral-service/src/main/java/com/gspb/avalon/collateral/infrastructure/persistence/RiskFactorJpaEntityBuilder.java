package com.gspb.avalon.collateral.infrastructure.persistence;

import com.gspb.avalon.collateral.domain.model.RiskLevel;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Builder class for RiskFactorJpaEntity.
 */
public class RiskFactorJpaEntityBuilder {
    
    private UUID id;
    private UUID valuationId;
    private String factorName;
    private String description;
    private RiskLevel impact;
    private LocalDateTime identifiedAt;
    
    public RiskFactorJpaEntityBuilder id(UUID id) {
        this.id = id;
        return this;
    }
    
    public RiskFactorJpaEntityBuilder valuationId(UUID valuationId) {
        this.valuationId = valuationId;
        return this;
    }
    
    public RiskFactorJpaEntityBuilder factorName(String factorName) {
        this.factorName = factorName;
        return this;
    }
    
    public RiskFactorJpaEntityBuilder description(String description) {
        this.description = description;
        return this;
    }
    
    public RiskFactorJpaEntityBuilder impact(RiskLevel impact) {
        this.impact = impact;
        return this;
    }
    
    public RiskFactorJpaEntityBuilder identifiedAt(LocalDateTime identifiedAt) {
        this.identifiedAt = identifiedAt;
        return this;
    }
    
    public RiskFactorJpaEntity build() {
        RiskFactorJpaEntity entity = new RiskFactorJpaEntity();
        entity.setId(id);
        entity.setValuationId(valuationId);
        entity.setFactorName(factorName);
        entity.setDescription(description);
        entity.setImpact(impact);
        entity.setIdentifiedAt(identifiedAt);
        return entity;
    }
}
