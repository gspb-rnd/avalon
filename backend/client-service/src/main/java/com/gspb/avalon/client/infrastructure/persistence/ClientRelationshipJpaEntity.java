package com.gspb.avalon.client.infrastructure.persistence;

import com.gspb.avalon.client.domain.model.RelationshipType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

/**
 * JPA entity for ClientRelationship.
 */
@Entity
@Table(name = "client_relationships")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientRelationshipJpaEntity {
    
    @Id
    private UUID id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private ClientJpaEntity client;
    
    @Column(nullable = false)
    private String relatedPersonName;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RelationshipType type;
    
    @Column(nullable = false)
    private LocalDate startDate;
    
    @Column
    private LocalDate endDate;
    
    @Column
    private String notes;
}
