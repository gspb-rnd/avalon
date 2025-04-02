package com.gspb.avalon.document.domain.model;

import com.gspb.avalon.shared.domain.Entity;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity representing a signature request in the system.
 */
@Getter
public class SignatureRequest extends Entity<UUID> {
    
    private final UUID documentId;
    private final String recipientEmail;
    private final String recipientName;
    private final LocalDateTime sentAt;
    private LocalDateTime completedAt;
    private LocalDateTime expiresAt;
    private SignatureStatus status;
    private String signatureUrl;
    private String ipAddress;
    
    /**
     * Creates a new signature request.
     *
     * @param id The signature request ID
     * @param documentId The document ID
     * @param recipientEmail The recipient email
     * @param recipientName The recipient name
     * @param expiresAt The expiration date
     */
    public SignatureRequest(UUID id, UUID documentId, String recipientEmail, String recipientName, LocalDateTime expiresAt) {
        super(id);
        this.documentId = documentId;
        this.recipientEmail = recipientEmail;
        this.recipientName = recipientName;
        this.sentAt = LocalDateTime.now();
        this.expiresAt = expiresAt;
        this.status = SignatureStatus.PENDING;
    }
    
    /**
     * Completes the signature request.
     *
     * @param signatureUrl The signature URL
     * @param ipAddress The IP address
     */
    public void complete(String signatureUrl, String ipAddress) {
        if (this.status != SignatureStatus.PENDING) {
            throw new IllegalStateException("Signature request must be in PENDING status to be completed");
        }
        
        this.status = SignatureStatus.COMPLETED;
        this.completedAt = LocalDateTime.now();
        this.signatureUrl = signatureUrl;
        this.ipAddress = ipAddress;
    }
    
    /**
     * Declines the signature request.
     */
    public void decline() {
        if (this.status != SignatureStatus.PENDING) {
            throw new IllegalStateException("Signature request must be in PENDING status to be declined");
        }
        
        this.status = SignatureStatus.DECLINED;
        this.completedAt = LocalDateTime.now();
    }
    
    /**
     * Cancels the signature request.
     */
    public void cancel() {
        if (this.status != SignatureStatus.PENDING) {
            throw new IllegalStateException("Signature request must be in PENDING status to be cancelled");
        }
        
        this.status = SignatureStatus.CANCELLED;
        this.completedAt = LocalDateTime.now();
    }
    
    /**
     * Checks if the signature request has expired.
     *
     * @return True if the signature request has expired, false otherwise
     */
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(this.expiresAt);
    }
    
    /**
     * Marks the signature request as expired.
     */
    public void markAsExpired() {
        if (this.status != SignatureStatus.PENDING) {
            throw new IllegalStateException("Signature request must be in PENDING status to be marked as expired");
        }
        
        this.status = SignatureStatus.EXPIRED;
        this.completedAt = LocalDateTime.now();
    }
    
    /**
     * Extends the expiration date of the signature request.
     *
     * @param newExpiresAt The new expiration date
     */
    public void extendExpiration(LocalDateTime newExpiresAt) {
        if (this.status != SignatureStatus.PENDING) {
            throw new IllegalStateException("Signature request must be in PENDING status to extend expiration");
        }
        
        if (newExpiresAt.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("New expiration date must be in the future");
        }
        
        this.expiresAt = newExpiresAt;
    }
}
