package com.gspb.avalon.collateral.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Command for adding an external reference to a collateral valuation.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class AddExternalReferenceCommand {
    
    @NotNull(message = "Valuation ID is required")
    private UUID valuationId;
    
    @NotBlank(message = "Source system is required")
    private String sourceSystem;
    
    @NotBlank(message = "Reference ID is required")
    private String referenceId;
    
    private String referenceUrl;
    
    public UUID getValuationId() {
        return valuationId;
    }
    
    public void setValuationId(UUID valuationId) {
        this.valuationId = valuationId;
    }
    
    public String getSourceSystem() {
        return sourceSystem;
    }
    
    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }
    
    public String getReferenceId() {
        return referenceId;
    }
    
    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }
    
    public String getReferenceUrl() {
        return referenceUrl;
    }
    
    public void setReferenceUrl(String referenceUrl) {
        this.referenceUrl = referenceUrl;
    }
    
    public static AddExternalReferenceCommandBuilder builder() {
        return new AddExternalReferenceCommandBuilder();
    }
}
