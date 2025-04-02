package com.gspb.avalon.loan.application.service;

import com.gspb.avalon.loan.domain.model.LoanApplication;
import com.gspb.avalon.loan.domain.model.ValidationRuleResult;
import com.gspb.avalon.loan.domain.model.ValidationSeverity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for validating loan applications.
 */
@Service
public class ValidationService {
    
    private static final String RULE_LTV_RATIO = "LTV_RATIO";
    private static final String RULE_MIN_LOAN_AMOUNT = "MIN_LOAN_AMOUNT";
    private static final String RULE_MAX_LOAN_AMOUNT = "MAX_LOAN_AMOUNT";
    private static final String RULE_MIN_TERM = "MIN_TERM";
    private static final String RULE_MAX_TERM = "MAX_TERM";
    private static final String RULE_COLLATERAL_VERIFICATION = "COLLATERAL_VERIFICATION";
    private static final String RULE_INTEREST_RATE = "INTEREST_RATE";
    
    private static final BigDecimal MAX_LTV_RATIO = new BigDecimal("80.0");
    private static final BigDecimal MIN_LOAN_AMOUNT = new BigDecimal("100000.0");
    private static final BigDecimal MAX_LOAN_AMOUNT = new BigDecimal("10000000.0");
    private static final int MIN_TERM_MONTHS = 6;
    private static final int MAX_TERM_MONTHS = 360;
    private static final BigDecimal MIN_INTEREST_RATE = new BigDecimal("1.0");
    private static final BigDecimal MAX_INTEREST_RATE = new BigDecimal("20.0");
    
    /**
     * Validates a loan application.
     *
     * @param loanApplication The loan application to validate
     * @return A list of validation rule results
     */
    public List<ValidationRuleResult> validateLoanApplication(LoanApplication loanApplication) {
        List<ValidationRuleResult> results = new ArrayList<>();
        
        results.add(validateLoanToValueRatio(loanApplication));
        
        results.add(validateMinLoanAmount(loanApplication));
        results.add(validateMaxLoanAmount(loanApplication));
        
        results.add(validateMinTerm(loanApplication));
        results.add(validateMaxTerm(loanApplication));
        
        results.add(validateCollateralVerification(loanApplication));
        
        results.add(validateInterestRate(loanApplication));
        
        return results;
    }
    
    /**
     * Validates the loan-to-value ratio.
     *
     * @param loanApplication The loan application to validate
     * @return The validation rule result
     */
    private ValidationRuleResult validateLoanToValueRatio(LoanApplication loanApplication) {
        BigDecimal ltvRatio = loanApplication.calculateLoanToValueRatio();
        boolean passed = ltvRatio.compareTo(MAX_LTV_RATIO) <= 0;
        
        return new ValidationRuleResult(
                RULE_LTV_RATIO,
                "Loan-to-Value Ratio",
                passed,
                passed ? "Loan-to-value ratio is within acceptable limits" : 
                       "Loan-to-value ratio exceeds maximum allowed value of " + MAX_LTV_RATIO + "%",
                passed ? ValidationSeverity.INFO : ValidationSeverity.ERROR,
                true
        );
    }
    
    /**
     * Validates the minimum loan amount.
     *
     * @param loanApplication The loan application to validate
     * @return The validation rule result
     */
    private ValidationRuleResult validateMinLoanAmount(LoanApplication loanApplication) {
        if (loanApplication.getTerms() == null) {
            return new ValidationRuleResult(
                    RULE_MIN_LOAN_AMOUNT,
                    "Minimum Loan Amount",
                    false,
                    "Loan terms are not set",
                    ValidationSeverity.ERROR,
                    false
            );
        }
        
        BigDecimal amount = loanApplication.getTerms().getAmount();
        boolean passed = amount.compareTo(MIN_LOAN_AMOUNT) >= 0;
        
        return new ValidationRuleResult(
                RULE_MIN_LOAN_AMOUNT,
                "Minimum Loan Amount",
                passed,
                passed ? "Loan amount meets minimum requirements" : 
                       "Loan amount is below minimum allowed value of " + MIN_LOAN_AMOUNT,
                passed ? ValidationSeverity.INFO : ValidationSeverity.ERROR,
                false
        );
    }
    
    /**
     * Validates the maximum loan amount.
     *
     * @param loanApplication The loan application to validate
     * @return The validation rule result
     */
    private ValidationRuleResult validateMaxLoanAmount(LoanApplication loanApplication) {
        if (loanApplication.getTerms() == null) {
            return new ValidationRuleResult(
                    RULE_MAX_LOAN_AMOUNT,
                    "Maximum Loan Amount",
                    false,
                    "Loan terms are not set",
                    ValidationSeverity.ERROR,
                    false
            );
        }
        
        BigDecimal amount = loanApplication.getTerms().getAmount();
        boolean passed = amount.compareTo(MAX_LOAN_AMOUNT) <= 0;
        
        return new ValidationRuleResult(
                RULE_MAX_LOAN_AMOUNT,
                "Maximum Loan Amount",
                passed,
                passed ? "Loan amount is within maximum limits" : 
                       "Loan amount exceeds maximum allowed value of " + MAX_LOAN_AMOUNT,
                passed ? ValidationSeverity.INFO : ValidationSeverity.WARNING,
                true
        );
    }
    
    /**
     * Validates the minimum loan term.
     *
     * @param loanApplication The loan application to validate
     * @return The validation rule result
     */
    private ValidationRuleResult validateMinTerm(LoanApplication loanApplication) {
        if (loanApplication.getTerms() == null) {
            return new ValidationRuleResult(
                    RULE_MIN_TERM,
                    "Minimum Loan Term",
                    false,
                    "Loan terms are not set",
                    ValidationSeverity.ERROR,
                    false
            );
        }
        
        int termInMonths = loanApplication.getTerms().getTermInMonths();
        boolean passed = termInMonths >= MIN_TERM_MONTHS;
        
        return new ValidationRuleResult(
                RULE_MIN_TERM,
                "Minimum Loan Term",
                passed,
                passed ? "Loan term meets minimum requirements" : 
                       "Loan term is below minimum allowed value of " + MIN_TERM_MONTHS + " months",
                passed ? ValidationSeverity.INFO : ValidationSeverity.ERROR,
                false
        );
    }
    
    /**
     * Validates the maximum loan term.
     *
     * @param loanApplication The loan application to validate
     * @return The validation rule result
     */
    private ValidationRuleResult validateMaxTerm(LoanApplication loanApplication) {
        if (loanApplication.getTerms() == null) {
            return new ValidationRuleResult(
                    RULE_MAX_TERM,
                    "Maximum Loan Term",
                    false,
                    "Loan terms are not set",
                    ValidationSeverity.ERROR,
                    false
            );
        }
        
        int termInMonths = loanApplication.getTerms().getTermInMonths();
        boolean passed = termInMonths <= MAX_TERM_MONTHS;
        
        return new ValidationRuleResult(
                RULE_MAX_TERM,
                "Maximum Loan Term",
                passed,
                passed ? "Loan term is within maximum limits" : 
                       "Loan term exceeds maximum allowed value of " + MAX_TERM_MONTHS + " months",
                passed ? ValidationSeverity.INFO : ValidationSeverity.WARNING,
                true
        );
    }
    
    /**
     * Validates that all collaterals are verified.
     *
     * @param loanApplication The loan application to validate
     * @return The validation rule result
     */
    private ValidationRuleResult validateCollateralVerification(LoanApplication loanApplication) {
        boolean allVerified = loanApplication.getCollaterals().stream()
                .allMatch(collateral -> collateral.isVerified());
        
        return new ValidationRuleResult(
                RULE_COLLATERAL_VERIFICATION,
                "Collateral Verification",
                allVerified,
                allVerified ? "All collaterals are verified" : "Not all collaterals are verified",
                allVerified ? ValidationSeverity.INFO : ValidationSeverity.ERROR,
                true
        );
    }
    
    /**
     * Validates the interest rate.
     *
     * @param loanApplication The loan application to validate
     * @return The validation rule result
     */
    private ValidationRuleResult validateInterestRate(LoanApplication loanApplication) {
        if (loanApplication.getTerms() == null) {
            return new ValidationRuleResult(
                    RULE_INTEREST_RATE,
                    "Interest Rate",
                    false,
                    "Loan terms are not set",
                    ValidationSeverity.ERROR,
                    false
            );
        }
        
        BigDecimal interestRate = loanApplication.getTerms().getInterestRate();
        boolean minPassed = interestRate.compareTo(MIN_INTEREST_RATE) >= 0;
        boolean maxPassed = interestRate.compareTo(MAX_INTEREST_RATE) <= 0;
        boolean passed = minPassed && maxPassed;
        
        String message;
        if (passed) {
            message = "Interest rate is within acceptable limits";
        } else if (!minPassed) {
            message = "Interest rate is below minimum allowed value of " + MIN_INTEREST_RATE + "%";
        } else {
            message = "Interest rate exceeds maximum allowed value of " + MAX_INTEREST_RATE + "%";
        }
        
        return new ValidationRuleResult(
                RULE_INTEREST_RATE,
                "Interest Rate",
                passed,
                message,
                passed ? ValidationSeverity.INFO : ValidationSeverity.WARNING,
                true
        );
    }
}
