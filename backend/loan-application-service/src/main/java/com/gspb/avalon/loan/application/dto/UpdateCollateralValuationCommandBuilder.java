package com.gspb.avalon.loan.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Builder class for UpdateCollateralValuationCommand.
 */
public class UpdateCollateralValuationCommandBuilder {
    
    private UUID loanApplicationId;
    private UUID collateralId;
    private BigDecimal appraiserValue;
    private String appraiserName;
    private LocalDate valuationDate;
    private BigDecimal loanToValueRatio;
    
    public UpdateCollateralValuationCommandBuilder loanApplicationId(UUID loanApplicationId) {
        this.loanApplicationId = loanApplicationId;
        return this;
    }
    
    public UpdateCollateralValuationCommandBuilder collateralId(UUID collateralId) {
        this.collateralId = collateralId;
        return this;
    }
    
    public UpdateCollateralValuationCommandBuilder appraiserValue(BigDecimal appraiserValue) {
        this.appraiserValue = appraiserValue;
        return this;
    }
    
    public UpdateCollateralValuationCommandBuilder appraiserName(String appraiserName) {
        this.appraiserName = appraiserName;
        return this;
    }
    
    public UpdateCollateralValuationCommandBuilder valuationDate(LocalDate valuationDate) {
        this.valuationDate = valuationDate;
        return this;
    }
    
    public UpdateCollateralValuationCommandBuilder loanToValueRatio(BigDecimal loanToValueRatio) {
        this.loanToValueRatio = loanToValueRatio;
        return this;
    }
    
    public UpdateCollateralValuationCommand build() {
        UpdateCollateralValuationCommand command = new UpdateCollateralValuationCommand();
        command.setLoanApplicationId(loanApplicationId);
        command.setCollateralId(collateralId);
        command.setAppraiserValue(appraiserValue);
        command.setAppraiserName(appraiserName);
        command.setValuationDate(valuationDate);
        command.setLoanToValueRatio(loanToValueRatio);
        return command;
    }
}
