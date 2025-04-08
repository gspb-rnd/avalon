package com.gspb.avalon.loan.application.dto;

import com.gspb.avalon.loan.domain.model.InterestRateType;
import com.gspb.avalon.loan.domain.model.PaymentFrequency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Data Transfer Object for LoanTerms.
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class LoanTermsDTO {
    
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
    
    public static LoanTermsDTOBuilder builder() {
        return new LoanTermsDTOBuilder();
    }
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    public String getCurrency() {
        return currency;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    public int getTermInMonths() {
        return termInMonths;
    }
    
    public void setTermInMonths(int termInMonths) {
        this.termInMonths = termInMonths;
    }
    
    public BigDecimal getInterestRate() {
        return interestRate;
    }
    
    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
    
    public InterestRateType getInterestRateType() {
        return interestRateType;
    }
    
    public void setInterestRateType(InterestRateType interestRateType) {
        this.interestRateType = interestRateType;
    }
    
    public PaymentFrequency getPaymentFrequency() {
        return paymentFrequency;
    }
    
    public void setPaymentFrequency(PaymentFrequency paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }
    
    public LocalDate getStartDate() {
        return startDate;
    }
    
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    
    public LocalDate getMaturityDate() {
        return maturityDate;
    }
    
    public void setMaturityDate(LocalDate maturityDate) {
        this.maturityDate = maturityDate;
    }
    
    public BigDecimal getOriginationFee() {
        return originationFee;
    }
    
    public void setOriginationFee(BigDecimal originationFee) {
        this.originationFee = originationFee;
    }
    
    public boolean isEarlyRepaymentAllowed() {
        return earlyRepaymentAllowed;
    }
    
    public void setEarlyRepaymentAllowed(boolean earlyRepaymentAllowed) {
        this.earlyRepaymentAllowed = earlyRepaymentAllowed;
    }
    
    public BigDecimal getEarlyRepaymentFee() {
        return earlyRepaymentFee;
    }
    
    public void setEarlyRepaymentFee(BigDecimal earlyRepaymentFee) {
        this.earlyRepaymentFee = earlyRepaymentFee;
    }
    
    public BigDecimal getMonthlyPayment() {
        return monthlyPayment;
    }
    
    public void setMonthlyPayment(BigDecimal monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }
    
    public BigDecimal getTotalCost() {
        return totalCost;
    }
    
    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }
}
