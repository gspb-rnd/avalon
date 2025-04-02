package com.gspb.avalon.collateral.infrastructure.persistence;

import com.gspb.avalon.collateral.domain.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for converting between domain model and JPA entities.
 */
@Component
public class CollateralValuationMapper {
    
    public CollateralValuation toDomain(CollateralValuationJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        
        CollateralValuation valuation = new CollateralValuation(
            entity.getId(),
            entity.getCollateralId(),
            entity.getLoanApplicationId(),
            entity.getAssetClass(),
            entity.getAssetDescription(),
            entity.getValuationMethod()
        );
        
        if (entity.getEstimatedValue() != null) {
            valuation.updateValuation(
                entity.getEstimatedValue(),
                entity.getHaircut(),
                entity.getRiskLevel(),
                entity.getValuatedBy(),
                entity.getExpirationDate()
            );
        }
        
        if (entity.getExternalReferences() != null) {
            for (ExternalValuationReferenceJpaEntity refEntity : entity.getExternalReferences()) {
                valuation.addExternalReference(
                    refEntity.getSourceSystem(),
                    refEntity.getReferenceId(),
                    refEntity.getReferenceUrl()
                );
            }
        }
        
        if (entity.getRiskFactors() != null) {
            for (RiskFactorJpaEntity riskEntity : entity.getRiskFactors()) {
                valuation.addRiskFactor(
                    riskEntity.getFactorName(),
                    riskEntity.getDescription(),
                    riskEntity.getImpact()
                );
            }
        }
        
        return valuation;
    }
    
    public CollateralValuationJpaEntity toEntity(CollateralValuation domain) {
        if (domain == null) {
            return null;
        }
        
        CollateralValuationJpaEntity entity = new CollateralValuationJpaEntity();
        entity.setId(domain.getId());
        entity.setCollateralId(domain.getCollateralId());
        entity.setLoanApplicationId(domain.getLoanApplicationId());
        entity.setAssetClass(domain.getAssetClass());
        entity.setAssetDescription(domain.getAssetDescription());
        entity.setValuationMethod(domain.getValuationMethod());
        entity.setEstimatedValue(domain.getEstimatedValue());
        entity.setHaircut(domain.getHaircut());
        entity.setAdjustedValue(domain.getAdjustedValue());
        entity.setRiskLevel(domain.getRiskLevel());
        entity.setStatus(domain.getStatus());
        entity.setValuationDate(domain.getValuationDate());
        entity.setExpirationDate(domain.getExpirationDate());
        entity.setValuatedBy(domain.getValuatedBy());
        
        if (domain.getValuationHistory() != null) {
            List<ValuationHistoryJpaEntity> historyEntities = domain.getValuationHistory().stream()
                .map(this::toHistoryEntity)
                .collect(Collectors.toList());
            entity.setValuationHistory(historyEntities);
        } else {
            entity.setValuationHistory(new ArrayList<>());
        }
        
        if (domain.getExternalReferences() != null) {
            List<ExternalValuationReferenceJpaEntity> refEntities = domain.getExternalReferences().stream()
                .map(this::toReferenceEntity)
                .collect(Collectors.toList());
            entity.setExternalReferences(refEntities);
        } else {
            entity.setExternalReferences(new ArrayList<>());
        }
        
        if (domain.getRiskFactors() != null) {
            List<RiskFactorJpaEntity> riskEntities = domain.getRiskFactors().stream()
                .map(this::toRiskFactorEntity)
                .collect(Collectors.toList());
            entity.setRiskFactors(riskEntities);
        } else {
            entity.setRiskFactors(new ArrayList<>());
        }
        
        return entity;
    }
    
    private ValuationHistoryJpaEntity toHistoryEntity(ValuationHistory domain) {
        ValuationHistoryJpaEntity entity = new ValuationHistoryJpaEntity();
        entity.setId(domain.getId());
        entity.setValuationId(domain.getValuationId());
        entity.setEstimatedValue(domain.getEstimatedValue());
        entity.setHaircut(domain.getHaircut());
        entity.setAdjustedValue(domain.getAdjustedValue());
        entity.setRiskLevel(domain.getRiskLevel());
        entity.setValuationMethod(domain.getValuationMethod());
        entity.setValuationDate(domain.getValuationDate());
        entity.setValuatedBy(domain.getValuatedBy());
        return entity;
    }
    
    private ExternalValuationReferenceJpaEntity toReferenceEntity(ExternalValuationReference domain) {
        ExternalValuationReferenceJpaEntity entity = new ExternalValuationReferenceJpaEntity();
        entity.setId(domain.getId());
        entity.setValuationId(domain.getValuationId());
        entity.setSourceSystem(domain.getSourceSystem());
        entity.setReferenceId(domain.getReferenceId());
        entity.setReferenceUrl(domain.getReferenceUrl());
        entity.setCreatedAt(domain.getCreatedAt());
        return entity;
    }
    
    private RiskFactorJpaEntity toRiskFactorEntity(RiskFactor domain) {
        RiskFactorJpaEntity entity = new RiskFactorJpaEntity();
        entity.setId(domain.getId());
        entity.setValuationId(domain.getValuationId());
        entity.setFactorName(domain.getFactorName());
        entity.setDescription(domain.getDescription());
        entity.setImpact(domain.getImpact());
        entity.setIdentifiedAt(domain.getIdentifiedAt());
        return entity;
    }
}
