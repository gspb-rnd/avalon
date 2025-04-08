package com.gspb.avalon.loan.application.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Command for updating a collateral valuation.
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCollateralValuationCommand {
    
    public void setLoanApplicationId(UUID loanApplicationId) {
        this.loanApplicationId = loanApplicationId;
    }
    
    public void setCollateralId(UUID collateralId) {
        this.collateralId = collateralId;
    }
    
    public BigDecimal getAppraiserValue() {
        return appraiserValue;
    }
    
    public String getAppraiserName() {
        return appraiserName;
    }
    
    public LocalDate getValuationDate() {
        return valuationDate;
    }
    
    public BigDecimal getLoanToValueRatio() {
        return loanToValueRatio;
    }
    
    private UUID loanApplicationId;
    
    private UUID collateralId;
    
    @NotNull(message = "Appraiser value is required")
    @DecimalMin(value = "0.01", message = "Appraiser value must be greater than zero")
    private BigDecimal appraiserValue;
    
    @NotBlank(message = "Appraiser name is required")
    private String appraiserName;
    
    @NotNull(message = "Valuation date is required")
    private LocalDate valuationDate;
    
    @NotNull(message = "Loan to value ratio is required")
    @DecimalMin(value = "0.01", message = "Loan to value ratio must be greater than zero")
    private BigDecimal loanToValueRatio;
}
