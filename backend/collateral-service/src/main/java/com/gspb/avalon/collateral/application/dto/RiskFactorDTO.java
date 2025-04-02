package com.gspb.avalon.collateral.application.dto;

import com.gspb.avalon.collateral.domain.model.RiskLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Data Transfer Object for RiskFactor.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RiskFactorDTO {
    private UUID id;
    private UUID valuationId;
    private String factorName;
    private String description;
    private RiskLevel impact;
    private LocalDateTime identifiedAt;
}
