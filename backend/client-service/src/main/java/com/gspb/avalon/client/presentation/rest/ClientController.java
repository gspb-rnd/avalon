package com.gspb.avalon.client.presentation.rest;

import com.gspb.avalon.client.application.dto.*;
import com.gspb.avalon.client.application.service.ClientApplicationService;
import com.gspb.avalon.client.domain.model.ClientStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST controller for client operations.
 */
@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {
    
    private final ClientApplicationService clientService;
    
    /**
     * Creates a new client.
     *
     * @param command The create client command
     * @return The created client DTO
     */
    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@Valid @RequestBody CreateClientCommand command) {
        ClientDTO client = clientService.createClient(command);
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }
    
    /**
     * Updates a client.
     *
     * @param id The client ID
     * @param command The update client command
     * @return The updated client DTO
     */
    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable UUID id, @Valid @RequestBody UpdateClientCommand command) {
        command.setId(id);
        ClientDTO client = clientService.updateClient(command);
        return ResponseEntity.ok(client);
    }
    
    /**
     * Gets a client by ID.
     *
     * @param id The client ID
     * @return The client DTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable UUID id) {
        ClientDTO client = clientService.getClient(id);
        return ResponseEntity.ok(client);
    }
    
    /**
     * Gets all clients.
     *
     * @return A list of client DTOs
     */
    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        List<ClientDTO> clients = clientService.getAllClients();
        return ResponseEntity.ok(clients);
    }
    
    /**
     * Adds a KYC document to a client.
     *
     * @param id The client ID
     * @param command The add KYC document command
     * @return The updated client DTO
     */
    @PostMapping("/{id}/kyc-documents")
    public ResponseEntity<ClientDTO> addKYCDocument(@PathVariable UUID id, @Valid @RequestBody AddKYCDocumentCommand command) {
        command.setClientId(id);
        ClientDTO client = clientService.addKYCDocument(command);
        return ResponseEntity.ok(client);
    }
    
    /**
     * Verifies a KYC document.
     *
     * @param id The client ID
     * @param documentId The document ID
     * @param command The verify KYC document command
     * @return The updated client DTO
     */
    @PutMapping("/{id}/kyc-documents/{documentId}/verify")
    public ResponseEntity<ClientDTO> verifyKYCDocument(
            @PathVariable UUID id,
            @PathVariable UUID documentId,
            @Valid @RequestBody VerifyKYCDocumentCommand command) {
        command.setDocumentId(documentId);
        ClientDTO client = clientService.verifyKYCDocument(command);
        return ResponseEntity.ok(client);
    }
    
    /**
     * Updates a client's status.
     *
     * @param id The client ID
     * @param status The new status
     * @return The updated client DTO
     */
    @PutMapping("/{id}/status")
    public ResponseEntity<ClientDTO> updateClientStatus(@PathVariable UUID id, @RequestParam ClientStatus status) {
        ClientDTO client = clientService.updateClientStatus(id, status);
        return ResponseEntity.ok(client);
    }
}
