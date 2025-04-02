package com.gspb.avalon.shared.domain;

import java.util.List;
import java.util.Optional;

/**
 * Generic repository interface for domain-driven design.
 * Repositories mediate between the domain and data mapping layers.
 *
 * @param <T> The type of the aggregate root
 * @param <ID> The type of the aggregate root's identifier
 */
public interface Repository<T extends AggregateRoot<ID>, ID> {
    
    /**
     * Saves an aggregate root.
     *
     * @param aggregateRoot The aggregate root to save
     * @return The saved aggregate root
     */
    T save(T aggregateRoot);
    
    /**
     * Finds an aggregate root by its identifier.
     *
     * @param id The identifier
     * @return An Optional containing the aggregate root, or empty if not found
     */
    Optional<T> findById(ID id);
    
    /**
     * Finds all aggregate roots.
     *
     * @return A list of all aggregate roots
     */
    List<T> findAll();
    
    /**
     * Deletes an aggregate root.
     *
     * @param aggregateRoot The aggregate root to delete
     */
    void delete(T aggregateRoot);
    
    /**
     * Checks if an aggregate root with the given identifier exists.
     *
     * @param id The identifier
     * @return true if an aggregate root with the given identifier exists, false otherwise
     */
    boolean existsById(ID id);
}
