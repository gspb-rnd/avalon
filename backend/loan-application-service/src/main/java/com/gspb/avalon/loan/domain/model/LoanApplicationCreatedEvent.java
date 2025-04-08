package com.gspb.avalon.loan.domain.model;

import com.gspb.avalon.shared.domain.DomainEvent;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Domain event representing a loan application creation.
 */
@Getter
public class LoanApplicationCreatedEvent extends DomainEvent {
    
    private final UUID loanApplicationId;
    private final UUID clientId;
    private final LoanType loanType;
    private final String advisorId;
    
    /**
     * Creates a new loan application created event.
     *
     * @param loanApplicationId The loan application ID
     * @param clientId The client ID
     * @param loanType The loan type
     * @param advisorId The advisor ID
     */
    public LoanApplicationCreatedEvent(UUID loanApplicationId, UUID clientId, LoanType loanType, String advisorId) {
        super();
        this.loanApplicationId = loanApplicationId;
        this.clientId = clientId;
        this.loanType = loanType;
        this.advisorId = advisorId;
    }
}
