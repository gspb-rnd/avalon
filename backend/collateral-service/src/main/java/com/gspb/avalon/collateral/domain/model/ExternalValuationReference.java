package com.gspb.avalon.collateral.domain.model;

import com.gspb.avalon.shared.domain.ValueObject;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Value object representing a reference to an external valuation source.
 */
@Getter
public class ExternalValuationReference extends ValueObject {
    private final UUID id;
    private final UUID valuationId;
    private final String sourceSystem;
    private final String referenceId;
    private final String referenceUrl;
    private final LocalDateTime createdAt;

    public ExternalValuationReference(UUID id, UUID valuationId, String sourceSystem, String referenceId, 
                                     String referenceUrl, LocalDateTime createdAt) {
        this.id = id;
        this.valuationId = valuationId;
        this.sourceSystem = sourceSystem;
        this.referenceId = referenceId;
        this.referenceUrl = referenceUrl;
        this.createdAt = createdAt;
    }
}
