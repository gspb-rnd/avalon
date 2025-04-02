package com.gspb.avalon.document.presentation.rest;

import com.gspb.avalon.document.application.dto.*;
import com.gspb.avalon.document.application.service.DocumentApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST controller for documents.
 */
@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class DocumentController {
    
    private final DocumentApplicationService documentService;
    
    /**
     * Creates a new document package.
     *
     * @param command The create document package command
     * @return The created document package DTO
     */
    @PostMapping("/packages")
    public ResponseEntity<DocumentPackageDTO> createDocumentPackage(@Valid @RequestBody CreateDocumentPackageCommand command) {
        DocumentPackageDTO documentPackageDTO = documentService.createDocumentPackage(command);
        return new ResponseEntity<>(documentPackageDTO, HttpStatus.CREATED);
    }
    
    /**
     * Gets a document package by ID.
     *
     * @param id The document package ID
     * @return The document package DTO
     */
    @GetMapping("/packages/{id}")
    public ResponseEntity<DocumentPackageDTO> getDocumentPackage(@PathVariable UUID id) {
        DocumentPackageDTO documentPackageDTO = documentService.getDocumentPackage(id);
        return ResponseEntity.ok(documentPackageDTO);
    }
    
    /**
     * Gets document packages by loan application ID.
     *
     * @param loanApplicationId The loan application ID
     * @return The document package DTOs
     */
    @GetMapping("/packages/loan-application/{loanApplicationId}")
    public ResponseEntity<List<DocumentPackageDTO>> getDocumentPackagesByLoanApplicationId(@PathVariable UUID loanApplicationId) {
        List<DocumentPackageDTO> documentPackageDTOs = documentService.getDocumentPackagesByLoanApplicationId(loanApplicationId);
        return ResponseEntity.ok(documentPackageDTOs);
    }
    
    /**
     * Creates a new document.
     *
     * @param command The create document command
     * @return The created document DTO
     */
    @PostMapping
    public ResponseEntity<DocumentDTO> createDocument(@Valid @RequestBody CreateDocumentCommand command) {
        DocumentDTO documentDTO = documentService.createDocument(command);
        return new ResponseEntity<>(documentDTO, HttpStatus.CREATED);
    }
    
    /**
     * Gets a document by ID.
     *
     * @param id The document ID
     * @return The document DTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<DocumentDTO> getDocument(@PathVariable UUID id) {
        DocumentDTO documentDTO = documentService.getDocument(id);
        return ResponseEntity.ok(documentDTO);
    }
    
    /**
     * Gets documents by package ID.
     *
     * @param packageId The package ID
     * @return The document DTOs
     */
    @GetMapping("/package/{packageId}")
    public ResponseEntity<List<DocumentDTO>> getDocumentsByPackageId(@PathVariable UUID packageId) {
        List<DocumentDTO> documentDTOs = documentService.getDocumentsByPackageId(packageId);
        return ResponseEntity.ok(documentDTOs);
    }
    
    /**
     * Generates a document.
     *
     * @param command The generate document command
     * @return The updated document DTO
     */
    @PutMapping("/generate")
    public ResponseEntity<DocumentDTO> generateDocument(@Valid @RequestBody GenerateDocumentCommand command) {
        DocumentDTO documentDTO = documentService.generateDocument(command);
        return ResponseEntity.ok(documentDTO);
    }
    
    /**
     * Adds a signature request to a document.
     *
     * @param command The add signature request command
     * @return The updated document DTO
     */
    @PostMapping("/signature-requests")
    public ResponseEntity<DocumentDTO> addSignatureRequest(@Valid @RequestBody AddSignatureRequestCommand command) {
        DocumentDTO documentDTO = documentService.addSignatureRequest(command);
        return ResponseEntity.ok(documentDTO);
    }
    
    /**
     * Sends a document for signature.
     *
     * @param id The document ID
     * @param sentBy The user who sent the document for signature
     * @return The updated document DTO
     */
    @PutMapping("/{id}/send-for-signature")
    public ResponseEntity<DocumentDTO> sendForSignature(@PathVariable UUID id, @RequestParam String sentBy) {
        DocumentDTO documentDTO = documentService.sendForSignature(id, sentBy);
        return ResponseEntity.ok(documentDTO);
    }
    
    /**
     * Completes a signature request.
     *
     * @param command The complete signature request command
     * @return The updated document DTO
     */
    @PutMapping("/signature-requests/complete")
    public ResponseEntity<DocumentDTO> completeSignatureRequest(@Valid @RequestBody CompleteSignatureRequestCommand command) {
        DocumentDTO documentDTO = documentService.completeSignatureRequest(command);
        return ResponseEntity.ok(documentDTO);
    }
    
    /**
     * Declines a signature request.
     *
     * @param documentId The document ID
     * @param signatureRequestId The signature request ID
     * @return The updated document DTO
     */
    @PutMapping("/{documentId}/signature-requests/{signatureRequestId}/decline")
    public ResponseEntity<DocumentDTO> declineSignatureRequest(
            @PathVariable UUID documentId,
            @PathVariable UUID signatureRequestId) {
        DocumentDTO documentDTO = documentService.declineSignatureRequest(documentId, signatureRequestId);
        return ResponseEntity.ok(documentDTO);
    }
    
    /**
     * Marks a document as signed.
     *
     * @param id The document ID
     * @param signedUrl The signed URL
     * @param signedBy The user who signed the document
     * @return The updated document DTO
     */
    @PutMapping("/{id}/mark-as-signed")
    public ResponseEntity<DocumentDTO> markAsSigned(
            @PathVariable UUID id,
            @RequestParam String signedUrl,
            @RequestParam String signedBy) {
        DocumentDTO documentDTO = documentService.markAsSigned(id, signedUrl, signedBy);
        return ResponseEntity.ok(documentDTO);
    }
    
    /**
     * Rejects a document.
     *
     * @param id The document ID
     * @param rejectedBy The user who rejected the document
     * @return The updated document DTO
     */
    @PutMapping("/{id}/reject")
    public ResponseEntity<DocumentDTO> rejectDocument(
            @PathVariable UUID id,
            @RequestParam String rejectedBy) {
        DocumentDTO documentDTO = documentService.rejectDocument(id, rejectedBy);
        return ResponseEntity.ok(documentDTO);
    }
    
    /**
     * Archives a document.
     *
     * @param id The document ID
     * @param archivedBy The user who archived the document
     * @return The updated document DTO
     */
    @PutMapping("/{id}/archive")
    public ResponseEntity<DocumentDTO> archiveDocument(
            @PathVariable UUID id,
            @RequestParam String archivedBy) {
        DocumentDTO documentDTO = documentService.archiveDocument(id, archivedBy);
        return ResponseEntity.ok(documentDTO);
    }
}
