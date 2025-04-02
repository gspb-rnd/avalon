package com.gspb.avalon.document.domain.model;

import com.gspb.avalon.shared.domain.AggregateRoot;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Aggregate root representing a document package in the system.
 */
@Getter
public class DocumentPackage extends AggregateRoot<UUID> {
    
    private final UUID loanApplicationId;
    private final String name;
    private final String createdBy;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private final List<UUID> documentIds = new ArrayList<>();
    
    /**
     * Creates a new document package.
     *
     * @param id The document package ID
     * @param loanApplicationId The loan application ID
     * @param name The document package name
     * @param createdBy The user who created the document package
     */
    public DocumentPackage(UUID id, UUID loanApplicationId, String name, String createdBy) {
        super(id);
        this.loanApplicationId = loanApplicationId;
        this.name = name;
        this.createdBy = createdBy;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }
    
    /**
     * Adds a document to the package.
     *
     * @param documentId The document ID
     */
    public void addDocument(UUID documentId) {
        if (!this.documentIds.contains(documentId)) {
            this.documentIds.add(documentId);
            this.updatedAt = LocalDateTime.now();
        }
    }
    
    /**
     * Removes a document from the package.
     *
     * @param documentId The document ID
     */
    public void removeDocument(UUID documentId) {
        if (this.documentIds.remove(documentId)) {
            this.updatedAt = LocalDateTime.now();
        }
    }
    
    /**
     * Gets the document IDs in the package.
     *
     * @return An unmodifiable list of document IDs
     */
    public List<UUID> getDocumentIds() {
        return Collections.unmodifiableList(documentIds);
    }
    
    /**
     * Checks if the package contains a document.
     *
     * @param documentId The document ID
     * @return True if the package contains the document, false otherwise
     */
    public boolean containsDocument(UUID documentId) {
        return this.documentIds.contains(documentId);
    }
    
    /**
     * Gets the number of documents in the package.
     *
     * @return The number of documents
     */
    public int getDocumentCount() {
        return this.documentIds.size();
    }
}
