package com.gspb.avalon.document.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Command for completing a signature request.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompleteSignatureRequestCommand {
    
    @NotNull(message = "Document ID is required")
    private UUID documentId;
    
    @NotNull(message = "Signature request ID is required")
    private UUID signatureRequestId;
    
    @NotBlank(message = "Signature URL is required")
    private String signatureUrl;
    
    @NotBlank(message = "IP address is required")
    private String ipAddress;
}
