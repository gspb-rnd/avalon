package com.gspb.avalon.client.infrastructure.persistence;

import com.gspb.avalon.client.domain.model.ClientStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Spring Data JPA repository for ClientJpaEntity.
 */
public interface SpringDataClientRepository extends JpaRepository<ClientJpaEntity, UUID> {
    
    /**
     * Finds a client by email address.
     *
     * @param email The email address
     * @return An Optional containing the client, or empty if not found
     */
    Optional<ClientJpaEntity> findByEmail(String email);
    
    /**
     * Finds clients by status.
     *
     * @param status The status
     * @return A list of clients with the given status
     */
    List<ClientJpaEntity> findByStatus(ClientStatus status);
    
    /**
     * Finds clients by name.
     *
     * @param firstName The first name to search for
     * @param lastName The last name to search for
     * @return A list of clients whose name contains the given string
     */
    List<ClientJpaEntity> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);
}
