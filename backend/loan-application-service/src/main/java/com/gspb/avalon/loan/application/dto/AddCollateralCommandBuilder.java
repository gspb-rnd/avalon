package com.gspb.avalon.loan.application.dto;

import com.gspb.avalon.loan.domain.model.CollateralType;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Builder class for AddCollateralCommand.
 */
public class AddCollateralCommandBuilder {
    
    private UUID loanApplicationId;
    private CollateralType type;
    private String description;
    private BigDecimal estimatedValue;
    private String documentationUrl;
    
    public AddCollateralCommandBuilder loanApplicationId(UUID loanApplicationId) {
        this.loanApplicationId = loanApplicationId;
        return this;
    }
    
    public AddCollateralCommandBuilder type(CollateralType type) {
        this.type = type;
        return this;
    }
    
    public AddCollateralCommandBuilder description(String description) {
        this.description = description;
        return this;
    }
    
    public AddCollateralCommandBuilder estimatedValue(BigDecimal estimatedValue) {
        this.estimatedValue = estimatedValue;
        return this;
    }
    
    public AddCollateralCommandBuilder documentationUrl(String documentationUrl) {
        this.documentationUrl = documentationUrl;
        return this;
    }
    
    public AddCollateralCommand build() {
        AddCollateralCommand command = new AddCollateralCommand();
        command.setLoanApplicationId(loanApplicationId);
        command.setType(type);
        command.setDescription(description);
        command.setEstimatedValue(estimatedValue);
        command.setDocumentationUrl(documentationUrl);
        return command;
    }
}
