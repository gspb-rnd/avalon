package com.gspb.avalon.collateral.infrastructure.persistence;

import com.gspb.avalon.collateral.domain.model.AssetClass;
import com.gspb.avalon.collateral.domain.model.RiskLevel;
import com.gspb.avalon.collateral.domain.model.ValuationMethod;
import com.gspb.avalon.collateral.domain.model.ValuationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * JPA entity for persisting CollateralValuation aggregate.
 */
@Entity
@Table(name = "collateral_valuations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CollateralValuationJpaEntity {
    
    @Id
    private UUID id;
    
    private UUID collateralId;
    
    private UUID loanApplicationId;
    
    @Enumerated(EnumType.STRING)
    private AssetClass assetClass;
    
    @Column(length = 500)
    private String assetDescription;
    
    @Enumerated(EnumType.STRING)
    private ValuationMethod valuationMethod;
    
    @Column(precision = 19, scale = 4)
    private BigDecimal estimatedValue;
    
    @Column(precision = 5, scale = 2)
    private BigDecimal haircut;
    
    @Column(precision = 19, scale = 4)
    private BigDecimal adjustedValue;
    
    @Enumerated(EnumType.STRING)
    private RiskLevel riskLevel;
    
    @Enumerated(EnumType.STRING)
    private ValuationStatus status;
    
    private LocalDateTime valuationDate;
    
    private LocalDateTime expirationDate;
    
    private String valuatedBy;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "valuation_id")
    private List<ValuationHistoryJpaEntity> valuationHistory = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "valuation_id")
    private List<ExternalValuationReferenceJpaEntity> externalReferences = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "valuation_id")
    private List<RiskFactorJpaEntity> riskFactors = new ArrayList<>();
}
