package com.gspb.avalon.loan.application.dto;

import com.gspb.avalon.loan.domain.model.InterestRateType;
import com.gspb.avalon.loan.domain.model.PaymentFrequency;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Command for updating loan terms.
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UpdateLoanTermsCommand {
    
    private UUID loanApplicationId;
    
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
    private BigDecimal amount;
    
    @NotNull(message = "Currency is required")
    private String currency;
    
    @NotNull(message = "Term in months is required")
    @Min(value = 1, message = "Term must be at least 1 month")
    private Integer termInMonths;
    
    @NotNull(message = "Interest rate is required")
    @DecimalMin(value = "0.01", message = "Interest rate must be greater than zero")
    private BigDecimal interestRate;
    
    @NotNull(message = "Interest rate type is required")
    private InterestRateType interestRateType;
    
    @NotNull(message = "Payment frequency is required")
    private PaymentFrequency paymentFrequency;
    
    @NotNull(message = "Start date is required")
    private LocalDate startDate;
    
    @NotNull(message = "Maturity date is required")
    private LocalDate maturityDate;
    
    @NotNull(message = "Origination fee is required")
    @DecimalMin(value = "0.0", message = "Origination fee must be non-negative")
    private BigDecimal originationFee;
    
    private boolean earlyRepaymentAllowed;
    
    private BigDecimal earlyRepaymentFee;
    
    public void setLoanApplicationId(UUID loanApplicationId) {
        this.loanApplicationId = loanApplicationId;
    }
    
    public UUID getLoanApplicationId() {
        return loanApplicationId;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    public String getCurrency() {
        return currency;
    }
    
    public Integer getTermInMonths() {
        return termInMonths;
    }
    
    public BigDecimal getInterestRate() {
        return interestRate;
    }
    
    public InterestRateType getInterestRateType() {
        return interestRateType;
    }
    
    public PaymentFrequency getPaymentFrequency() {
        return paymentFrequency;
    }
    
    public LocalDate getStartDate() {
        return startDate;
    }
    
    public LocalDate getMaturityDate() {
        return maturityDate;
    }
    
    public BigDecimal getOriginationFee() {
        return originationFee;
    }
    
    public boolean isEarlyRepaymentAllowed() {
        return earlyRepaymentAllowed;
    }
    
    public BigDecimal getEarlyRepaymentFee() {
        return earlyRepaymentFee;
    }
    
    public static UpdateLoanTermsCommandBuilder builder() {
        return new UpdateLoanTermsCommandBuilder();
    }
}
