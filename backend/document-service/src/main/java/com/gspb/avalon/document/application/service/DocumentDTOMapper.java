package com.gspb.avalon.document.application.service;

import com.gspb.avalon.document.application.dto.DocumentDTO;
import com.gspb.avalon.document.application.dto.DocumentPackageDTO;
import com.gspb.avalon.document.application.dto.SignatureRequestDTO;
import com.gspb.avalon.document.domain.model.Document;
import com.gspb.avalon.document.domain.model.DocumentPackage;
import com.gspb.avalon.document.domain.model.SignatureRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper between domain model and DTOs for documents.
 */
@Component
public class DocumentDTOMapper {
    
    /**
     * Maps a document domain model to a DTO.
     *
     * @param document The document domain model
     * @return The document DTO
     */
    public DocumentDTO toDTO(Document document) {
        DocumentDTO dto = DocumentDTO.builder()
                .id(document.getId())
                .packageId(document.getPackageId())
                .type(document.getType())
                .name(document.getName())
                .createdBy(document.getCreatedBy())
                .createdAt(document.getCreatedAt())
                .updatedAt(document.getUpdatedAt())
                .status(document.getStatus())
                .contentUrl(document.getContentUrl())
                .signedUrl(document.getSignedUrl())
                .allSignatureRequestsCompleted(document.areAllSignatureRequestsCompleted())
                .anySignatureRequestDeclined(document.isAnySignatureRequestDeclined())
                .anySignatureRequestExpired(document.isAnySignatureRequestExpired())
                .build();
        
        dto.setSignatureRequests(mapSignatureRequests(document.getSignatureRequests()));
        
        return dto;
    }
    
    /**
     * Maps a document package domain model to a DTO.
     *
     * @param documentPackage The document package domain model
     * @return The document package DTO
     */
    public DocumentPackageDTO toDTO(DocumentPackage documentPackage) {
        return DocumentPackageDTO.builder()
                .id(documentPackage.getId())
                .loanApplicationId(documentPackage.getLoanApplicationId())
                .name(documentPackage.getName())
                .createdBy(documentPackage.getCreatedBy())
                .createdAt(documentPackage.getCreatedAt())
                .updatedAt(documentPackage.getUpdatedAt())
                .documentIds(documentPackage.getDocumentIds())
                .documentCount(documentPackage.getDocumentCount())
                .build();
    }
    
    /**
     * Maps signature request domain models to DTOs.
     *
     * @param signatureRequests The signature request domain models
     * @return The signature request DTOs
     */
    private List<SignatureRequestDTO> mapSignatureRequests(List<SignatureRequest> signatureRequests) {
        return signatureRequests.stream()
                .map(this::mapSignatureRequest)
                .collect(Collectors.toList());
    }
    
    /**
     * Maps a signature request domain model to a DTO.
     *
     * @param signatureRequest The signature request domain model
     * @return The signature request DTO
     */
    private SignatureRequestDTO mapSignatureRequest(SignatureRequest signatureRequest) {
        return SignatureRequestDTO.builder()
                .id(signatureRequest.getId())
                .documentId(signatureRequest.getDocumentId())
                .recipientEmail(signatureRequest.getRecipientEmail())
                .recipientName(signatureRequest.getRecipientName())
                .sentAt(signatureRequest.getSentAt())
                .completedAt(signatureRequest.getCompletedAt())
                .expiresAt(signatureRequest.getExpiresAt())
                .status(signatureRequest.getStatus())
                .signatureUrl(signatureRequest.getSignatureUrl())
                .ipAddress(signatureRequest.getIpAddress())
                .expired(signatureRequest.isExpired())
                .build();
    }
}
