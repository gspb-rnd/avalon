package com.gspb.avalon.loan.application.dto;

import com.gspb.avalon.loan.domain.model.ValidationSeverity;

/**
 * Builder class for ValidationRuleResultDTO.
 */
public class ValidationRuleResultDTOBuilder {
    
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
    
    public ValidationRuleResultDTOBuilder ruleId(String ruleId) {
        this.ruleId = ruleId;
        return this;
    }
    
    public ValidationRuleResultDTOBuilder ruleName(String ruleName) {
        this.ruleName = ruleName;
        return this;
    }
    
    public ValidationRuleResultDTOBuilder passed(boolean passed) {
        this.passed = passed;
        return this;
    }
    
    public ValidationRuleResultDTOBuilder message(String message) {
        this.message = message;
        return this;
    }
    
    public ValidationRuleResultDTOBuilder severity(ValidationSeverity severity) {
        this.severity = severity;
        return this;
    }
    
    public ValidationRuleResultDTOBuilder overridable(boolean overridable) {
        this.overridable = overridable;
        return this;
    }
    
    public ValidationRuleResultDTOBuilder overridden(boolean overridden) {
        this.overridden = overridden;
        return this;
    }
    
    public ValidationRuleResultDTOBuilder overrideReason(String overrideReason) {
        this.overrideReason = overrideReason;
        return this;
    }
    
    public ValidationRuleResultDTOBuilder overriddenBy(String overriddenBy) {
        this.overriddenBy = overriddenBy;
        return this;
    }
    
    public ValidationRuleResultDTOBuilder effectivelyPassed(boolean effectivelyPassed) {
        this.effectivelyPassed = effectivelyPassed;
        return this;
    }
    
    public ValidationRuleResultDTO build() {
        ValidationRuleResultDTO validationRuleResultDTO = new ValidationRuleResultDTO();
        validationRuleResultDTO.setRuleId(ruleId);
        validationRuleResultDTO.setRuleName(ruleName);
        validationRuleResultDTO.setPassed(passed);
        validationRuleResultDTO.setMessage(message);
        validationRuleResultDTO.setSeverity(severity);
        validationRuleResultDTO.setOverridable(overridable);
        validationRuleResultDTO.setOverridden(overridden);
        validationRuleResultDTO.setOverrideReason(overrideReason);
        validationRuleResultDTO.setOverriddenBy(overriddenBy);
        validationRuleResultDTO.setEffectivelyPassed(effectivelyPassed);
        return validationRuleResultDTO;
    }
}
