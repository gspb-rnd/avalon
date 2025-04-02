package com.gspb.avalon.loan.infrastructure.persistence;

import com.gspb.avalon.loan.domain.model.CollateralType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * JPA entity for collateral.
 */
@Entity
@Table(name = "collaterals")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CollateralJpaEntity {
    
    @Id
    private UUID id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_application_id", nullable = false)
    private LoanApplicationJpaEntity loanApplication;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private CollateralType type;
    
    @Column(name = "description", nullable = false)
    private String description;
    
    @Column(name = "estimated_value", precision = 19, scale = 2, nullable = false)
    private BigDecimal estimatedValue;
    
    @Column(name = "appraiser_value", precision = 19, scale = 2)
    private BigDecimal appraiserValue;
    
    @Column(name = "loan_to_value_ratio", precision = 5, scale = 2)
    private BigDecimal loanToValueRatio;
    
    @Column(name = "valuation_date")
    private LocalDate valuationDate;
    
    @Column(name = "appraiser_name")
    private String appraiserName;
    
    @Column(name = "documentation_url")
    private String documentationUrl;
    
    @Column(name = "verified")
    private Boolean verified;
}
