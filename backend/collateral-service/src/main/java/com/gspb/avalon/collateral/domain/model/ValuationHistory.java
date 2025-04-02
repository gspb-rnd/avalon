package com.gspb.avalon.collateral.domain.model;

import com.gspb.avalon.shared.domain.ValueObject;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Value object representing a historical valuation record for a collateral asset.
 */
@Getter
public class ValuationHistory extends ValueObject {
    private final UUID id;
    private final UUID valuationId;
    private final BigDecimal estimatedValue;
    private final BigDecimal haircut;
    private final BigDecimal adjustedValue;
    private final RiskLevel riskLevel;
    private final ValuationMethod valuationMethod;
    private final LocalDateTime valuationDate;
    private final String valuatedBy;

    public ValuationHistory(UUID id, UUID valuationId, BigDecimal estimatedValue, BigDecimal haircut, 
                           BigDecimal adjustedValue, RiskLevel riskLevel, ValuationMethod valuationMethod, 
                           LocalDateTime valuationDate, String valuatedBy) {
        this.id = id;
        this.valuationId = valuationId;
        this.estimatedValue = estimatedValue;
        this.haircut = haircut;
        this.adjustedValue = adjustedValue;
        this.riskLevel = riskLevel;
        this.valuationMethod = valuationMethod;
        this.valuationDate = valuationDate;
        this.valuatedBy = valuatedBy;
    }
}
