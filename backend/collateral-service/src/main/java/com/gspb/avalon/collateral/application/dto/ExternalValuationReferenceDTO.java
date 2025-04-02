package com.gspb.avalon.collateral.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Data Transfer Object for ExternalValuationReference.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExternalValuationReferenceDTO {
    private UUID id;
    private UUID valuationId;
    private String sourceSystem;
    private String referenceId;
    private String referenceUrl;
    private LocalDateTime createdAt;
}
