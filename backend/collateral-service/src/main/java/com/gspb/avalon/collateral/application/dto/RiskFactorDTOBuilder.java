package com.gspb.avalon.collateral.application.dto;

import com.gspb.avalon.collateral.domain.model.RiskLevel;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Builder class for RiskFactorDTO.
 */
public class RiskFactorDTOBuilder {
    
    private UUID id;
    private UUID valuationId;
    private String factorName;
    private String description;
    private RiskLevel impact;
    private LocalDateTime identifiedAt;
    
    public RiskFactorDTOBuilder id(UUID id) {
        this.id = id;
        return this;
    }
    
    public RiskFactorDTOBuilder valuationId(UUID valuationId) {
        this.valuationId = valuationId;
        return this;
    }
    
    public RiskFactorDTOBuilder factorName(String factorName) {
        this.factorName = factorName;
        return this;
    }
    
    public RiskFactorDTOBuilder description(String description) {
        this.description = description;
        return this;
    }
    
    public RiskFactorDTOBuilder impact(RiskLevel impact) {
        this.impact = impact;
        return this;
    }
    
    public RiskFactorDTOBuilder identifiedAt(LocalDateTime identifiedAt) {
        this.identifiedAt = identifiedAt;
        return this;
    }
    
    public RiskFactorDTO build() {
        RiskFactorDTO dto = new RiskFactorDTO();
        dto.setId(id);
        dto.setValuationId(valuationId);
        dto.setFactorName(factorName);
        dto.setDescription(description);
        dto.setImpact(impact);
        dto.setIdentifiedAt(identifiedAt);
        return dto;
    }
}
