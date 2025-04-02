package com.gspb.avalon.client.application.dto;

import com.gspb.avalon.client.domain.model.RelationshipType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Data Transfer Object for ClientRelationship.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientRelationshipDTO {
    
    private UUID id;
    private UUID clientId;
    private String relatedPersonName;
    private RelationshipType type;
    private LocalDate startDate;
    private LocalDate endDate;
    private String notes;
    private boolean active;
}
