package com.gspb.avalon.shared.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Base class for all aggregate roots in the domain model.
 * An aggregate root is an entity that is the root of an aggregate,
 * which is a cluster of domain objects that can be treated as a single unit.
 *
 * @param <ID> The type of the aggregate root's identifier
 */
public abstract class AggregateRoot<ID> extends Entity<ID> {
    
    private final List<DomainEvent> domainEvents = new ArrayList<>();
    
    protected AggregateRoot(ID id) {
        super(id);
    }
    
    /**
     * Registers a domain event to be dispatched when the aggregate is saved.
     *
     * @param event The domain event to register
     */
    protected void registerEvent(DomainEvent event) {
        this.domainEvents.add(event);
    }
    
    /**
     * Gets all domain events registered by this aggregate root.
     *
     * @return An unmodifiable list of domain events
     */
    public List<DomainEvent> getDomainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }
    
    /**
     * Clears all domain events registered by this aggregate root.
     * This should be called after the events have been dispatched.
     */
    public void clearDomainEvents() {
        this.domainEvents.clear();
    }
}
