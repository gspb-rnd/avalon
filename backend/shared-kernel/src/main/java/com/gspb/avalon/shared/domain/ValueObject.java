package com.gspb.avalon.shared.domain;

/**
 * Marker interface for all value objects in the domain model.
 * A value object is an object that contains attributes but has no identity.
 * Value objects should be immutable.
 */
public interface ValueObject {
    
    /**
     * Value objects are compared by their structural equality.
     * This method must be implemented by all value objects.
     *
     * @param other The object to compare with
     * @return true if the objects are structurally equal, false otherwise
     */
    @Override
    boolean equals(Object other);
    
    /**
     * The hash code must be consistent with equals.
     *
     * @return The hash code of this value object
     */
    @Override
    int hashCode();
}
