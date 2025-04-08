package com.gspb.avalon.collateral.application.dto;

import com.gspb.avalon.collateral.domain.model.AssetClass;
import com.gspb.avalon.collateral.domain.model.ValuationMethod;

import java.util.UUID;

/**
 * Builder class for CreateCollateralValuationCommand.
 */
public class CreateCollateralValuationCommandBuilder {
    
    private UUID collateralId;
    private UUID loanApplicationId;
    private AssetClass assetClass;
    private String assetDescription;
    private ValuationMethod valuationMethod;
    
    public CreateCollateralValuationCommandBuilder collateralId(UUID collateralId) {
        this.collateralId = collateralId;
        return this;
    }
    
    public CreateCollateralValuationCommandBuilder loanApplicationId(UUID loanApplicationId) {
        this.loanApplicationId = loanApplicationId;
        return this;
    }
    
    public CreateCollateralValuationCommandBuilder assetClass(AssetClass assetClass) {
        this.assetClass = assetClass;
        return this;
    }
    
    public CreateCollateralValuationCommandBuilder assetDescription(String assetDescription) {
        this.assetDescription = assetDescription;
        return this;
    }
    
    public CreateCollateralValuationCommandBuilder valuationMethod(ValuationMethod valuationMethod) {
        this.valuationMethod = valuationMethod;
        return this;
    }
    
    public CreateCollateralValuationCommand build() {
        CreateCollateralValuationCommand command = new CreateCollateralValuationCommand();
        command.setCollateralId(collateralId);
        command.setLoanApplicationId(loanApplicationId);
        command.setAssetClass(assetClass);
        command.setAssetDescription(assetDescription);
        command.setValuationMethod(valuationMethod);
        return command;
    }
}
