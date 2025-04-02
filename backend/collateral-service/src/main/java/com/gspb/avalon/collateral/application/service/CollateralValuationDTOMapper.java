package com.gspb.avalon.collateral.application.service;

import com.gspb.avalon.collateral.application.dto.*;
import com.gspb.avalon.collateral.domain.model.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for converting between domain model and DTOs.
 */
@Component
public class CollateralValuationDTOMapper {
    
    public CollateralValuationDTO toDTO(CollateralValuation domain) {
        if (domain == null) {
            return null;
        }
        
        List<ValuationHistoryDTO> historyDTOs = null;
        if (domain.getValuationHistory() != null) {
            historyDTOs = domain.getValuationHistory().stream()
                    .map(this::toHistoryDTO)
                    .collect(Collectors.toList());
        }
        
        List<ExternalValuationReferenceDTO> referenceDTOs = null;
        if (domain.getExternalReferences() != null) {
            referenceDTOs = domain.getExternalReferences().stream()
                    .map(this::toReferenceDTO)
                    .collect(Collectors.toList());
        }
        
        List<RiskFactorDTO> riskFactorDTOs = null;
        if (domain.getRiskFactors() != null) {
            riskFactorDTOs = domain.getRiskFactors().stream()
                    .map(this::toRiskFactorDTO)
                    .collect(Collectors.toList());
        }
        
        return CollateralValuationDTO.builder()
                .id(domain.getId())
                .collateralId(domain.getCollateralId())
                .loanApplicationId(domain.getLoanApplicationId())
                .assetClass(domain.getAssetClass())
                .assetDescription(domain.getAssetDescription())
                .valuationMethod(domain.getValuationMethod())
                .estimatedValue(domain.getEstimatedValue())
                .haircut(domain.getHaircut())
                .adjustedValue(domain.getAdjustedValue())
                .riskLevel(domain.getRiskLevel())
                .status(domain.getStatus())
                .valuationDate(domain.getValuationDate())
                .expirationDate(domain.getExpirationDate())
                .valuatedBy(domain.getValuatedBy())
                .valuationHistory(historyDTOs)
                .externalReferences(referenceDTOs)
                .riskFactors(riskFactorDTOs)
                .build();
    }
    
    private ValuationHistoryDTO toHistoryDTO(ValuationHistory domain) {
        return ValuationHistoryDTO.builder()
                .id(domain.getId())
                .valuationId(domain.getValuationId())
                .estimatedValue(domain.getEstimatedValue())
                .haircut(domain.getHaircut())
                .adjustedValue(domain.getAdjustedValue())
                .riskLevel(domain.getRiskLevel())
                .valuationMethod(domain.getValuationMethod())
                .valuationDate(domain.getValuationDate())
                .valuatedBy(domain.getValuatedBy())
                .build();
    }
    
    private ExternalValuationReferenceDTO toReferenceDTO(ExternalValuationReference domain) {
        return ExternalValuationReferenceDTO.builder()
                .id(domain.getId())
                .valuationId(domain.getValuationId())
                .sourceSystem(domain.getSourceSystem())
                .referenceId(domain.getReferenceId())
                .referenceUrl(domain.getReferenceUrl())
                .createdAt(domain.getCreatedAt())
                .build();
    }
    
    private RiskFactorDTO toRiskFactorDTO(RiskFactor domain) {
        return RiskFactorDTO.builder()
                .id(domain.getId())
                .valuationId(domain.getValuationId())
                .factorName(domain.getFactorName())
                .description(domain.getDescription())
                .impact(domain.getImpact())
                .identifiedAt(domain.getIdentifiedAt())
                .build();
    }
}
