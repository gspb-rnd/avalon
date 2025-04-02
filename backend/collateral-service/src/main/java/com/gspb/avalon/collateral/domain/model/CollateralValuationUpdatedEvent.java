package com.gspb.avalon.collateral.domain.model;

import com.gspb.avalon.shared.domain.DomainEvent;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Domain event that is triggered when a collateral valuation is updated.
 */
@Getter
public class CollateralValuationUpdatedEvent extends DomainEvent {
    private final UUID valuationId;
    private final UUID collateralId;
    private final BigDecimal estimatedValue;
    private final BigDecimal adjustedValue;
    private final ValuationStatus status;

    public CollateralValuationUpdatedEvent(UUID valuationId, UUID collateralId, BigDecimal estimatedValue, 
                                          BigDecimal adjustedValue, ValuationStatus status) {
        super(UUID.randomUUID(), LocalDateTime.now());
        this.valuationId = valuationId;
        this.collateralId = collateralId;
        this.estimatedValue = estimatedValue;
        this.adjustedValue = adjustedValue;
        this.status = status;
    }
}
