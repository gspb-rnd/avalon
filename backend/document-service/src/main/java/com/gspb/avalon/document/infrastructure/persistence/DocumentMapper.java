package com.gspb.avalon.document.infrastructure.persistence;

import com.gspb.avalon.document.domain.model.Document;
import com.gspb.avalon.document.domain.model.SignatureRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Mapper between domain model and JPA entities for documents.
 */
@Component
public class DocumentMapper {
    
    /**
     * Maps a document domain model to a JPA entity.
     *
     * @param document The document domain model
     * @return The document JPA entity
     */
    public DocumentJpaEntity toJpaEntity(Document document) {
        DocumentJpaEntity entity = DocumentJpaEntity.builder()
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
                .build();
        
        entity.setSignatureRequests(mapSignatureRequests(document.getSignatureRequests(), entity));
        
        return entity;
    }
    
    /**
     * Maps a document JPA entity to a domain model.
     *
     * @param entity The document JPA entity
     * @return The document domain model
     */
    public Document toDomainModel(DocumentJpaEntity entity) {
        Document document = new Document(
                entity.getId(),
                entity.getPackageId(),
                entity.getType(),
                entity.getName(),
                entity.getCreatedBy()
        );
        
        try {
            java.lang.reflect.Field statusField = Document.class.getDeclaredField("status");
            statusField.setAccessible(true);
            statusField.set(document, entity.getStatus());
            
            java.lang.reflect.Field contentUrlField = Document.class.getDeclaredField("contentUrl");
            contentUrlField.setAccessible(true);
            contentUrlField.set(document, entity.getContentUrl());
            
            java.lang.reflect.Field signedUrlField = Document.class.getDeclaredField("signedUrl");
            signedUrlField.setAccessible(true);
            signedUrlField.set(document, entity.getSignedUrl());
            
            java.lang.reflect.Field createdAtField = Document.class.getDeclaredField("createdAt");
            createdAtField.setAccessible(true);
            createdAtField.set(document, entity.getCreatedAt());
            
            java.lang.reflect.Field updatedAtField = Document.class.getDeclaredField("updatedAt");
            updatedAtField.setAccessible(true);
            updatedAtField.set(document, entity.getUpdatedAt());
            
            java.lang.reflect.Field signatureRequestsField = Document.class.getDeclaredField("signatureRequests");
            signatureRequestsField.setAccessible(true);
            List<SignatureRequest> signatureRequests = entity.getSignatureRequests().stream()
                    .map(this::mapSignatureRequest)
                    .collect(Collectors.toList());
            ((List<SignatureRequest>) signatureRequestsField.get(document)).addAll(signatureRequests);
            
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Error mapping document entity to domain model", e);
        }
        
        return document;
    }
    
    /**
     * Maps signature request domain models to JPA entities.
     *
     * @param signatureRequests The signature request domain models
     * @param documentEntity The document JPA entity
     * @return The signature request JPA entities
     */
    private List<SignatureRequestJpaEntity> mapSignatureRequests(List<SignatureRequest> signatureRequests, DocumentJpaEntity documentEntity) {
        return signatureRequests.stream()
                .map(request -> SignatureRequestJpaEntity.builder()
                        .id(request.getId())
                        .document(documentEntity)
                        .recipientEmail(request.getRecipientEmail())
                        .recipientName(request.getRecipientName())
                        .sentAt(request.getSentAt())
                        .completedAt(request.getCompletedAt())
                        .expiresAt(request.getExpiresAt())
                        .status(request.getStatus())
                        .signatureUrl(request.getSignatureUrl())
                        .ipAddress(request.getIpAddress())
                        .build())
                .collect(Collectors.toList());
    }
    
    /**
     * Maps a signature request JPA entity to a domain model.
     *
     * @param entity The signature request JPA entity
     * @return The signature request domain model
     */
    private SignatureRequest mapSignatureRequest(SignatureRequestJpaEntity entity) {
        SignatureRequest signatureRequest = new SignatureRequest(
                entity.getId(),
                entity.getDocument().getId(),
                entity.getRecipientEmail(),
                entity.getRecipientName(),
                entity.getExpiresAt()
        );
        
        try {
            java.lang.reflect.Field statusField = SignatureRequest.class.getDeclaredField("status");
            statusField.setAccessible(true);
            statusField.set(signatureRequest, entity.getStatus());
            
            java.lang.reflect.Field completedAtField = SignatureRequest.class.getDeclaredField("completedAt");
            completedAtField.setAccessible(true);
            completedAtField.set(signatureRequest, entity.getCompletedAt());
            
            java.lang.reflect.Field signatureUrlField = SignatureRequest.class.getDeclaredField("signatureUrl");
            signatureUrlField.setAccessible(true);
            signatureUrlField.set(signatureRequest, entity.getSignatureUrl());
            
            java.lang.reflect.Field ipAddressField = SignatureRequest.class.getDeclaredField("ipAddress");
            ipAddressField.setAccessible(true);
            ipAddressField.set(signatureRequest, entity.getIpAddress());
            
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Error mapping signature request entity to domain model", e);
        }
        
        return signatureRequest;
    }
}
