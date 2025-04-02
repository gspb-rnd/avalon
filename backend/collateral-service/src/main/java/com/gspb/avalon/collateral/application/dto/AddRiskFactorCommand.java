package com.gspb.avalon.collateral.application.dto;

import com.gspb.avalon.collateral.domain.model.RiskLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Command for adding a risk factor to a collateral valuation.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddRiskFactorCommand {
    
    @NotNull(message = "Valuation ID is required")
    private UUID valuationId;
    
    @NotBlank(message = "Factor name is required")
    private String factorName;
    
    @NotBlank(message = "Description is required")
    private String description;
    
    @NotNull(message = "Impact level is required")
    private RiskLevel impact;
}
