package com.gspb.avalon.client.domain.repository;

import com.gspb.avalon.client.domain.model.Client;
import com.gspb.avalon.shared.domain.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for Client aggregate root.
 */
public interface ClientRepository extends Repository<Client, UUID> {
    
    /**
     * Finds a client by email address.
     *
     * @param email The email address
     * @return An Optional containing the client, or empty if not found
     */
    Optional<Client> findByEmail(String email);
    
    /**
     * Finds clients by status.
     *
     * @param status The status
     * @return A list of clients with the given status
     */
    List<Client> findByStatus(String status);
    
    /**
     * Finds clients by name.
     *
     * @param name The name to search for
     * @return A list of clients whose name contains the given string
     */
    List<Client> findByNameContaining(String name);
}
