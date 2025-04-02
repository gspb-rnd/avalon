package com.gspb.avalon.loan.application.service;

import com.gspb.avalon.loan.domain.model.LoanApplication;
import com.gspb.avalon.loan.domain.model.ValidationRuleResult;
import com.gspb.avalon.loan.domain.model.ValidationSeverity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for validating loan applications.
 * This implementation delegates to the Drools rule engine for validation.
 */
@Service
@RequiredArgsConstructor
public class ValidationService {
    
    private final DroolsValidationService droolsValidationService;
    
    /**
     * Validates a loan application using Drools rules.
     *
     * @param loanApplication The loan application to validate
     * @return A list of validation rule results
     */
    public List<ValidationRuleResult> validateLoanApplication(LoanApplication loanApplication) {
        return droolsValidationService.validateLoanApplication(loanApplication);
    }
}
