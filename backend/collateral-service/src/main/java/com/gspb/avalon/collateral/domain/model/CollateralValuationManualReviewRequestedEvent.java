package com.gspb.avalon.collateral.domain.model;

import com.gspb.avalon.shared.domain.DomainEvent;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Domain event that is triggered when a collateral valuation requires manual review.
 */
@Getter
public class CollateralValuationManualReviewRequestedEvent extends DomainEvent {
    private final UUID valuationId;
    private final UUID collateralId;
    private final String reason;

    public CollateralValuationManualReviewRequestedEvent(UUID valuationId, UUID collateralId, String reason) {
        super();
        this.valuationId = valuationId;
        this.collateralId = collateralId;
        this.reason = reason;
    }
}
