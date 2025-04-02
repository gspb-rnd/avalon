package com.gspb.avalon.collateral.infrastructure.persistence;

import com.gspb.avalon.collateral.domain.model.RiskLevel;
import com.gspb.avalon.collateral.domain.model.ValuationMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * JPA entity for persisting ValuationHistory value objects.
 */
@Entity
@Table(name = "valuation_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValuationHistoryJpaEntity {
    
    @Id
    private UUID id;
    
    private UUID valuationId;
    
    @Column(precision = 19, scale = 4)
    private BigDecimal estimatedValue;
    
    @Column(precision = 5, scale = 2)
    private BigDecimal haircut;
    
    @Column(precision = 19, scale = 4)
    private BigDecimal adjustedValue;
    
    @Enumerated(EnumType.STRING)
    private RiskLevel riskLevel;
    
    @Enumerated(EnumType.STRING)
    private ValuationMethod valuationMethod;
    
    private LocalDateTime valuationDate;
    
    private String valuatedBy;
}
