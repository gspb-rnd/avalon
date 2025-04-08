package com.gspb.avalon.loan.application.dto;

import com.gspb.avalon.loan.domain.model.InterestRateType;
import com.gspb.avalon.loan.domain.model.PaymentFrequency;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Builder class for UpdateLoanTermsCommand.
 */
public class UpdateLoanTermsCommandBuilder {
    
    private UUID loanApplicationId;
    private BigDecimal amount;
    private String currency;
    private Integer termInMonths;
    private BigDecimal interestRate;
    private InterestRateType interestRateType;
    private PaymentFrequency paymentFrequency;
    private LocalDate startDate;
    private LocalDate maturityDate;
    private BigDecimal originationFee;
    private boolean earlyRepaymentAllowed;
    private BigDecimal earlyRepaymentFee;
    
    public UpdateLoanTermsCommandBuilder loanApplicationId(UUID loanApplicationId) {
        this.loanApplicationId = loanApplicationId;
        return this;
    }
    
    public UpdateLoanTermsCommandBuilder amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }
    
    public UpdateLoanTermsCommandBuilder currency(String currency) {
        this.currency = currency;
        return this;
    }
    
    public UpdateLoanTermsCommandBuilder termInMonths(Integer termInMonths) {
        this.termInMonths = termInMonths;
        return this;
    }
    
    public UpdateLoanTermsCommandBuilder interestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
        return this;
    }
    
    public UpdateLoanTermsCommandBuilder interestRateType(InterestRateType interestRateType) {
        this.interestRateType = interestRateType;
        return this;
    }
    
    public UpdateLoanTermsCommandBuilder paymentFrequency(PaymentFrequency paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
        return this;
    }
    
    public UpdateLoanTermsCommandBuilder startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }
    
    public UpdateLoanTermsCommandBuilder maturityDate(LocalDate maturityDate) {
        this.maturityDate = maturityDate;
        return this;
    }
    
    public UpdateLoanTermsCommandBuilder originationFee(BigDecimal originationFee) {
        this.originationFee = originationFee;
        return this;
    }
    
    public UpdateLoanTermsCommandBuilder earlyRepaymentAllowed(boolean earlyRepaymentAllowed) {
        this.earlyRepaymentAllowed = earlyRepaymentAllowed;
        return this;
    }
    
    public UpdateLoanTermsCommandBuilder earlyRepaymentFee(BigDecimal earlyRepaymentFee) {
        this.earlyRepaymentFee = earlyRepaymentFee;
        return this;
    }
    
    public UpdateLoanTermsCommand build() {
        UpdateLoanTermsCommand command = new UpdateLoanTermsCommand();
        command.setLoanApplicationId(loanApplicationId);
        command.setAmount(amount);
        command.setCurrency(currency);
        command.setTermInMonths(termInMonths);
        command.setInterestRate(interestRate);
        command.setInterestRateType(interestRateType);
        command.setPaymentFrequency(paymentFrequency);
        command.setStartDate(startDate);
        command.setMaturityDate(maturityDate);
        command.setOriginationFee(originationFee);
        command.setEarlyRepaymentAllowed(earlyRepaymentAllowed);
        command.setEarlyRepaymentFee(earlyRepaymentFee);
        return command;
    }
}
