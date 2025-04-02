package com.gspb.avalon.shared.domain;

import java.util.Objects;

/**
 * Base class for all entities in the domain model.
 * An entity is defined by its identity rather than its attributes.
 *
 * @param <ID> The type of the entity's identifier
 */
public abstract class Entity<ID> {
    
    private final ID id;
    
    protected Entity(ID id) {
        this.id = Objects.requireNonNull(id, "Entity ID cannot be null");
    }
    
    public ID getId() {
        return id;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity<?> entity = (Entity<?>) o;
        return Objects.equals(id, entity.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
