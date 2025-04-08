package com.gspb.avalon.collateral.application.dto;

import com.gspb.avalon.collateral.domain.model.RiskLevel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Builder class for UpdateCollateralValuationCommand.
 */
public class UpdateCollateralValuationCommandBuilder {
    
    private UUID valuationId;
    private BigDecimal estimatedValue;
    private BigDecimal haircut;
    private RiskLevel riskLevel;
    private String valuatedBy;
    private LocalDateTime expirationDate;
    
    public UpdateCollateralValuationCommandBuilder valuationId(UUID valuationId) {
        this.valuationId = valuationId;
        return this;
    }
    
    public UpdateCollateralValuationCommandBuilder estimatedValue(BigDecimal estimatedValue) {
        this.estimatedValue = estimatedValue;
        return this;
    }
    
    public UpdateCollateralValuationCommandBuilder haircut(BigDecimal haircut) {
        this.haircut = haircut;
        return this;
    }
    
    public UpdateCollateralValuationCommandBuilder riskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
        return this;
    }
    
    public UpdateCollateralValuationCommandBuilder valuatedBy(String valuatedBy) {
        this.valuatedBy = valuatedBy;
        return this;
    }
    
    public UpdateCollateralValuationCommandBuilder expirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }
    
    public UpdateCollateralValuationCommand build() {
        UpdateCollateralValuationCommand command = new UpdateCollateralValuationCommand();
        command.setValuationId(valuationId);
        command.setEstimatedValue(estimatedValue);
        command.setHaircut(haircut);
        command.setRiskLevel(riskLevel);
        command.setValuatedBy(valuatedBy);
        command.setExpirationDate(expirationDate);
        return command;
    }
}
