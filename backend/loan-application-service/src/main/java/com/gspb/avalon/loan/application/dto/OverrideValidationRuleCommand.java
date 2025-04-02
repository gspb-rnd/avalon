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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OverrideValidationRuleCommand {
    
    private UUID loanApplicationId;
    
    @NotBlank(message = "Rule ID is required")
    private String ruleId;
    
    @NotBlank(message = "Override reason is required")
    private String reason;
    
    @NotBlank(message = "Overridden by is required")
    private String overriddenBy;
}
