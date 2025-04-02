package com.gspb.avalon.document.application.service;

import com.gspb.avalon.document.application.dto.*;
import com.gspb.avalon.document.domain.model.Document;
import com.gspb.avalon.document.domain.model.DocumentPackage;
import com.gspb.avalon.document.domain.model.SignatureRequest;
import com.gspb.avalon.document.domain.repository.DocumentPackageRepository;
import com.gspb.avalon.document.domain.repository.DocumentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Application service for documents.
 */
@Service
@RequiredArgsConstructor
public class DocumentApplicationService {
    
    private final DocumentRepository documentRepository;
    private final DocumentPackageRepository documentPackageRepository;
    private final DocumentDTOMapper mapper;
    private final DocumentGenerationService documentGenerationService;
    
    /**
     * Creates a new document package.
     *
     * @param command The create document package command
     * @return The created document package DTO
     */
    @Transactional
    public DocumentPackageDTO createDocumentPackage(CreateDocumentPackageCommand command) {
        DocumentPackage documentPackage = new DocumentPackage(
                UUID.randomUUID(),
                command.getLoanApplicationId(),
                command.getName(),
                command.getCreatedBy()
        );
        
        DocumentPackage savedDocumentPackage = documentPackageRepository.save(documentPackage);
        return mapper.toDTO(savedDocumentPackage);
    }
    
    /**
     * Creates a new document.
     *
     * @param command The create document command
     * @return The created document DTO
     */
    @Transactional
    public DocumentDTO createDocument(CreateDocumentCommand command) {
        DocumentPackage documentPackage = findDocumentPackageById(command.getPackageId());
        
        Document document = new Document(
                UUID.randomUUID(),
                command.getPackageId(),
                command.getType(),
                command.getName(),
                command.getCreatedBy()
        );
        
        Document savedDocument = documentRepository.save(document);
        
        documentPackage.addDocument(savedDocument.getId());
        documentPackageRepository.save(documentPackage);
        
        return mapper.toDTO(savedDocument);
    }
    
    /**
     * Generates a document.
     *
     * @param command The generate document command
     * @return The updated document DTO
     */
    @Transactional
    public DocumentDTO generateDocument(GenerateDocumentCommand command) {
        Document document = findDocumentById(command.getDocumentId());
        
        document.generate(command.getContentUrl(), command.getGeneratedBy());
        
        Document savedDocument = documentRepository.save(document);
        return mapper.toDTO(savedDocument);
    }
    
    /**
     * Adds a signature request to a document.
     *
     * @param command The add signature request command
     * @return The updated document DTO
     */
    @Transactional
    public DocumentDTO addSignatureRequest(AddSignatureRequestCommand command) {
        Document document = findDocumentById(command.getDocumentId());
        
        document.addSignatureRequest(
                command.getRecipientEmail(),
                command.getRecipientName(),
                command.getExpiresAt()
        );
        
        Document savedDocument = documentRepository.save(document);
        return mapper.toDTO(savedDocument);
    }
    
    /**
     * Sends a document for signature.
     *
     * @param documentId The document ID
     * @param sentBy The user who sent the document for signature
     * @return The updated document DTO
     */
    @Transactional
    public DocumentDTO sendForSignature(UUID documentId, String sentBy) {
        Document document = findDocumentById(documentId);
        
        document.sendForSignature(sentBy);
        
        Document savedDocument = documentRepository.save(document);
        return mapper.toDTO(savedDocument);
    }
    
    /**
     * Completes a signature request.
     *
     * @param command The complete signature request command
     * @return The updated document DTO
     */
    @Transactional
    public DocumentDTO completeSignatureRequest(CompleteSignatureRequestCommand command) {
        Document document = findDocumentById(command.getDocumentId());
        
        SignatureRequest signatureRequest = document.getSignatureRequest(command.getSignatureRequestId());
        signatureRequest.complete(command.getSignatureUrl(), command.getIpAddress());
        
        Document savedDocument = documentRepository.save(document);
        return mapper.toDTO(savedDocument);
    }
    
    /**
     * Declines a signature request.
     *
     * @param documentId The document ID
     * @param signatureRequestId The signature request ID
     * @return The updated document DTO
     */
    @Transactional
    public DocumentDTO declineSignatureRequest(UUID documentId, UUID signatureRequestId) {
        Document document = findDocumentById(documentId);
        
        SignatureRequest signatureRequest = document.getSignatureRequest(signatureRequestId);
        signatureRequest.decline();
        
        Document savedDocument = documentRepository.save(document);
        return mapper.toDTO(savedDocument);
    }
    
    /**
     * Marks a document as signed.
     *
     * @param documentId The document ID
     * @param signedUrl The signed URL
     * @param signedBy The user who signed the document
     * @return The updated document DTO
     */
    @Transactional
    public DocumentDTO markAsSigned(UUID documentId, String signedUrl, String signedBy) {
        Document document = findDocumentById(documentId);
        
        document.markAsSigned(signedUrl, signedBy);
        
        Document savedDocument = documentRepository.save(document);
        return mapper.toDTO(savedDocument);
    }
    
    /**
     * Rejects a document.
     *
     * @param documentId The document ID
     * @param rejectedBy The user who rejected the document
     * @return The updated document DTO
     */
    @Transactional
    public DocumentDTO rejectDocument(UUID documentId, String rejectedBy) {
        Document document = findDocumentById(documentId);
        
        document.reject(rejectedBy);
        
        Document savedDocument = documentRepository.save(document);
        return mapper.toDTO(savedDocument);
    }
    
    /**
     * Archives a document.
     *
     * @param documentId The document ID
     * @param archivedBy The user who archived the document
     * @return The updated document DTO
     */
    @Transactional
    public DocumentDTO archiveDocument(UUID documentId, String archivedBy) {
        Document document = findDocumentById(documentId);
        
        document.archive(archivedBy);
        
        Document savedDocument = documentRepository.save(document);
        return mapper.toDTO(savedDocument);
    }
    
    /**
     * Gets a document by ID.
     *
     * @param documentId The document ID
     * @return The document DTO
     */
    @Transactional(readOnly = true)
    public DocumentDTO getDocument(UUID documentId) {
        Document document = findDocumentById(documentId);
        return mapper.toDTO(document);
    }
    
    /**
     * Gets a document package by ID.
     *
     * @param packageId The document package ID
     * @return The document package DTO
     */
    @Transactional(readOnly = true)
    public DocumentPackageDTO getDocumentPackage(UUID packageId) {
        DocumentPackage documentPackage = findDocumentPackageById(packageId);
        return mapper.toDTO(documentPackage);
    }
    
    /**
     * Gets documents by package ID.
     *
     * @param packageId The package ID
     * @return The document DTOs
     */
    @Transactional(readOnly = true)
    public List<DocumentDTO> getDocumentsByPackageId(UUID packageId) {
        List<Document> documents = documentRepository.findByPackageId(packageId);
        return documents.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Gets document packages by loan application ID.
     *
     * @param loanApplicationId The loan application ID
     * @return The document package DTOs
     */
    @Transactional(readOnly = true)
    public List<DocumentPackageDTO> getDocumentPackagesByLoanApplicationId(UUID loanApplicationId) {
        List<DocumentPackage> documentPackages = documentPackageRepository.findByLoanApplicationId(loanApplicationId);
        return documentPackages.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Finds a document by ID.
     *
     * @param documentId The document ID
     * @return The document
     * @throws EntityNotFoundException if the document is not found
     */
    private Document findDocumentById(UUID documentId) {
        return documentRepository.findById(documentId)
                .orElseThrow(() -> new EntityNotFoundException("Document not found: " + documentId));
    }
    
    /**
     * Finds a document package by ID.
     *
     * @param packageId The document package ID
     * @return The document package
     * @throws EntityNotFoundException if the document package is not found
     */
    private DocumentPackage findDocumentPackageById(UUID packageId) {
        return documentPackageRepository.findById(packageId)
                .orElseThrow(() -> new EntityNotFoundException("Document package not found: " + packageId));
    }
}
