package com.gspb.avalon.collateral.domain.model;

import com.gspb.avalon.shared.domain.ValueObject;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Value object representing a risk factor associated with a collateral valuation.
 */
@Getter
public class RiskFactor extends ValueObject {
    private final UUID id;
    private final UUID valuationId;
    private final String factorName;
    private final String description;
    private final RiskLevel impact;
    private final LocalDateTime identifiedAt;

    public RiskFactor(UUID id, UUID valuationId, String factorName, String description, 
                     RiskLevel impact, LocalDateTime identifiedAt) {
        this.id = id;
        this.valuationId = valuationId;
        this.factorName = factorName;
        this.description = description;
        this.impact = impact;
        this.identifiedAt = identifiedAt;
    }
    
    public UUID getId() {
        return id;
    }
    
    public UUID getValuationId() {
        return valuationId;
    }
    
    public String getFactorName() {
        return factorName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public RiskLevel getImpact() {
        return impact;
    }
    
    public LocalDateTime getIdentifiedAt() {
        return identifiedAt;
    }
}
