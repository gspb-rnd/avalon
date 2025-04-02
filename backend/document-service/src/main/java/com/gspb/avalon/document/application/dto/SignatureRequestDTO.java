package com.gspb.avalon.document.application.dto;

import com.gspb.avalon.document.domain.model.SignatureStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Data Transfer Object for SignatureRequest.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignatureRequestDTO {
    
    private UUID id;
    private UUID documentId;
    private String recipientEmail;
    private String recipientName;
    private LocalDateTime sentAt;
    private LocalDateTime completedAt;
    private LocalDateTime expiresAt;
    private SignatureStatus status;
    private String signatureUrl;
    private String ipAddress;
    private boolean expired;
}
