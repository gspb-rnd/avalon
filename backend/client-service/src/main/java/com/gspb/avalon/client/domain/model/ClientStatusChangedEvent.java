package com.gspb.avalon.client.domain.model;

import com.gspb.avalon.shared.domain.DomainEvent;

import java.util.UUID;

/**
 * Domain event that is triggered when a client's status changes.
 */
public class ClientStatusChangedEvent extends DomainEvent {
    
    private final UUID clientId;
    private final ClientStatus oldStatus;
    private final ClientStatus newStatus;
    
    /**
     * Creates a new client status changed event.
     *
     * @param clientId The client ID
     * @param oldStatus The old status
     * @param newStatus The new status
     */
    public ClientStatusChangedEvent(UUID clientId, ClientStatus oldStatus, ClientStatus newStatus) {
        super();
        this.clientId = clientId;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
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
     * Gets the old status.
     *
     * @return The old status
     */
    public ClientStatus getOldStatus() {
        return oldStatus;
    }
    
    /**
     * Gets the new status.
     *
     * @return The new status
     */
    public ClientStatus getNewStatus() {
        return newStatus;
    }
}
