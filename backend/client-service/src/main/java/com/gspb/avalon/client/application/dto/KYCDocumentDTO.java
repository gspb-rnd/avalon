package com.gspb.avalon.client.application.dto;

import com.gspb.avalon.client.domain.model.KYCDocumentStatus;
import com.gspb.avalon.client.domain.model.KYCDocumentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Data Transfer Object for KYCDocument.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KYCDocumentDTO {
    
    private UUID id;
    private UUID clientId;
    private KYCDocumentType type;
    private String documentNumber;
    private LocalDateTime submissionDate;
    private String fileUrl;
    private KYCDocumentStatus status;
    private String verificationNotes;
}
