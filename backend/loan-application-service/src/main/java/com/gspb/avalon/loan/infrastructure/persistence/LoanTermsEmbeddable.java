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
@Builder
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
}
