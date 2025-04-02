package com.gspb.avalon.collateral.application.dto;

import com.gspb.avalon.collateral.domain.model.RiskLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Command for updating a collateral valuation with estimated value and risk assessment.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCollateralValuationCommand {
    
    @NotNull(message = "Valuation ID is required")
    private UUID valuationId;
    
    @NotNull(message = "Estimated value is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Estimated value must be greater than zero")
    private BigDecimal estimatedValue;
    
    @NotNull(message = "Haircut percentage is required")
    @DecimalMin(value = "0.0", message = "Haircut must be at least 0%")
    @DecimalMax(value = "100.0", message = "Haircut cannot exceed 100%")
    private BigDecimal haircut;
    
    @NotNull(message = "Risk level is required")
    private RiskLevel riskLevel;
    
    @NotNull(message = "Valuated by is required")
    private String valuatedBy;
    
    @NotNull(message = "Expiration date is required")
    private LocalDateTime expirationDate;
}
