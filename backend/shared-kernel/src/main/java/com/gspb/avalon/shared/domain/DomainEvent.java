package com.gspb.avalon.shared.domain;

import java.time.Instant;
import java.util.UUID;

/**
 * Base class for all domain events in the system.
 * Domain events represent something that happened in the domain that domain experts care about.
 */
public abstract class DomainEvent {
    
    private final UUID eventId;
    private final Instant occurredOn;
    
    protected DomainEvent() {
        this.eventId = UUID.randomUUID();
        this.occurredOn = Instant.now();
    }
    
    /**
     * Gets the unique identifier of this event.
     *
     * @return The event ID
     */
    public UUID getEventId() {
        return eventId;
    }
    
    /**
     * Gets the timestamp when this event occurred.
     *
     * @return The timestamp
     */
    public Instant getOccurredOn() {
        return occurredOn;
    }
    
    /**
     * Gets the type of this event.
     * By default, this is the simple name of the event class.
     *
     * @return The event type
     */
    public String getEventType() {
        return this.getClass().getSimpleName();
    }
}
