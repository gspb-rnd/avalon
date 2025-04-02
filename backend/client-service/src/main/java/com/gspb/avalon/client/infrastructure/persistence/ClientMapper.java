package com.gspb.avalon.client.infrastructure.persistence;

import com.gspb.avalon.client.domain.model.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Mapper between domain and JPA entities for Client.
 */
@Component
public class ClientMapper {
    
    /**
     * Maps a domain Client to a JPA entity.
     *
     * @param client The domain Client
     * @return The JPA entity
     */
    public ClientJpaEntity toJpaEntity(Client client) {
        ClientJpaEntity entity = ClientJpaEntity.builder()
                .id(client.getId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .email(client.getEmail())
                .phoneNumber(client.getPhoneNumber())
                .dateOfBirth(client.getDateOfBirth())
                .status(client.getStatus())
                .address(toJpaEmbeddable(client.getAddress()))
                .build();
        
        entity.setKycDocuments(client.getKYCDocuments().stream()
                .map(doc -> toJpaEntity(doc, entity))
                .collect(Collectors.toList()));
        
        entity.setRelationships(client.getRelationships().stream()
                .map(rel -> toJpaEntity(rel, entity))
                .collect(Collectors.toList()));
        
        return entity;
    }
    
    /**
     * Maps a JPA entity to a domain Client.
     *
     * @param entity The JPA entity
     * @return The domain Client
     */
    public Client toDomain(ClientJpaEntity entity) {
        Client client = new Client(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPhoneNumber(),
                entity.getDateOfBirth(),
                toDomain(entity.getAddress())
        );
        
        client.updateStatus(entity.getStatus());
        
        entity.getKycDocuments().forEach(doc -> {
            KYCDocument kycDocument = toDomain(doc);
            client.addKYCDocument(kycDocument);
        });
        
        entity.getRelationships().forEach(rel -> {
            ClientRelationship relationship = toDomain(rel);
            client.addRelationship(relationship);
        });
        
        client.clearDomainEvents();
        
        return client;
    }
    
    /**
     * Maps a domain Address to a JPA embeddable.
     *
     * @param address The domain Address
     * @return The JPA embeddable
     */
    private AddressJpaEmbeddable toJpaEmbeddable(Address address) {
        return AddressJpaEmbeddable.builder()
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .postalCode(address.getPostalCode())
                .country(address.getCountry())
                .build();
    }
    
    /**
     * Maps a JPA embeddable to a domain Address.
     *
     * @param embeddable The JPA embeddable
     * @return The domain Address
     */
    private Address toDomain(AddressJpaEmbeddable embeddable) {
        return new Address(
                embeddable.getStreet(),
                embeddable.getCity(),
                embeddable.getState(),
                embeddable.getPostalCode(),
                embeddable.getCountry()
        );
    }
    
    /**
     * Maps a domain KYCDocument to a JPA entity.
     *
     * @param document The domain KYCDocument
     * @param clientEntity The client JPA entity
     * @return The JPA entity
     */
    private KYCDocumentJpaEntity toJpaEntity(KYCDocument document, ClientJpaEntity clientEntity) {
        return KYCDocumentJpaEntity.builder()
                .id(document.getId())
                .client(clientEntity)
                .type(document.getType())
                .documentNumber(document.getDocumentNumber())
                .submissionDate(document.getSubmissionDate())
                .fileUrl(document.getFileUrl())
                .status(document.getStatus())
                .verificationNotes(document.getVerificationNotes())
                .build();
    }
    
    /**
     * Maps a JPA entity to a domain KYCDocument.
     *
     * @param entity The JPA entity
     * @return The domain KYCDocument
     */
    private KYCDocument toDomain(KYCDocumentJpaEntity entity) {
        KYCDocument document = new KYCDocument(
                entity.getId(),
                entity.getClient().getId(),
                entity.getType(),
                entity.getDocumentNumber(),
                entity.getFileUrl()
        );
        
        if (entity.getStatus() == KYCDocumentStatus.VERIFIED) {
            document.verify(entity.getVerificationNotes());
        } else if (entity.getStatus() == KYCDocumentStatus.REJECTED) {
            document.reject(entity.getVerificationNotes());
        }
        
        return document;
    }
    
    /**
     * Maps a domain ClientRelationship to a JPA entity.
     *
     * @param relationship The domain ClientRelationship
     * @param clientEntity The client JPA entity
     * @return The JPA entity
     */
    private ClientRelationshipJpaEntity toJpaEntity(ClientRelationship relationship, ClientJpaEntity clientEntity) {
        return ClientRelationshipJpaEntity.builder()
                .id(relationship.getId())
                .client(clientEntity)
                .relatedPersonName(relationship.getRelatedPersonName())
                .type(relationship.getType())
                .startDate(relationship.getStartDate())
                .endDate(relationship.getEndDate())
                .notes(relationship.getNotes())
                .build();
    }
    
    /**
     * Maps a JPA entity to a domain ClientRelationship.
     *
     * @param entity The JPA entity
     * @return The domain ClientRelationship
     */
    private ClientRelationship toDomain(ClientRelationshipJpaEntity entity) {
        ClientRelationship relationship = new ClientRelationship(
                entity.getId(),
                entity.getClient().getId(),
                entity.getRelatedPersonName(),
                entity.getType(),
                entity.getStartDate()
        );
        
        if (entity.getEndDate() != null) {
            relationship.endRelationship(entity.getEndDate());
        }
        
        if (entity.getNotes() != null) {
            relationship.addNotes(entity.getNotes());
        }
        
        return relationship;
    }
}
