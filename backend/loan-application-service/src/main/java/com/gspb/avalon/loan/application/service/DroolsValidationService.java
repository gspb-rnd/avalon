package com.gspb.avalon.loan.application.service;

import com.gspb.avalon.loan.domain.model.LoanApplication;
import com.gspb.avalon.loan.domain.model.ValidationRuleResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.runtime.KieSession;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Drools-based implementation of validation service for loan applications.
 * This service uses the Drools rule engine to validate loan applications
 * based on rules defined in DRL files and decision tables.
 */
@Service
@Primary
@RequiredArgsConstructor
@Slf4j
public class DroolsValidationService {
    
    private final DroolsConfig droolsConfig;
    
    /**
     * Validates a loan application using Drools rules.
     *
     * @param loanApplication The loan application to validate
     * @return A list of validation rule results
     */
    public List<ValidationRuleResult> validateLoanApplication(LoanApplication loanApplication) {
        List<ValidationRuleResult> results = new ArrayList<>();
        
        try {
            KieSession kieSession = droolsConfig.createKieSession();
            
            kieSession.setGlobal("validationResults", results);
            
            kieSession.insert(loanApplication);
            
            kieSession.fireAllRules();
            
            kieSession.dispose();
            
            log.info("Loan application validation completed with {} rule results", results.size());
        } catch (IOException e) {
            log.error("Error during loan application validation", e);
            results.add(new ValidationRuleResult(
                    "SYSTEM_ERROR",
                    "System Error",
                    false,
                    "Error during rule execution: " + e.getMessage(),
                    com.gspb.avalon.loan.domain.model.ValidationSeverity.ERROR,
                    false
            ));
        }
        
        return results;
    }
}
