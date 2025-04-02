package com.gspb.avalon.client.domain.model;

import com.gspb.avalon.shared.domain.AggregateRoot;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Client aggregate root representing a high net worth client in the system.
 */
public class Client extends AggregateRoot<UUID> {
    
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private Address address;
    private ClientStatus status;
    private final List<KYCDocument> kycDocuments = new ArrayList<>();
    private final List<ClientRelationship> relationships = new ArrayList<>();
    
    /**
     * Creates a new client.
     *
     * @param id The client ID
     * @param firstName The client's first name
     * @param lastName The client's last name
     * @param email The client's email address
     * @param phoneNumber The client's phone number
     * @param dateOfBirth The client's date of birth
     * @param address The client's address
     */
    public Client(UUID id, String firstName, String lastName, String email, String phoneNumber, 
                 LocalDate dateOfBirth, Address address) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.status = ClientStatus.PENDING_KYC;
        
        registerEvent(new ClientCreatedEvent(id, firstName, lastName, email));
    }
    
    /**
     * Adds a KYC document to the client.
     *
     * @param document The KYC document to add
     */
    public void addKYCDocument(KYCDocument document) {
        this.kycDocuments.add(document);
        registerEvent(new KYCDocumentAddedEvent(this.getId(), document.getId(), document.getType()));
    }
    
    /**
     * Adds a relationship to the client.
     *
     * @param relationship The relationship to add
     */
    public void addRelationship(ClientRelationship relationship) {
        this.relationships.add(relationship);
    }
    
    /**
     * Updates the client's KYC status.
     *
     * @param status The new status
     */
    public void updateStatus(ClientStatus status) {
        ClientStatus oldStatus = this.status;
        this.status = status;
        registerEvent(new ClientStatusChangedEvent(this.getId(), oldStatus, status));
    }
    
    /**
     * Updates the client's address.
     *
     * @param address The new address
     */
    public void updateAddress(Address address) {
        this.address = address;
    }
    
    /**
     * Updates the client's contact information.
     *
     * @param email The new email address
     * @param phoneNumber The new phone number
     */
    public void updateContactInfo(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    
    /**
     * Gets the client's first name.
     *
     * @return The first name
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * Gets the client's last name.
     *
     * @return The last name
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * Gets the client's full name.
     *
     * @return The full name
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    /**
     * Gets the client's email address.
     *
     * @return The email address
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Gets the client's phone number.
     *
     * @return The phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    /**
     * Gets the client's date of birth.
     *
     * @return The date of birth
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    
    /**
     * Gets the client's address.
     *
     * @return The address
     */
    public Address getAddress() {
        return address;
    }
    
    /**
     * Gets the client's status.
     *
     * @return The status
     */
    public ClientStatus getStatus() {
        return status;
    }
    
    /**
     * Gets the client's KYC documents.
     *
     * @return An unmodifiable list of KYC documents
     */
    public List<KYCDocument> getKYCDocuments() {
        return Collections.unmodifiableList(kycDocuments);
    }
    
    /**
     * Gets the client's relationships.
     *
     * @return An unmodifiable list of relationships
     */
    public List<ClientRelationship> getRelationships() {
        return Collections.unmodifiableList(relationships);
    }
}
