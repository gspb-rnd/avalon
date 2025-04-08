package com.gspb.avalon.loan.application.dto;

import java.util.UUID;

/**
 * Builder class for OverrideValidationRuleCommand.
 */
public class OverrideValidationRuleCommandBuilder {
    
    private UUID loanApplicationId;
    private String ruleId;
    private String reason;
    private String overriddenBy;
    
    public OverrideValidationRuleCommandBuilder loanApplicationId(UUID loanApplicationId) {
        this.loanApplicationId = loanApplicationId;
        return this;
    }
    
    public OverrideValidationRuleCommandBuilder ruleId(String ruleId) {
        this.ruleId = ruleId;
        return this;
    }
    
    public OverrideValidationRuleCommandBuilder reason(String reason) {
        this.reason = reason;
        return this;
    }
    
    public OverrideValidationRuleCommandBuilder overriddenBy(String overriddenBy) {
        this.overriddenBy = overriddenBy;
        return this;
    }
    
    public OverrideValidationRuleCommand build() {
        OverrideValidationRuleCommand command = new OverrideValidationRuleCommand();
        command.setLoanApplicationId(loanApplicationId);
        command.setRuleId(ruleId);
        command.setReason(reason);
        command.setOverriddenBy(overriddenBy);
        return command;
    }
}
