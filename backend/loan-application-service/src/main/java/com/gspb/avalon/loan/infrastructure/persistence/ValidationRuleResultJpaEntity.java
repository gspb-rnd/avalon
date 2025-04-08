package com.gspb.avalon.loan.infrastructure.persistence;

import com.gspb.avalon.loan.domain.model.ValidationSeverity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * JPA entity for validation rule result.
 */
@Entity
@Table(name = "validation_rule_results")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ValidationRuleResultJpaEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_application_id", nullable = false)
    private LoanApplicationJpaEntity loanApplication;
    
    @Column(name = "rule_id", nullable = false)
    private String ruleId;
    
    @Column(name = "rule_name", nullable = false)
    private String ruleName;
    
    @Column(name = "passed", nullable = false)
    private Boolean passed;
    
    @Column(name = "message")
    private String message;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "severity", nullable = false)
    private ValidationSeverity severity;
    
    @Column(name = "overridable", nullable = false)
    private Boolean overridable;
    
    @Column(name = "overridden", nullable = false)
    private Boolean overridden;
    
    @Column(name = "override_reason")
    private String overrideReason;
    
    @Column(name = "overridden_by")
    private String overriddenBy;
    
    public static ValidationRuleResultJpaEntityBuilder builder() {
        return new ValidationRuleResultJpaEntityBuilder();
    }
    
    public boolean isPassed() {
        return passed != null && passed;
    }
    
    public boolean isOverridable() {
        return overridable != null && overridable;
    }
    
    public boolean isOverridden() {
        return overridden != null && overridden;
    }
    
    public String getRuleId() {
        return ruleId;
    }
    
    public String getRuleName() {
        return ruleName;
    }
    
    public Boolean getPassed() {
        return passed;
    }
    
    public String getMessage() {
        return message;
    }
    
    public ValidationSeverity getSeverity() {
        return severity;
    }
    
    public Boolean getOverridable() {
        return overridable;
    }
    
    public Boolean getOverridden() {
        return overridden;
    }
    
    public String getOverrideReason() {
        return overrideReason;
    }
    
    public String getOverriddenBy() {
        return overriddenBy;
    }
    
    /**
     * Checks if the validation rule is effectively passed (either passed or overridden).
     *
     * @return True if the rule is effectively passed, false otherwise
     */
    public boolean isEffectivelyPassed() {
        return (passed != null && passed) || (overridden != null && overridden);
    }
}
