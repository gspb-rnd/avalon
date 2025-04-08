package com.gspb.avalon.collateral.infrastructure.persistence;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Builder class for ExternalValuationReferenceJpaEntity.
 */
public class ExternalValuationReferenceJpaEntityBuilder {
    
    private UUID id;
    private UUID valuationId;
    private String sourceSystem;
    private String referenceId;
    private String referenceUrl;
    private LocalDateTime createdAt;
    
    public ExternalValuationReferenceJpaEntityBuilder id(UUID id) {
        this.id = id;
        return this;
    }
    
    public ExternalValuationReferenceJpaEntityBuilder valuationId(UUID valuationId) {
        this.valuationId = valuationId;
        return this;
    }
    
    public ExternalValuationReferenceJpaEntityBuilder sourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
        return this;
    }
    
    public ExternalValuationReferenceJpaEntityBuilder referenceId(String referenceId) {
        this.referenceId = referenceId;
        return this;
    }
    
    public ExternalValuationReferenceJpaEntityBuilder referenceUrl(String referenceUrl) {
        this.referenceUrl = referenceUrl;
        return this;
    }
    
    public ExternalValuationReferenceJpaEntityBuilder createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }
    
    public ExternalValuationReferenceJpaEntity build() {
        ExternalValuationReferenceJpaEntity entity = new ExternalValuationReferenceJpaEntity();
        entity.setId(id);
        entity.setValuationId(valuationId);
        entity.setSourceSystem(sourceSystem);
        entity.setReferenceId(referenceId);
        entity.setReferenceUrl(referenceUrl);
        entity.setCreatedAt(createdAt);
        return entity;
    }
}
