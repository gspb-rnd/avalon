package com.gspb.avalon.document.domain.repository;

import com.gspb.avalon.document.domain.model.Document;
import com.gspb.avalon.document.domain.model.DocumentStatus;
import com.gspb.avalon.document.domain.model.DocumentType;
import com.gspb.avalon.shared.domain.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for Document aggregate root.
 */
public interface DocumentRepository extends Repository<Document, UUID> {
    
    /**
     * Finds documents by package ID.
     *
     * @param packageId The package ID
     * @return A list of documents for the package
     */
    List<Document> findByPackageId(UUID packageId);
    
    /**
     * Finds documents by status.
     *
     * @param status The status
     * @return A list of documents with the given status
     */
    List<Document> findByStatus(DocumentStatus status);
    
    /**
     * Finds documents by type.
     *
     * @param type The document type
     * @return A list of documents with the given type
     */
    List<Document> findByType(DocumentType type);
    
    /**
     * Finds documents by package ID and status.
     *
     * @param packageId The package ID
     * @param status The status
     * @return A list of documents for the package with the given status
     */
    List<Document> findByPackageIdAndStatus(UUID packageId, DocumentStatus status);
    
    /**
     * Finds documents by package ID and type.
     *
     * @param packageId The package ID
     * @param type The document type
     * @return A list of documents for the package with the given type
     */
    List<Document> findByPackageIdAndType(UUID packageId, DocumentType type);
}
