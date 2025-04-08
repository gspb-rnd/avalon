package com.gspb.avalon.collateral.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Builder class for ExternalValuationReferenceDTO.
 */
public class ExternalValuationReferenceDTOBuilder {
    
    private UUID id;
    private UUID valuationId;
    private String sourceSystem;
    private String referenceId;
    private String referenceUrl;
    private LocalDateTime createdAt;
    
    public ExternalValuationReferenceDTOBuilder id(UUID id) {
        this.id = id;
        return this;
    }
    
    public ExternalValuationReferenceDTOBuilder valuationId(UUID valuationId) {
        this.valuationId = valuationId;
        return this;
    }
    
    public ExternalValuationReferenceDTOBuilder sourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
        return this;
    }
    
    public ExternalValuationReferenceDTOBuilder referenceId(String referenceId) {
        this.referenceId = referenceId;
        return this;
    }
    
    public ExternalValuationReferenceDTOBuilder referenceUrl(String referenceUrl) {
        this.referenceUrl = referenceUrl;
        return this;
    }
    
    public ExternalValuationReferenceDTOBuilder createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }
    
    public ExternalValuationReferenceDTO build() {
        ExternalValuationReferenceDTO dto = new ExternalValuationReferenceDTO();
        dto.setId(id);
        dto.setValuationId(valuationId);
        dto.setSourceSystem(sourceSystem);
        dto.setReferenceId(referenceId);
        dto.setReferenceUrl(referenceUrl);
        dto.setCreatedAt(createdAt);
        return dto;
    }
}
