package com.gspb.avalon.client.application.dto;

import com.gspb.avalon.client.domain.model.KYCDocumentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Command for adding a KYC document to a client.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddKYCDocumentCommand {
    
    @NotNull(message = "Client ID is required")
    private UUID clientId;
    
    @NotNull(message = "Document type is required")
    private KYCDocumentType type;
    
    @NotBlank(message = "Document number is required")
    private String documentNumber;
    
    @NotBlank(message = "File URL is required")
    private String fileUrl;
}
