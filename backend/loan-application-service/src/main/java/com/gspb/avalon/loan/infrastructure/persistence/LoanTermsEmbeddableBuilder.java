package com.gspb.avalon.loan.infrastructure.persistence;

import com.gspb.avalon.loan.domain.model.InterestRateType;
import com.gspb.avalon.loan.domain.model.PaymentFrequency;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Builder class for LoanTermsEmbeddable.
 */
public class LoanTermsEmbeddableBuilder {
    
    private BigDecimal amount;
    private String currency;
    private Integer termInMonths;
    private BigDecimal interestRate;
    private InterestRateType interestRateType;
    private PaymentFrequency paymentFrequency;
    private LocalDate startDate;
    private LocalDate maturityDate;
    private BigDecimal originationFee;
    private Boolean earlyRepaymentAllowed;
    private BigDecimal earlyRepaymentFee;
    
    public LoanTermsEmbeddableBuilder amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }
    
    public LoanTermsEmbeddableBuilder currency(String currency) {
        this.currency = currency;
        return this;
    }
    
    public LoanTermsEmbeddableBuilder termInMonths(Integer termInMonths) {
        this.termInMonths = termInMonths;
        return this;
    }
    
    public LoanTermsEmbeddableBuilder interestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
        return this;
    }
    
    public LoanTermsEmbeddableBuilder interestRateType(InterestRateType interestRateType) {
        this.interestRateType = interestRateType;
        return this;
    }
    
    public LoanTermsEmbeddableBuilder paymentFrequency(PaymentFrequency paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
        return this;
    }
    
    public LoanTermsEmbeddableBuilder startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }
    
    public LoanTermsEmbeddableBuilder maturityDate(LocalDate maturityDate) {
        this.maturityDate = maturityDate;
        return this;
    }
    
    public LoanTermsEmbeddableBuilder originationFee(BigDecimal originationFee) {
        this.originationFee = originationFee;
        return this;
    }
    
    public LoanTermsEmbeddableBuilder earlyRepaymentAllowed(Boolean earlyRepaymentAllowed) {
        this.earlyRepaymentAllowed = earlyRepaymentAllowed;
        return this;
    }
    
    public LoanTermsEmbeddableBuilder earlyRepaymentFee(BigDecimal earlyRepaymentFee) {
        this.earlyRepaymentFee = earlyRepaymentFee;
        return this;
    }
    
    public LoanTermsEmbeddable build() {
        LoanTermsEmbeddable embeddable = new LoanTermsEmbeddable();
        embeddable.setAmount(amount);
        embeddable.setCurrency(currency);
        embeddable.setTermInMonths(termInMonths);
        embeddable.setInterestRate(interestRate);
        embeddable.setInterestRateType(interestRateType);
        embeddable.setPaymentFrequency(paymentFrequency);
        embeddable.setStartDate(startDate);
        embeddable.setMaturityDate(maturityDate);
        embeddable.setOriginationFee(originationFee);
        embeddable.setEarlyRepaymentAllowed(earlyRepaymentAllowed);
        embeddable.setEarlyRepaymentFee(earlyRepaymentFee);
        return embeddable;
    }
}
