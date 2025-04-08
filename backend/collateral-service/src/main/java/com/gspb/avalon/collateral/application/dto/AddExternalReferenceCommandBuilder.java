package com.gspb.avalon.collateral.application.dto;

import java.util.UUID;

/**
 * Builder class for AddExternalReferenceCommand.
 */
public class AddExternalReferenceCommandBuilder {
    
    private UUID valuationId;
    private String sourceSystem;
    private String referenceId;
    private String referenceUrl;
    
    public AddExternalReferenceCommandBuilder valuationId(UUID valuationId) {
        this.valuationId = valuationId;
        return this;
    }
    
    public AddExternalReferenceCommandBuilder sourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
        return this;
    }
    
    public AddExternalReferenceCommandBuilder referenceId(String referenceId) {
        this.referenceId = referenceId;
        return this;
    }
    
    public AddExternalReferenceCommandBuilder referenceUrl(String referenceUrl) {
        this.referenceUrl = referenceUrl;
        return this;
    }
    
    public AddExternalReferenceCommand build() {
        AddExternalReferenceCommand command = new AddExternalReferenceCommand();
        command.setValuationId(valuationId);
        command.setSourceSystem(sourceSystem);
        command.setReferenceId(referenceId);
        command.setReferenceUrl(referenceUrl);
        return command;
    }
}
