package com.gspb.avalon.loan.application.dto;

import com.gspb.avalon.loan.domain.model.LoanType;

import java.util.UUID;

/**
 * Builder class for CreateLoanApplicationCommand.
 */
public class CreateLoanApplicationCommandBuilder {
    
    private UUID clientId;
    private String advisorId;
    private LoanType loanType;
    private String purpose;
    
    public CreateLoanApplicationCommandBuilder clientId(UUID clientId) {
        this.clientId = clientId;
        return this;
    }
    
    public CreateLoanApplicationCommandBuilder advisorId(String advisorId) {
        this.advisorId = advisorId;
        return this;
    }
    
    public CreateLoanApplicationCommandBuilder loanType(LoanType loanType) {
        this.loanType = loanType;
        return this;
    }
    
    public CreateLoanApplicationCommandBuilder purpose(String purpose) {
        this.purpose = purpose;
        return this;
    }
    
    public CreateLoanApplicationCommand build() {
        CreateLoanApplicationCommand command = new CreateLoanApplicationCommand();
        command.setClientId(clientId);
        command.setAdvisorId(advisorId);
        command.setLoanType(loanType);
        command.setPurpose(purpose);
        return command;
    }
}
