package com.gspb.avalon.collateral.application.dto;

import com.gspb.avalon.collateral.domain.model.RiskLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Data Transfer Object for RiskFactor.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class RiskFactorDTO {
    private UUID id;
    private UUID valuationId;
    private String factorName;
    private String description;
    private RiskLevel impact;
    private LocalDateTime identifiedAt;
    
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
    
    public String getFactorName() {
        return factorName;
    }
    
    public void setFactorName(String factorName) {
        this.factorName = factorName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public RiskLevel getImpact() {
        return impact;
    }
    
    public void setImpact(RiskLevel impact) {
        this.impact = impact;
    }
    
    public LocalDateTime getIdentifiedAt() {
        return identifiedAt;
    }
    
    public void setIdentifiedAt(LocalDateTime identifiedAt) {
        this.identifiedAt = identifiedAt;
    }
    
    public static RiskFactorDTOBuilder builder() {
        return new RiskFactorDTOBuilder();
    }
}
