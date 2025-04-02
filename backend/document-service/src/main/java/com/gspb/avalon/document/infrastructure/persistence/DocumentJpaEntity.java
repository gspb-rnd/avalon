package com.gspb.avalon.document.infrastructure.persistence;

import com.gspb.avalon.document.domain.model.DocumentStatus;
import com.gspb.avalon.document.domain.model.DocumentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * JPA entity for document.
 */
@Entity
@Table(name = "documents")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentJpaEntity {
    
    @Id
    private UUID id;
    
    @Column(name = "package_id", nullable = false)
    private UUID packageId;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private DocumentType type;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "created_by", nullable = false)
    private String createdBy;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private DocumentStatus status;
    
    @Column(name = "content_url")
    private String contentUrl;
    
    @Column(name = "signed_url")
    private String signedUrl;
    
    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SignatureRequestJpaEntity> signatureRequests = new ArrayList<>();
}
