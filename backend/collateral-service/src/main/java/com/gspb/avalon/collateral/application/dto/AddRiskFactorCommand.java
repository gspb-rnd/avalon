package com.gspb.avalon.collateral.application.dto;

import com.gspb.avalon.collateral.domain.model.RiskLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Command for adding a risk factor to a collateral valuation.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class AddRiskFactorCommand {
    
    @NotNull(message = "Valuation ID is required")
    private UUID valuationId;
    
    @NotBlank(message = "Factor name is required")
    private String factorName;
    
    @NotBlank(message = "Description is required")
    private String description;
    
    @NotNull(message = "Impact level is required")
    private RiskLevel impact;
    
    public UUID getValuationId() {
        return valuationId;
    }
    
    public void setValuationId(UUID valuationId) {
        this.valuationId = valuationId;
    }
    
    public String getFactorName() {
        return factorName;
    }
    
    public void setFactorName(String factorName) {
        this.factorName = factorName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public RiskLevel getImpact() {
        return impact;
    }
    
    public void setImpact(RiskLevel impact) {
        this.impact = impact;
    }
    
    public static AddRiskFactorCommandBuilder builder() {
        return new AddRiskFactorCommandBuilder();
    }
}
