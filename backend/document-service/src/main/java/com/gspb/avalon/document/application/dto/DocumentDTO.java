package com.gspb.avalon.document.application.dto;

import com.gspb.avalon.document.domain.model.DocumentStatus;
import com.gspb.avalon.document.domain.model.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Data Transfer Object for Document.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDTO {
    
    private UUID id;
    private UUID packageId;
    private DocumentType type;
    private String name;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private DocumentStatus status;
    private String contentUrl;
    private String signedUrl;
    private List<SignatureRequestDTO> signatureRequests = new ArrayList<>();
    private boolean allSignatureRequestsCompleted;
    private boolean anySignatureRequestDeclined;
    private boolean anySignatureRequestExpired;
}
