package com.gspb.avalon.loan.domain.model;

import com.gspb.avalon.shared.domain.ValueObject;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Value object representing loan terms in the system.
 */
@Getter
public class LoanTerms extends ValueObject {
    
    private final BigDecimal amount;
    private final String currency;
    private final int termInMonths;
    private final BigDecimal interestRate;
    private final InterestRateType interestRateType;
    private final PaymentFrequency paymentFrequency;
    private final LocalDate startDate;
    private final LocalDate maturityDate;
    private final BigDecimal originationFee;
    private final boolean earlyRepaymentAllowed;
    private final BigDecimal earlyRepaymentFee;
    
    /**
     * Creates new loan terms.
     *
     * @param amount The loan amount
     * @param currency The loan currency
     * @param termInMonths The loan term in months
     * @param interestRate The interest rate
     * @param interestRateType The interest rate type
     * @param paymentFrequency The payment frequency
     * @param startDate The start date
     * @param maturityDate The maturity date
     * @param originationFee The origination fee
     * @param earlyRepaymentAllowed Whether early repayment is allowed
     * @param earlyRepaymentFee The early repayment fee
     */
    public LoanTerms(BigDecimal amount, String currency, int termInMonths, BigDecimal interestRate,
                    InterestRateType interestRateType, PaymentFrequency paymentFrequency,
                    LocalDate startDate, LocalDate maturityDate, BigDecimal originationFee,
                    boolean earlyRepaymentAllowed, BigDecimal earlyRepaymentFee) {
        this.amount = amount;
        this.currency = currency;
        this.termInMonths = termInMonths;
        this.interestRate = interestRate;
        this.interestRateType = interestRateType;
        this.paymentFrequency = paymentFrequency;
        this.startDate = startDate;
        this.maturityDate = maturityDate;
        this.originationFee = originationFee;
        this.earlyRepaymentAllowed = earlyRepaymentAllowed;
        this.earlyRepaymentFee = earlyRepaymentFee;
    }
    
    /**
     * Calculates the total cost of the loan.
     *
     * @return The total cost
     */
    public BigDecimal calculateTotalCost() {
        BigDecimal totalInterest = amount.multiply(interestRate.divide(BigDecimal.valueOf(100)))
                .multiply(BigDecimal.valueOf(termInMonths / 12.0));
        
        return amount.add(totalInterest).add(originationFee);
    }
    
    /**
     * Calculates the monthly payment for the loan.
     *
     * @return The monthly payment
     */
    public BigDecimal calculateMonthlyPayment() {
        BigDecimal monthlyRate = interestRate.divide(BigDecimal.valueOf(1200));
        double factor = Math.pow(1 + monthlyRate.doubleValue(), termInMonths);
        
        return amount.multiply(monthlyRate.multiply(BigDecimal.valueOf(factor)))
                .divide(BigDecimal.valueOf(factor - 1), 2, BigDecimal.ROUND_HALF_UP);
    }
}
