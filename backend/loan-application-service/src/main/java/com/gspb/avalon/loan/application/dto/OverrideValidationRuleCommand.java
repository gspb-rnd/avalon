package com.gspb.avalon.loan.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Command for overriding a validation rule.
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OverrideValidationRuleCommand {
    
    public static OverrideValidationRuleCommandBuilder builder() {
        return new OverrideValidationRuleCommandBuilder();
    }
    
    public void setLoanApplicationId(UUID loanApplicationId) {
        this.loanApplicationId = loanApplicationId;
    }
    
    public UUID getLoanApplicationId() {
        return loanApplicationId;
    }
    
    public String getRuleId() {
        return ruleId;
    }
    
    public String getReason() {
        return reason;
    }
    
    public String getOverriddenBy() {
        return overriddenBy;
    }
    
    private UUID loanApplicationId;
    
    @NotBlank(message = "Rule ID is required")
    private String ruleId;
    
    @NotBlank(message = "Override reason is required")
    private String reason;
    
    @NotBlank(message = "Overridden by is required")
    private String overriddenBy;
}
