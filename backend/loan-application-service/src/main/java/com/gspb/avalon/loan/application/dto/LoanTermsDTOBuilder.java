package com.gspb.avalon.loan.application.dto;

import com.gspb.avalon.loan.domain.model.InterestRateType;
import com.gspb.avalon.loan.domain.model.PaymentFrequency;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Builder class for LoanTermsDTO.
 */
public class LoanTermsDTOBuilder {
    
    private BigDecimal amount;
    private String currency;
    private int termInMonths;
    private BigDecimal interestRate;
    private InterestRateType interestRateType;
    private PaymentFrequency paymentFrequency;
    private LocalDate startDate;
    private LocalDate maturityDate;
    private BigDecimal originationFee;
    private boolean earlyRepaymentAllowed;
    private BigDecimal earlyRepaymentFee;
    private BigDecimal monthlyPayment;
    private BigDecimal totalCost;
    
    public LoanTermsDTOBuilder amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }
    
    public LoanTermsDTOBuilder currency(String currency) {
        this.currency = currency;
        return this;
    }
    
    public LoanTermsDTOBuilder termInMonths(int termInMonths) {
        this.termInMonths = termInMonths;
        return this;
    }
    
    public LoanTermsDTOBuilder interestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
        return this;
    }
    
    public LoanTermsDTOBuilder interestRateType(InterestRateType interestRateType) {
        this.interestRateType = interestRateType;
        return this;
    }
    
    public LoanTermsDTOBuilder paymentFrequency(PaymentFrequency paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
        return this;
    }
    
    public LoanTermsDTOBuilder startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }
    
    public LoanTermsDTOBuilder maturityDate(LocalDate maturityDate) {
        this.maturityDate = maturityDate;
        return this;
    }
    
    public LoanTermsDTOBuilder originationFee(BigDecimal originationFee) {
        this.originationFee = originationFee;
        return this;
    }
    
    public LoanTermsDTOBuilder earlyRepaymentAllowed(boolean earlyRepaymentAllowed) {
        this.earlyRepaymentAllowed = earlyRepaymentAllowed;
        return this;
    }
    
    public LoanTermsDTOBuilder earlyRepaymentFee(BigDecimal earlyRepaymentFee) {
        this.earlyRepaymentFee = earlyRepaymentFee;
        return this;
    }
    
    public LoanTermsDTOBuilder monthlyPayment(BigDecimal monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
        return this;
    }
    
    public LoanTermsDTOBuilder totalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
        return this;
    }
    
    public LoanTermsDTO build() {
        LoanTermsDTO loanTermsDTO = new LoanTermsDTO();
        loanTermsDTO.setAmount(amount);
        loanTermsDTO.setCurrency(currency);
        loanTermsDTO.setTermInMonths(termInMonths);
        loanTermsDTO.setInterestRate(interestRate);
        loanTermsDTO.setInterestRateType(interestRateType);
        loanTermsDTO.setPaymentFrequency(paymentFrequency);
        loanTermsDTO.setStartDate(startDate);
        loanTermsDTO.setMaturityDate(maturityDate);
        loanTermsDTO.setOriginationFee(originationFee);
        loanTermsDTO.setEarlyRepaymentAllowed(earlyRepaymentAllowed);
        loanTermsDTO.setEarlyRepaymentFee(earlyRepaymentFee);
        loanTermsDTO.setMonthlyPayment(monthlyPayment);
        loanTermsDTO.setTotalCost(totalCost);
        return loanTermsDTO;
    }
}
