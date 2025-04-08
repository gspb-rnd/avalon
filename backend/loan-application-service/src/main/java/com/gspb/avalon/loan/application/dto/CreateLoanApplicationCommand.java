package com.gspb.avalon.loan.application.dto;

import com.gspb.avalon.loan.domain.model.LoanType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Command for creating a loan application.
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CreateLoanApplicationCommand {
    
    @NotNull(message = "Client ID is required")
    private UUID clientId;
    
    @NotNull(message = "Advisor ID is required")
    private String advisorId;
    
    @NotNull(message = "Loan type is required")
    private LoanType loanType;
    
    private String purpose;
    
    public UUID getClientId() {
        return clientId;
    }
    
    public String getAdvisorId() {
        return advisorId;
    }
    
    public LoanType getLoanType() {
        return loanType;
    }
    
    public String getPurpose() {
        return purpose;
    }
    
    public static CreateLoanApplicationCommandBuilder builder() {
        return new CreateLoanApplicationCommandBuilder();
    }
}
