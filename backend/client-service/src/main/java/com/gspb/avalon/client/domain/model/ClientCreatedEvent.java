package com.gspb.avalon.client.domain.model;

import com.gspb.avalon.shared.domain.DomainEvent;

import java.util.UUID;

/**
 * Domain event that is triggered when a new client is created.
 */
public class ClientCreatedEvent extends DomainEvent {
    
    private final UUID clientId;
    private final String firstName;
    private final String lastName;
    private final String email;
    
    /**
     * Creates a new client created event.
     *
     * @param clientId The client ID
     * @param firstName The client's first name
     * @param lastName The client's last name
     * @param email The client's email address
     */
    public ClientCreatedEvent(UUID clientId, String firstName, String lastName, String email) {
        super();
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    
    /**
     * Gets the client ID.
     *
     * @return The client ID
     */
    public UUID getClientId() {
        return clientId;
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
     * Gets the client's email address.
     *
     * @return The email address
     */
    public String getEmail() {
        return email;
    }
}
