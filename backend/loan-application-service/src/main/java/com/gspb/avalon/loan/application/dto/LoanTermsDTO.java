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
@Builder
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
}
