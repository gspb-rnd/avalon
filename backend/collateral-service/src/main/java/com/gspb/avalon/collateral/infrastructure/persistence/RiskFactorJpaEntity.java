package com.gspb.avalon.collateral.infrastructure.persistence;

import com.gspb.avalon.collateral.domain.model.RiskLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * JPA entity for persisting RiskFactor value objects.
 */
@Entity
@Table(name = "risk_factors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RiskFactorJpaEntity {
    
    @Id
    private UUID id;
    
    private UUID valuationId;
    
    private String factorName;
    
    @Column(length = 500)
    private String description;
    
    @Enumerated(EnumType.STRING)
    private RiskLevel impact;
    
    private LocalDateTime identifiedAt;
}
