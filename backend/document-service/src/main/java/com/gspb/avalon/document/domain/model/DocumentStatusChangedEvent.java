package com.gspb.avalon.document.domain.model;

import com.gspb.avalon.shared.domain.DomainEvent;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Domain event representing a document status change.
 */
@Getter
public class DocumentStatusChangedEvent extends DomainEvent {
    
    private final UUID documentId;
    private final DocumentStatus oldStatus;
    private final DocumentStatus newStatus;
    private final String changedBy;
    
    /**
     * Creates a new document status changed event.
     *
     * @param documentId The document ID
     * @param oldStatus The old status
     * @param newStatus The new status
     * @param changedBy The user who changed the status
     */
    public DocumentStatusChangedEvent(UUID documentId, DocumentStatus oldStatus, DocumentStatus newStatus, String changedBy) {
        super(UUID.randomUUID(), LocalDateTime.now());
        this.documentId = documentId;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
        this.changedBy = changedBy;
    }
}
