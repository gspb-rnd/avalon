package com.gspb.avalon.client.infrastructure.persistence;

import com.gspb.avalon.client.domain.model.KYCDocumentStatus;
import com.gspb.avalon.client.domain.model.KYCDocumentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * JPA entity for KYCDocument.
 */
@Entity
@Table(name = "kyc_documents")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KYCDocumentJpaEntity {
    
    @Id
    private UUID id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private ClientJpaEntity client;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private KYCDocumentType type;
    
    @Column(nullable = false)
    private String documentNumber;
    
    @Column(nullable = false)
    private LocalDateTime submissionDate;
    
    @Column(nullable = false)
    private String fileUrl;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private KYCDocumentStatus status;
    
    @Column
    private String verificationNotes;
}
