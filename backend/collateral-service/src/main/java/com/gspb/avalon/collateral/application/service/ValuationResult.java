package com.gspb.avalon.collateral.application.service;

import com.gspb.avalon.collateral.domain.model.RiskLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Result object for valuation engine operations.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValuationResult {
    private BigDecimal estimatedValue;
    private BigDecimal haircut;
    private RiskLevel riskLevel;
    private LocalDateTime expirationDate;
    private String notes;
}
