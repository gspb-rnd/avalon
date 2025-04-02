package com.gspb.avalon.collateral.infrastructure.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * JPA entity for persisting ExternalValuationReference value objects.
 */
@Entity
@Table(name = "external_valuation_references")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExternalValuationReferenceJpaEntity {
    
    @Id
    private UUID id;
    
    private UUID valuationId;
    
    private String sourceSystem;
    
    private String referenceId;
    
    private String referenceUrl;
    
    private LocalDateTime createdAt;
}
