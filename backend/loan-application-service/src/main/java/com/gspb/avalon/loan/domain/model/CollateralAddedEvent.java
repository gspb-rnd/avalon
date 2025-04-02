package com.gspb.avalon.loan.domain.model;

import com.gspb.avalon.shared.domain.DomainEvent;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Domain event representing a collateral addition to a loan application.
 */
@Getter
public class CollateralAddedEvent extends DomainEvent {
    
    private final UUID loanApplicationId;
    private final UUID collateralId;
    private final CollateralType collateralType;
    private final BigDecimal estimatedValue;
    
    /**
     * Creates a new collateral added event.
     *
     * @param loanApplicationId The loan application ID
     * @param collateralId The collateral ID
     * @param collateralType The collateral type
     * @param estimatedValue The estimated value
     */
    public CollateralAddedEvent(UUID loanApplicationId, UUID collateralId, CollateralType collateralType, BigDecimal estimatedValue) {
        super(UUID.randomUUID(), LocalDateTime.now());
        this.loanApplicationId = loanApplicationId;
        this.collateralId = collateralId;
        this.collateralType = collateralType;
        this.estimatedValue = estimatedValue;
    }
}
