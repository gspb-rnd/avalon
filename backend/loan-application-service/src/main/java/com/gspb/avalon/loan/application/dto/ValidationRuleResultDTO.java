package com.gspb.avalon.loan.application.dto;

import com.gspb.avalon.loan.domain.model.ValidationSeverity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for ValidationRuleResult.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidationRuleResultDTO {
    
    private String ruleId;
    private String ruleName;
    private boolean passed;
    private String message;
    private ValidationSeverity severity;
    private boolean overridable;
    private boolean overridden;
    private String overrideReason;
    private String overriddenBy;
    private boolean effectivelyPassed;
}
