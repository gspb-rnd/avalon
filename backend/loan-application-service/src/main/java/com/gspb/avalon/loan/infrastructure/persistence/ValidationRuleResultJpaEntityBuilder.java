package com.gspb.avalon.loan.infrastructure.persistence;

import com.gspb.avalon.loan.domain.model.ValidationSeverity;

import java.util.UUID;

/**
 * Builder class for ValidationRuleResultJpaEntity.
 */
public class ValidationRuleResultJpaEntityBuilder {
    
    private UUID id;
    private LoanApplicationJpaEntity loanApplication;
    private String ruleId;
    private String ruleName;
    private Boolean passed;
    private String message;
    private ValidationSeverity severity;
    private Boolean overridable;
    private Boolean overridden;
    private String overrideReason;
    private String overriddenBy;
    
    public ValidationRuleResultJpaEntityBuilder id(UUID id) {
        this.id = id;
        return this;
    }
    
    public ValidationRuleResultJpaEntityBuilder loanApplication(LoanApplicationJpaEntity loanApplication) {
        this.loanApplication = loanApplication;
        return this;
    }
    
    public ValidationRuleResultJpaEntityBuilder ruleId(String ruleId) {
        this.ruleId = ruleId;
        return this;
    }
    
    public ValidationRuleResultJpaEntityBuilder ruleName(String ruleName) {
        this.ruleName = ruleName;
        return this;
    }
    
    public ValidationRuleResultJpaEntityBuilder passed(Boolean passed) {
        this.passed = passed;
        return this;
    }
    
    public ValidationRuleResultJpaEntityBuilder message(String message) {
        this.message = message;
        return this;
    }
    
    public ValidationRuleResultJpaEntityBuilder severity(ValidationSeverity severity) {
        this.severity = severity;
        return this;
    }
    
    public ValidationRuleResultJpaEntityBuilder overridable(Boolean overridable) {
        this.overridable = overridable;
        return this;
    }
    
    public ValidationRuleResultJpaEntityBuilder overridden(Boolean overridden) {
        this.overridden = overridden;
        return this;
    }
    
    public ValidationRuleResultJpaEntityBuilder overrideReason(String overrideReason) {
        this.overrideReason = overrideReason;
        return this;
    }
    
    public ValidationRuleResultJpaEntityBuilder overriddenBy(String overriddenBy) {
        this.overriddenBy = overriddenBy;
        return this;
    }
    
    public ValidationRuleResultJpaEntity build() {
        ValidationRuleResultJpaEntity entity = new ValidationRuleResultJpaEntity();
        entity.setId(id);
        entity.setLoanApplication(loanApplication);
        entity.setRuleId(ruleId);
        entity.setRuleName(ruleName);
        entity.setPassed(passed);
        entity.setMessage(message);
        entity.setSeverity(severity);
        entity.setOverridable(overridable);
        entity.setOverridden(overridden);
        entity.setOverrideReason(overrideReason);
        entity.setOverriddenBy(overriddenBy);
        return entity;
    }
}
