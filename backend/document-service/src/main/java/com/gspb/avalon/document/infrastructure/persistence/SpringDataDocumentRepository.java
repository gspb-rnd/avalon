package com.gspb.avalon.document.infrastructure.persistence;

import com.gspb.avalon.document.domain.model.DocumentStatus;
import com.gspb.avalon.document.domain.model.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Spring Data JPA repository for documents.
 */
@Repository
public interface SpringDataDocumentRepository extends JpaRepository<DocumentJpaEntity, UUID> {
    
    /**
     * Finds documents by package ID.
     *
     * @param packageId The package ID
     * @return A list of document JPA entities for the package
     */
    List<DocumentJpaEntity> findByPackageId(UUID packageId);
    
    /**
     * Finds documents by status.
     *
     * @param status The status
     * @return A list of document JPA entities with the given status
     */
    List<DocumentJpaEntity> findByStatus(DocumentStatus status);
    
    /**
     * Finds documents by type.
     *
     * @param type The document type
     * @return A list of document JPA entities with the given type
     */
    List<DocumentJpaEntity> findByType(DocumentType type);
    
    /**
     * Finds documents by package ID and status.
     *
     * @param packageId The package ID
     * @param status The status
     * @return A list of document JPA entities for the package with the given status
     */
    List<DocumentJpaEntity> findByPackageIdAndStatus(UUID packageId, DocumentStatus status);
    
    /**
     * Finds documents by package ID and type.
     *
     * @param packageId The package ID
     * @param type The document type
     * @return A list of document JPA entities for the package with the given type
     */
    List<DocumentJpaEntity> findByPackageIdAndType(UUID packageId, DocumentType type);
}
