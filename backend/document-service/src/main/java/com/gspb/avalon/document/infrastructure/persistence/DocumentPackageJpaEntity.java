package com.gspb.avalon.document.infrastructure.persistence;

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
 * JPA entity for document package.
 */
@Entity
@Table(name = "document_packages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentPackageJpaEntity {
    
    @Id
    private UUID id;
    
    @Column(name = "loan_application_id", nullable = false)
    private UUID loanApplicationId;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "created_by", nullable = false)
    private String createdBy;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    
    @ElementCollection
    @CollectionTable(name = "document_package_documents", joinColumns = @JoinColumn(name = "package_id"))
    @Column(name = "document_id")
    private List<UUID> documentIds = new ArrayList<>();
}
