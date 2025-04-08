package com.gspb.avalon.loan.domain.model;

import com.gspb.avalon.shared.domain.ValueObject;
import lombok.Getter;

/**
 * Value object representing the result of a validation rule.
 */
@Getter
public class ValidationRuleResult extends ValueObject {
    
    private final String ruleId;
    private final String ruleName;
    private final boolean passed;
    private final String message;
    private final ValidationSeverity severity;
    private final boolean overridable;
    private boolean overridden;
    private String overrideReason;
    private String overriddenBy;
    
    public String getOverrideReason() {
        return overrideReason;
    }
    
    public String getOverriddenBy() {
        return overriddenBy;
    }
    
    public boolean isOverridden() {
        return overridden;
    }
    
    /**
     * Checks if the validation rule can be overridden.
     *
     * @return True if the rule can be overridden, false otherwise
     */
    public boolean isOverridable() {
        return overridable;
    }
    
    /**
     * Gets the rule ID.
     *
     * @return The rule ID
     */
    public String getRuleId() {
        return ruleId;
    }
    
    /**
     * Gets the rule name.
     *
     * @return The rule name
     */
    public String getRuleName() {
        return ruleName;
    }
    
    /**
     * Checks if the rule passed.
     *
     * @return True if the rule passed, false otherwise
     */
    public boolean isPassed() {
        return passed;
    }
    
    /**
     * Gets the validation message.
     *
     * @return The validation message
     */
    public String getMessage() {
        return message;
    }
    
    /**
     * Gets the validation severity.
     *
     * @return The validation severity
     */
    public ValidationSeverity getSeverity() {
        return severity;
    }
    
    /**
     * Creates a new validation rule result.
     *
     * @param ruleId The rule ID
     * @param ruleName The rule name
     * @param passed Whether the rule passed
     * @param message The validation message
     * @param severity The validation severity
     * @param overridable Whether the rule can be overridden
     */
    public ValidationRuleResult(String ruleId, String ruleName, boolean passed, String message, 
                               ValidationSeverity severity, boolean overridable) {
        this.ruleId = ruleId;
        this.ruleName = ruleName;
        this.passed = passed;
        this.message = message;
        this.severity = severity;
        this.overridable = overridable;
        this.overridden = false;
        this.overrideReason = null;
        this.overriddenBy = null;
    }
    
    /**
     * Overrides the validation rule result.
     *
     * @param reason The override reason
     * @param overriddenBy The user who overrode the rule
     * @return The overridden validation rule result
     */
    public ValidationRuleResult override(String reason, String overriddenBy) {
        if (!this.overridable) {
            throw new IllegalStateException("This validation rule cannot be overridden");
        }
        
        if (this.passed) {
            throw new IllegalStateException("Cannot override a passed validation rule");
        }
        
        this.overridden = true;
        this.overrideReason = reason;
        this.overriddenBy = overriddenBy;
        
        return this;
    }
    
    /**
     * Checks if the validation rule is effectively passed (either passed or overridden).
     *
     * @return True if the rule is effectively passed, false otherwise
     */
    public boolean isEffectivelyPassed() {
        return this.passed || this.overridden;
    }
}
