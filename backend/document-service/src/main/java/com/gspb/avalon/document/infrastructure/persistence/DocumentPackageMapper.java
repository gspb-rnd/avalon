package com.gspb.avalon.document.infrastructure.persistence;

import com.gspb.avalon.document.domain.model.DocumentPackage;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Mapper between domain model and JPA entities for document packages.
 */
@Component
public class DocumentPackageMapper {
    
    /**
     * Maps a document package domain model to a JPA entity.
     *
     * @param documentPackage The document package domain model
     * @return The document package JPA entity
     */
    public DocumentPackageJpaEntity toJpaEntity(DocumentPackage documentPackage) {
        return DocumentPackageJpaEntity.builder()
                .id(documentPackage.getId())
                .loanApplicationId(documentPackage.getLoanApplicationId())
                .name(documentPackage.getName())
                .createdBy(documentPackage.getCreatedBy())
                .createdAt(documentPackage.getCreatedAt())
                .updatedAt(documentPackage.getUpdatedAt())
                .documentIds(documentPackage.getDocumentIds())
                .build();
    }
    
    /**
     * Maps a document package JPA entity to a domain model.
     *
     * @param entity The document package JPA entity
     * @return The document package domain model
     */
    public DocumentPackage toDomainModel(DocumentPackageJpaEntity entity) {
        DocumentPackage documentPackage = new DocumentPackage(
                entity.getId(),
                entity.getLoanApplicationId(),
                entity.getName(),
                entity.getCreatedBy()
        );
        
        try {
            java.lang.reflect.Field createdAtField = DocumentPackage.class.getDeclaredField("createdAt");
            createdAtField.setAccessible(true);
            createdAtField.set(documentPackage, entity.getCreatedAt());
            
            java.lang.reflect.Field updatedAtField = DocumentPackage.class.getDeclaredField("updatedAt");
            updatedAtField.setAccessible(true);
            updatedAtField.set(documentPackage, entity.getUpdatedAt());
            
            java.lang.reflect.Field documentIdsField = DocumentPackage.class.getDeclaredField("documentIds");
            documentIdsField.setAccessible(true);
            ((java.util.List<UUID>) documentIdsField.get(documentPackage)).addAll(entity.getDocumentIds());
            
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Error mapping document package entity to domain model", e);
        }
        
        return documentPackage;
    }
}
