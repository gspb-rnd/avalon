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
@Builder(toBuilder = true)
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
    
    public static ValidationRuleResultDTOBuilder builder() {
        return new ValidationRuleResultDTOBuilder();
    }
    
    public String getRuleId() {
        return ruleId;
    }
    
    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }
    
    public String getRuleName() {
        return ruleName;
    }
    
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }
    
    public boolean isPassed() {
        return passed;
    }
    
    public void setPassed(boolean passed) {
        this.passed = passed;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public ValidationSeverity getSeverity() {
        return severity;
    }
    
    public void setSeverity(ValidationSeverity severity) {
        this.severity = severity;
    }
    
    public boolean isOverridable() {
        return overridable;
    }
    
    public void setOverridable(boolean overridable) {
        this.overridable = overridable;
    }
    
    public boolean isOverridden() {
        return overridden;
    }
    
    public void setOverridden(boolean overridden) {
        this.overridden = overridden;
    }
    
    public String getOverrideReason() {
        return overrideReason;
    }
    
    public void setOverrideReason(String overrideReason) {
        this.overrideReason = overrideReason;
    }
    
    public String getOverriddenBy() {
        return overriddenBy;
    }
    
    public void setOverriddenBy(String overriddenBy) {
        this.overriddenBy = overriddenBy;
    }
    
    public boolean isEffectivelyPassed() {
        return effectivelyPassed;
    }
    
    public void setEffectivelyPassed(boolean effectivelyPassed) {
        this.effectivelyPassed = effectivelyPassed;
    }
}
