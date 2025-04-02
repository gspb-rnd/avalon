package com.gspb.avalon.collateral.domain.model;

import com.gspb.avalon.shared.domain.DomainEvent;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Domain event that is triggered when a new collateral valuation is created.
 */
@Getter
public class CollateralValuationCreatedEvent extends DomainEvent {
    private final UUID valuationId;
    private final UUID collateralId;
    private final UUID loanApplicationId;
    private final AssetClass assetClass;

    public CollateralValuationCreatedEvent(UUID valuationId, UUID collateralId, UUID loanApplicationId, AssetClass assetClass) {
        super(UUID.randomUUID(), LocalDateTime.now());
        this.valuationId = valuationId;
        this.collateralId = collateralId;
        this.loanApplicationId = loanApplicationId;
        this.assetClass = assetClass;
    }
}
