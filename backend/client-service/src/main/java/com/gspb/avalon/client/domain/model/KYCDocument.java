package com.gspb.avalon.client.domain.model;

import com.gspb.avalon.shared.domain.Entity;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity representing a KYC document submitted by a client.
 */
public class KYCDocument extends Entity<UUID> {
    
    private final UUID clientId;
    private final KYCDocumentType type;
    private final String documentNumber;
    private final LocalDateTime submissionDate;
    private final String fileUrl;
    private KYCDocumentStatus status;
    private String verificationNotes;
    
    /**
     * Creates a new KYC document.
     *
     * @param id The document ID
     * @param clientId The client ID
     * @param type The document type
     * @param documentNumber The document number
     * @param fileUrl The URL to the document file
     */
    public KYCDocument(UUID id, UUID clientId, KYCDocumentType type, String documentNumber, String fileUrl) {
        super(id);
        this.clientId = clientId;
        this.type = type;
        this.documentNumber = documentNumber;
        this.submissionDate = LocalDateTime.now();
        this.fileUrl = fileUrl;
        this.status = KYCDocumentStatus.PENDING;
    }
    
    /**
     * Verifies the document.
     *
     * @param notes Verification notes
     */
    public void verify(String notes) {
        this.status = KYCDocumentStatus.VERIFIED;
        this.verificationNotes = notes;
    }
    
    /**
     * Rejects the document.
     *
     * @param notes Rejection notes
     */
    public void reject(String notes) {
        this.status = KYCDocumentStatus.REJECTED;
        this.verificationNotes = notes;
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
     * Gets the document type.
     *
     * @return The document type
     */
    public KYCDocumentType getType() {
        return type;
    }
    
    /**
     * Gets the document number.
     *
     * @return The document number
     */
    public String getDocumentNumber() {
        return documentNumber;
    }
    
    /**
     * Gets the submission date.
     *
     * @return The submission date
     */
    public LocalDateTime getSubmissionDate() {
        return submissionDate;
    }
    
    /**
     * Gets the file URL.
     *
     * @return The file URL
     */
    public String getFileUrl() {
        return fileUrl;
    }
    
    /**
     * Gets the document status.
     *
     * @return The document status
     */
    public KYCDocumentStatus getStatus() {
        return status;
    }
    
    /**
     * Gets the verification notes.
     *
     * @return The verification notes
     */
    public String getVerificationNotes() {
        return verificationNotes;
    }
}
