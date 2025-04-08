package com.gspb.avalon.collateral.infrastructure.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * JPA entity for persisting ExternalValuationReference value objects.
 */
@Entity
@Table(name = "external_valuation_references")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ExternalValuationReferenceJpaEntity {
    
    @Id
    private UUID id;
    
    private UUID valuationId;
    
    private String sourceSystem;
    
    private String referenceId;
    
    private String referenceUrl;
    
    private LocalDateTime createdAt;
    
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
    
    public String getSourceSystem() {
        return sourceSystem;
    }
    
    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }
    
    public String getReferenceId() {
        return referenceId;
    }
    
    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }
    
    public String getReferenceUrl() {
        return referenceUrl;
    }
    
    public void setReferenceUrl(String referenceUrl) {
        this.referenceUrl = referenceUrl;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public static ExternalValuationReferenceJpaEntityBuilder builder() {
        return new ExternalValuationReferenceJpaEntityBuilder();
    }
}
