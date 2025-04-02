package com.gspb.avalon.client.domain.model;

import com.gspb.avalon.shared.domain.Entity;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Entity representing a relationship between a client and another person.
 */
public class ClientRelationship extends Entity<UUID> {
    
    private final UUID clientId;
    private final String relatedPersonName;
    private final RelationshipType type;
    private final LocalDate startDate;
    private LocalDate endDate;
    private String notes;
    
    /**
     * Creates a new client relationship.
     *
     * @param id The relationship ID
     * @param clientId The client ID
     * @param relatedPersonName The name of the related person
     * @param type The relationship type
     * @param startDate The start date of the relationship
     */
    public ClientRelationship(UUID id, UUID clientId, String relatedPersonName, RelationshipType type, LocalDate startDate) {
        super(id);
        this.clientId = clientId;
        this.relatedPersonName = relatedPersonName;
        this.type = type;
        this.startDate = startDate;
    }
    
    /**
     * Ends the relationship.
     *
     * @param endDate The end date of the relationship
     */
    public void endRelationship(LocalDate endDate) {
        this.endDate = endDate;
    }
    
    /**
     * Adds notes to the relationship.
     *
     * @param notes The notes to add
     */
    public void addNotes(String notes) {
        this.notes = notes;
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
     * Gets the name of the related person.
     *
     * @return The name of the related person
     */
    public String getRelatedPersonName() {
        return relatedPersonName;
    }
    
    /**
     * Gets the relationship type.
     *
     * @return The relationship type
     */
    public RelationshipType getType() {
        return type;
    }
    
    /**
     * Gets the start date of the relationship.
     *
     * @return The start date
     */
    public LocalDate getStartDate() {
        return startDate;
    }
    
    /**
     * Gets the end date of the relationship.
     *
     * @return The end date, or null if the relationship is ongoing
     */
    public LocalDate getEndDate() {
        return endDate;
    }
    
    /**
     * Gets the notes about the relationship.
     *
     * @return The notes
     */
    public String getNotes() {
        return notes;
    }
    
    /**
     * Checks if the relationship is active.
     *
     * @return true if the relationship is active, false otherwise
     */
    public boolean isActive() {
        return endDate == null || endDate.isAfter(LocalDate.now());
    }
}
