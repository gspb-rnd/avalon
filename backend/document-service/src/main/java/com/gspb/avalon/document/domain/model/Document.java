package com.gspb.avalon.document.domain.model;

import com.gspb.avalon.shared.domain.AggregateRoot;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Aggregate root representing a document in the system.
 */
@Getter
public class Document extends AggregateRoot<UUID> {
    
    private final UUID packageId;
    private final DocumentType type;
    private final String name;
    private final String createdBy;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private DocumentStatus status;
    private String contentUrl;
    private String signedUrl;
    private final List<SignatureRequest> signatureRequests = new ArrayList<>();
    
    /**
     * Creates a new document.
     *
     * @param id The document ID
     * @param packageId The package ID
     * @param type The document type
     * @param name The document name
     * @param createdBy The user who created the document
     */
    public Document(UUID id, UUID packageId, DocumentType type, String name, String createdBy) {
        super(id);
        this.packageId = packageId;
        this.type = type;
        this.name = name;
        this.createdBy = createdBy;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
        this.status = DocumentStatus.DRAFT;
        
        registerEvent(new DocumentCreatedEvent(id, packageId, type, createdBy));
    }
    
    /**
     * Sets the content URL of the document.
     *
     * @param contentUrl The content URL
     */
    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
        this.updatedAt = LocalDateTime.now();
    }
    
    /**
     * Generates the document.
     *
     * @param contentUrl The content URL
     * @param generatedBy The user who generated the document
     */
    public void generate(String contentUrl, String generatedBy) {
        if (this.status != DocumentStatus.DRAFT) {
            throw new IllegalStateException("Document must be in DRAFT status to be generated");
        }
        
        DocumentStatus oldStatus = this.status;
        this.status = DocumentStatus.GENERATED;
        this.contentUrl = contentUrl;
        this.updatedAt = LocalDateTime.now();
        
        registerEvent(new DocumentStatusChangedEvent(this.getId(), oldStatus, this.status, generatedBy));
    }
    
    /**
     * Sends the document for signature.
     *
     * @param sentBy The user who sent the document for signature
     */
    public void sendForSignature(String sentBy) {
        if (this.status != DocumentStatus.GENERATED) {
            throw new IllegalStateException("Document must be in GENERATED status to be sent for signature");
        }
        
        if (this.signatureRequests.isEmpty()) {
            throw new IllegalStateException("Document must have at least one signature request to be sent for signature");
        }
        
        DocumentStatus oldStatus = this.status;
        this.status = DocumentStatus.PENDING_SIGNATURE;
        this.updatedAt = LocalDateTime.now();
        
        registerEvent(new DocumentStatusChangedEvent(this.getId(), oldStatus, this.status, sentBy));
    }
    
    /**
     * Adds a signature request to the document.
     *
     * @param recipientEmail The recipient email
     * @param recipientName The recipient name
     * @param expiresAt The expiration date
     * @return The signature request
     */
    public SignatureRequest addSignatureRequest(String recipientEmail, String recipientName, LocalDateTime expiresAt) {
        SignatureRequest signatureRequest = new SignatureRequest(
                UUID.randomUUID(),
                this.getId(),
                recipientEmail,
                recipientName,
                expiresAt
        );
        
        this.signatureRequests.add(signatureRequest);
        this.updatedAt = LocalDateTime.now();
        
        return signatureRequest;
    }
    
    /**
     * Marks the document as signed.
     *
     * @param signedUrl The signed URL
     * @param signedBy The user who signed the document
     */
    public void markAsSigned(String signedUrl, String signedBy) {
        if (this.status != DocumentStatus.PENDING_SIGNATURE) {
            throw new IllegalStateException("Document must be in PENDING_SIGNATURE status to be marked as signed");
        }
        
        boolean allCompleted = this.signatureRequests.stream()
                .allMatch(request -> request.getStatus() == SignatureStatus.COMPLETED);
        
        if (!allCompleted) {
            throw new IllegalStateException("All signature requests must be completed to mark the document as signed");
        }
        
        DocumentStatus oldStatus = this.status;
        this.status = DocumentStatus.SIGNED;
        this.signedUrl = signedUrl;
        this.updatedAt = LocalDateTime.now();
        
        registerEvent(new DocumentStatusChangedEvent(this.getId(), oldStatus, this.status, signedBy));
    }
    
    /**
     * Rejects the document.
     *
     * @param rejectedBy The user who rejected the document
     */
    public void reject(String rejectedBy) {
        if (this.status != DocumentStatus.PENDING_SIGNATURE) {
            throw new IllegalStateException("Document must be in PENDING_SIGNATURE status to be rejected");
        }
        
        DocumentStatus oldStatus = this.status;
        this.status = DocumentStatus.REJECTED;
        this.updatedAt = LocalDateTime.now();
        
        registerEvent(new DocumentStatusChangedEvent(this.getId(), oldStatus, this.status, rejectedBy));
    }
    
    /**
     * Archives the document.
     *
     * @param archivedBy The user who archived the document
     */
    public void archive(String archivedBy) {
        if (this.status == DocumentStatus.ARCHIVED) {
            throw new IllegalStateException("Document is already archived");
        }
        
        DocumentStatus oldStatus = this.status;
        this.status = DocumentStatus.ARCHIVED;
        this.updatedAt = LocalDateTime.now();
        
        registerEvent(new DocumentStatusChangedEvent(this.getId(), oldStatus, this.status, archivedBy));
    }
    
    /**
     * Gets the signature requests for the document.
     *
     * @return An unmodifiable list of signature requests
     */
    public List<SignatureRequest> getSignatureRequests() {
        return Collections.unmodifiableList(signatureRequests);
    }
    
    /**
     * Gets a signature request by ID.
     *
     * @param signatureRequestId The signature request ID
     * @return The signature request
     * @throws IllegalArgumentException if the signature request is not found
     */
    public SignatureRequest getSignatureRequest(UUID signatureRequestId) {
        return this.signatureRequests.stream()
                .filter(request -> request.getId().equals(signatureRequestId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Signature request not found: " + signatureRequestId));
    }
    
    /**
     * Checks if all signature requests are completed.
     *
     * @return True if all signature requests are completed, false otherwise
     */
    public boolean areAllSignatureRequestsCompleted() {
        if (this.signatureRequests.isEmpty()) {
            return false;
        }
        
        return this.signatureRequests.stream()
                .allMatch(request -> request.getStatus() == SignatureStatus.COMPLETED);
    }
    
    /**
     * Checks if any signature request is declined.
     *
     * @return True if any signature request is declined, false otherwise
     */
    public boolean isAnySignatureRequestDeclined() {
        return this.signatureRequests.stream()
                .anyMatch(request -> request.getStatus() == SignatureStatus.DECLINED);
    }
    
    /**
     * Checks if any signature request is expired.
     *
     * @return True if any signature request is expired, false otherwise
     */
    public boolean isAnySignatureRequestExpired() {
        return this.signatureRequests.stream()
                .anyMatch(request -> request.getStatus() == SignatureStatus.EXPIRED);
    }
}
