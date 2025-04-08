package com.gspb.avalon.loan.application.dto;

import com.gspb.avalon.loan.domain.model.CollateralType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Command for adding a collateral to a loan application.
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class AddCollateralCommand {
    
    private UUID loanApplicationId;
    
    @NotNull(message = "Collateral type is required")
    private CollateralType type;
    
    @NotBlank(message = "Description is required")
    private String description;
    
    @NotNull(message = "Estimated value is required")
    @DecimalMin(value = "0.01", message = "Estimated value must be greater than zero")
    private BigDecimal estimatedValue;
    
    private String documentationUrl;
    
    public UUID getLoanApplicationId() {
        return loanApplicationId;
    }
    
    public CollateralType getType() {
        return type;
    }
    
    public String getDescription() {
        return description;
    }
    
    public BigDecimal getEstimatedValue() {
        return estimatedValue;
    }
    
    public String getDocumentationUrl() {
        return documentationUrl;
    }
    
    public void setLoanApplicationId(UUID loanApplicationId) {
        this.loanApplicationId = loanApplicationId;
    }
    
    public static AddCollateralCommandBuilder builder() {
        return new AddCollateralCommandBuilder();
    }
}
