package com.gspb.avalon.document.infrastructure.persistence;

import com.gspb.avalon.document.domain.model.SignatureStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * JPA entity for signature request.
 */
@Entity
@Table(name = "signature_requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignatureRequestJpaEntity {
    
    @Id
    private UUID id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id", nullable = false)
    private DocumentJpaEntity document;
    
    @Column(name = "recipient_email", nullable = false)
    private String recipientEmail;
    
    @Column(name = "recipient_name", nullable = false)
    private String recipientName;
    
    @Column(name = "sent_at", nullable = false)
    private LocalDateTime sentAt;
    
    @Column(name = "completed_at")
    private LocalDateTime completedAt;
    
    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private SignatureStatus status;
    
    @Column(name = "signature_url")
    private String signatureUrl;
    
    @Column(name = "ip_address")
    private String ipAddress;
}
