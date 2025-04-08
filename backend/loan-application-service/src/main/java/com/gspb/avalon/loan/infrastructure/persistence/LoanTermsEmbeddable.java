package com.gspb.avalon.loan.infrastructure.persistence;

import com.gspb.avalon.loan.domain.model.InterestRateType;
import com.gspb.avalon.loan.domain.model.PaymentFrequency;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Embeddable JPA entity for loan terms.
 */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class LoanTermsEmbeddable {
    
    @Column(name = "amount", precision = 19, scale = 2)
    private BigDecimal amount;
    
    @Column(name = "currency", length = 3)
    private String currency;
    
    @Column(name = "term_in_months")
    private Integer termInMonths;
    
    @Column(name = "interest_rate", precision = 5, scale = 2)
    private BigDecimal interestRate;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "interest_rate_type")
    private InterestRateType interestRateType;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_frequency")
    private PaymentFrequency paymentFrequency;
    
    @Column(name = "start_date")
    private LocalDate startDate;
    
    @Column(name = "maturity_date")
    private LocalDate maturityDate;
    
    @Column(name = "origination_fee", precision = 19, scale = 2)
    private BigDecimal originationFee;
    
    @Column(name = "early_repayment_allowed")
    private Boolean earlyRepaymentAllowed;
    
    @Column(name = "early_repayment_fee", precision = 19, scale = 2)
    private BigDecimal earlyRepaymentFee;
    
    public static LoanTermsEmbeddableBuilder builder() {
        return new LoanTermsEmbeddableBuilder();
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
    
    public Integer getTermInMonths() {
        return termInMonths;
    }
    
    public void setTermInMonths(Integer termInMonths) {
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
        return earlyRepaymentAllowed != null && earlyRepaymentAllowed;
    }
    
    public Boolean getEarlyRepaymentAllowed() {
        return earlyRepaymentAllowed;
    }
    
    public void setEarlyRepaymentAllowed(Boolean earlyRepaymentAllowed) {
        this.earlyRepaymentAllowed = earlyRepaymentAllowed;
    }
    
    public BigDecimal getEarlyRepaymentFee() {
        return earlyRepaymentFee;
    }
    
    public void setEarlyRepaymentFee(BigDecimal earlyRepaymentFee) {
        this.earlyRepaymentFee = earlyRepaymentFee;
    }
}
