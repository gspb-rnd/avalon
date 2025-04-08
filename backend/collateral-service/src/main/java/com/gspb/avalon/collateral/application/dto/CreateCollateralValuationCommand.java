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
@Builder(toBuilder = true)
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
    
    public UUID getCollateralId() {
        return collateralId;
    }
    
    public void setCollateralId(UUID collateralId) {
        this.collateralId = collateralId;
    }
    
    public UUID getLoanApplicationId() {
        return loanApplicationId;
    }
    
    public void setLoanApplicationId(UUID loanApplicationId) {
        this.loanApplicationId = loanApplicationId;
    }
    
    public AssetClass getAssetClass() {
        return assetClass;
    }
    
    public void setAssetClass(AssetClass assetClass) {
        this.assetClass = assetClass;
    }
    
    public String getAssetDescription() {
        return assetDescription;
    }
    
    public void setAssetDescription(String assetDescription) {
        this.assetDescription = assetDescription;
    }
    
    public ValuationMethod getValuationMethod() {
        return valuationMethod;
    }
    
    public void setValuationMethod(ValuationMethod valuationMethod) {
        this.valuationMethod = valuationMethod;
    }
    
    public static CreateCollateralValuationCommandBuilder builder() {
        return new CreateCollateralValuationCommandBuilder();
    }
}
