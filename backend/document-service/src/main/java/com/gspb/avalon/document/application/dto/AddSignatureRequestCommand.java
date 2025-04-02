package com.gspb.avalon.document.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Command for adding a signature request to a document.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddSignatureRequestCommand {
    
    @NotNull(message = "Document ID is required")
    private UUID documentId;
    
    @NotBlank(message = "Recipient email is required")
    @Email(message = "Invalid email format")
    private String recipientEmail;
    
    @NotBlank(message = "Recipient name is required")
    private String recipientName;
    
    @NotNull(message = "Expiration date is required")
    @Future(message = "Expiration date must be in the future")
    private LocalDateTime expiresAt;
}
