package com.gspb.avalon.loan.domain.model;

import com.gspb.avalon.shared.domain.DomainEvent;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Domain event representing a loan application status change.
 */
@Getter
public class LoanApplicationStatusChangedEvent extends DomainEvent {
    
    private final UUID loanApplicationId;
    private final LoanStatus oldStatus;
    private final LoanStatus newStatus;
    private final String changedBy;
    
    /**
     * Creates a new loan application status changed event.
     *
     * @param loanApplicationId The loan application ID
     * @param oldStatus The old status
     * @param newStatus The new status
     * @param changedBy The user who changed the status
     */
    public LoanApplicationStatusChangedEvent(UUID loanApplicationId, LoanStatus oldStatus, LoanStatus newStatus, String changedBy) {
        super();
        this.loanApplicationId = loanApplicationId;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
        this.changedBy = changedBy;
    }
}
