package com.gspb.avalon.loan.application.dto;

import com.gspb.avalon.loan.domain.model.CollateralType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Data Transfer Object for Collateral.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollateralDTO {
    
    private UUID id;
    private UUID loanApplicationId;
    private CollateralType type;
    private String description;
    private BigDecimal estimatedValue;
    private BigDecimal appraiserValue;
    private BigDecimal loanToValueRatio;
    private LocalDate valuationDate;
    private String appraiserName;
    private String documentationUrl;
    private boolean verified;
}
