package com.gspb.avalon.document.domain.model;

import com.gspb.avalon.shared.domain.DomainEvent;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Domain event representing a document creation.
 */
@Getter
public class DocumentCreatedEvent extends DomainEvent {
    
    private final UUID documentId;
    private final UUID packageId;
    private final DocumentType documentType;
    private final String createdBy;
    
    /**
     * Creates a new document created event.
     *
     * @param documentId The document ID
     * @param packageId The package ID
     * @param documentType The document type
     * @param createdBy The user who created the document
     */
    public DocumentCreatedEvent(UUID documentId, UUID packageId, DocumentType documentType, String createdBy) {
        super();
        this.documentId = documentId;
        this.packageId = packageId;
        this.documentType = documentType;
        this.createdBy = createdBy;
    }
}
