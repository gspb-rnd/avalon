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
@Builder
public class AddExternalReferenceCommand {
    
    @NotNull(message = "Valuation ID is required")
    private UUID valuationId;
    
    @NotBlank(message = "Source system is required")
    private String sourceSystem;
    
    @NotBlank(message = "Reference ID is required")
    private String referenceId;
    
    private String referenceUrl;
}
