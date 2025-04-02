package com.gspb.avalon.client.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Command for verifying a KYC document.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerifyKYCDocumentCommand {
    
    @NotNull(message = "Document ID is required")
    private UUID documentId;
    
    private boolean approved;
    private String notes;
}
