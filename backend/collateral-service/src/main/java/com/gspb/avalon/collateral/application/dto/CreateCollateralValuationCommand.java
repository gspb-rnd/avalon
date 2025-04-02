package com.gspb.avalon.collateral.application.dto;

import com.gspb.avalon.collateral.domain.model.AssetClass;
import com.gspb.avalon.collateral.domain.model.ValuationMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Command for creating a new collateral valuation.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCollateralValuationCommand {
    
    @NotNull(message = "Collateral ID is required")
    private UUID collateralId;
    
    @NotNull(message = "Loan application ID is required")
    private UUID loanApplicationId;
    
    @NotNull(message = "Asset class is required")
    private AssetClass assetClass;
    
    @NotBlank(message = "Asset description is required")
    private String assetDescription;
    
    @NotNull(message = "Valuation method is required")
    private ValuationMethod valuationMethod;
}
