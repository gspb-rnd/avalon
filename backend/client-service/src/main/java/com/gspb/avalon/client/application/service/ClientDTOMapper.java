package com.gspb.avalon.client.application.service;

import com.gspb.avalon.client.application.dto.*;
import com.gspb.avalon.client.domain.model.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Mapper between domain entities and DTOs for Client.
 */
@Component
public class ClientDTOMapper {
    
    /**
     * Maps a domain Client to a DTO.
     *
     * @param client The domain Client
     * @return The DTO
     */
    public ClientDTO toDTO(Client client) {
        ClientDTO dto = ClientDTO.builder()
                .id(client.getId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .email(client.getEmail())
                .phoneNumber(client.getPhoneNumber())
                .dateOfBirth(client.getDateOfBirth())
                .status(client.getStatus())
                .address(toDTO(client.getAddress()))
                .build();
        
        dto.setKycDocuments(client.getKYCDocuments().stream()
                .map(this::toDTO)
                .collect(Collectors.toList()));
        
        dto.setRelationships(client.getRelationships().stream()
                .map(this::toDTO)
                .collect(Collectors.toList()));
        
        return dto;
    }
    
    /**
     * Maps a domain Address to a DTO.
     *
     * @param address The domain Address
     * @return The DTO
     */
    public AddressDTO toDTO(Address address) {
        return AddressDTO.builder()
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .postalCode(address.getPostalCode())
                .country(address.getCountry())
                .build();
    }
    
    /**
     * Maps a domain KYCDocument to a DTO.
     *
     * @param document The domain KYCDocument
     * @return The DTO
     */
    public KYCDocumentDTO toDTO(KYCDocument document) {
        return KYCDocumentDTO.builder()
                .id(document.getId())
                .clientId(document.getClientId())
                .type(document.getType())
                .documentNumber(document.getDocumentNumber())
                .submissionDate(document.getSubmissionDate())
                .fileUrl(document.getFileUrl())
                .status(document.getStatus())
                .verificationNotes(document.getVerificationNotes())
                .build();
    }
    
    /**
     * Maps a domain ClientRelationship to a DTO.
     *
     * @param relationship The domain ClientRelationship
     * @return The DTO
     */
    public ClientRelationshipDTO toDTO(ClientRelationship relationship) {
        return ClientRelationshipDTO.builder()
                .id(relationship.getId())
                .clientId(relationship.getClientId())
                .relatedPersonName(relationship.getRelatedPersonName())
                .type(relationship.getType())
                .startDate(relationship.getStartDate())
                .endDate(relationship.getEndDate())
                .notes(relationship.getNotes())
                .active(relationship.isActive())
                .build();
    }
}
