package com.gspb.avalon.collateral.application.dto;

import com.gspb.avalon.collateral.domain.model.RiskLevel;
import com.gspb.avalon.collateral.domain.model.ValuationMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Data Transfer Object for ValuationHistory.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValuationHistoryDTO {
    private UUID id;
    private UUID valuationId;
    private BigDecimal estimatedValue;
    private BigDecimal haircut;
    private BigDecimal adjustedValue;
    private RiskLevel riskLevel;
    private ValuationMethod valuationMethod;
    private LocalDateTime valuationDate;
    private String valuatedBy;
}
