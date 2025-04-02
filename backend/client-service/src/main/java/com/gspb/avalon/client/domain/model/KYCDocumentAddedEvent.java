package com.gspb.avalon.client.domain.model;

import com.gspb.avalon.shared.domain.DomainEvent;

import java.util.UUID;

/**
 * Domain event that is triggered when a KYC document is added to a client.
 */
public class KYCDocumentAddedEvent extends DomainEvent {
    
    private final UUID clientId;
    private final UUID documentId;
    private final KYCDocumentType documentType;
    
    /**
     * Creates a new KYC document added event.
     *
     * @param clientId The client ID
     * @param documentId The document ID
     * @param documentType The document type
     */
    public KYCDocumentAddedEvent(UUID clientId, UUID documentId, KYCDocumentType documentType) {
        super();
        this.clientId = clientId;
        this.documentId = documentId;
        this.documentType = documentType;
    }
    
    /**
     * Gets the client ID.
     *
     * @return The client ID
     */
    public UUID getClientId() {
        return clientId;
    }
    
    /**
     * Gets the document ID.
     *
     * @return The document ID
     */
    public UUID getDocumentId() {
        return documentId;
    }
    
    /**
     * Gets the document type.
     *
     * @return The document type
     */
    public KYCDocumentType getDocumentType() {
        return documentType;
    }
}
