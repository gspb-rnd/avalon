package com.gspb.avalon.collateral.application.dto;

import com.gspb.avalon.collateral.domain.model.RiskLevel;

import java.util.UUID;

/**
 * Builder class for AddRiskFactorCommand.
 */
public class AddRiskFactorCommandBuilder {
    
    private UUID valuationId;
    private String factorName;
    private String description;
    private RiskLevel impact;
    
    public AddRiskFactorCommandBuilder valuationId(UUID valuationId) {
        this.valuationId = valuationId;
        return this;
    }
    
    public AddRiskFactorCommandBuilder factorName(String factorName) {
        this.factorName = factorName;
        return this;
    }
    
    public AddRiskFactorCommandBuilder description(String description) {
        this.description = description;
        return this;
    }
    
    public AddRiskFactorCommandBuilder impact(RiskLevel impact) {
        this.impact = impact;
        return this;
    }
    
    public AddRiskFactorCommand build() {
        AddRiskFactorCommand command = new AddRiskFactorCommand();
        command.setValuationId(valuationId);
        command.setFactorName(factorName);
        command.setDescription(description);
        command.setImpact(impact);
        return command;
    }
}
