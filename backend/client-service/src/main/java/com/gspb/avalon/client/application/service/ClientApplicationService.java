package com.gspb.avalon.client.application.service;

import com.gspb.avalon.client.application.dto.*;
import com.gspb.avalon.client.domain.model.*;
import com.gspb.avalon.client.domain.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Application service for client operations.
 */
@Service
@RequiredArgsConstructor
public class ClientApplicationService {
    
    private final ClientRepository clientRepository;
    private final ClientDTOMapper dtoMapper;
    
    /**
     * Creates a new client.
     *
     * @param command The create client command
     * @return The created client DTO
     */
    @Transactional
    public ClientDTO createClient(CreateClientCommand command) {
        clientRepository.findByEmail(command.getEmail())
                .ifPresent(client -> {
                    throw new IllegalArgumentException("Client with email " + command.getEmail() + " already exists");
                });
        
        Client client = new Client(
                UUID.randomUUID(),
                command.getFirstName(),
                command.getLastName(),
                command.getEmail(),
                command.getPhoneNumber(),
                command.getDateOfBirth(),
                new Address(
                        command.getAddress().getStreet(),
                        command.getAddress().getCity(),
                        command.getAddress().getState(),
                        command.getAddress().getPostalCode(),
                        command.getAddress().getCountry()
                )
        );
        
        Client savedClient = clientRepository.save(client);
        
        return dtoMapper.toDTO(savedClient);
    }
    
    /**
     * Updates a client.
     *
     * @param command The update client command
     * @return The updated client DTO
     */
    @Transactional
    public ClientDTO updateClient(UpdateClientCommand command) {
        Client client = clientRepository.findById(command.getId())
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));
        
        if (command.getFirstName() != null && command.getLastName() != null) {
        }
        
        if (command.getEmail() != null && command.getPhoneNumber() != null) {
            client.updateContactInfo(command.getEmail(), command.getPhoneNumber());
        }
        
        if (command.getAddress() != null) {
            client.updateAddress(new Address(
                    command.getAddress().getStreet(),
                    command.getAddress().getCity(),
                    command.getAddress().getState(),
                    command.getAddress().getPostalCode(),
                    command.getAddress().getCountry()
            ));
        }
        
        Client savedClient = clientRepository.save(client);
        
        return dtoMapper.toDTO(savedClient);
    }
    
    /**
     * Gets a client by ID.
     *
     * @param id The client ID
     * @return The client DTO
     */
    @Transactional(readOnly = true)
    public ClientDTO getClient(UUID id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));
        
        return dtoMapper.toDTO(client);
    }
    
    /**
     * Gets all clients.
     *
     * @return A list of client DTOs
     */
    @Transactional(readOnly = true)
    public List<ClientDTO> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        
        return clients.stream()
                .map(dtoMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Adds a KYC document to a client.
     *
     * @param command The add KYC document command
     * @return The updated client DTO
     */
    @Transactional
    public ClientDTO addKYCDocument(AddKYCDocumentCommand command) {
        Client client = clientRepository.findById(command.getClientId())
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));
        
        KYCDocument document = new KYCDocument(
                UUID.randomUUID(),
                client.getId(),
                command.getType(),
                command.getDocumentNumber(),
                command.getFileUrl()
        );
        
        client.addKYCDocument(document);
        
        if (client.getStatus() == ClientStatus.PENDING_KYC) {
            client.updateStatus(ClientStatus.KYC_IN_PROGRESS);
        }
        
        Client savedClient = clientRepository.save(client);
        
        return dtoMapper.toDTO(savedClient);
    }
    
    /**
     * Verifies a KYC document.
     *
     * @param command The verify KYC document command
     * @return The updated client DTO
     */
    @Transactional
    public ClientDTO verifyKYCDocument(VerifyKYCDocumentCommand command) {
        Client client = clientRepository.findAll().stream()
                .filter(c -> c.getKYCDocuments().stream()
                        .anyMatch(doc -> doc.getId().equals(command.getDocumentId())))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Document not found"));
        
        KYCDocument document = client.getKYCDocuments().stream()
                .filter(doc -> doc.getId().equals(command.getDocumentId()))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Document not found"));
        
        if (command.isApproved()) {
            document.verify(command.getNotes());
        } else {
            document.reject(command.getNotes());
        }
        
        boolean allVerified = client.getKYCDocuments().stream()
                .allMatch(doc -> doc.getStatus() == KYCDocumentStatus.VERIFIED);
        
        if (allVerified && client.getStatus() == ClientStatus.KYC_IN_PROGRESS) {
            client.updateStatus(ClientStatus.KYC_COMPLETED);
        }
        
        Client savedClient = clientRepository.save(client);
        
        return dtoMapper.toDTO(savedClient);
    }
    
    /**
     * Updates a client's status.
     *
     * @param id The client ID
     * @param status The new status
     * @return The updated client DTO
     */
    @Transactional
    public ClientDTO updateClientStatus(UUID id, ClientStatus status) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));
        
        client.updateStatus(status);
        
        Client savedClient = clientRepository.save(client);
        
        return dtoMapper.toDTO(savedClient);
    }
}
