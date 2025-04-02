package com.gspb.avalon.collateral.application.service;

import com.gspb.avalon.collateral.application.dto.*;
import com.gspb.avalon.collateral.domain.model.CollateralValuation;
import com.gspb.avalon.collateral.domain.repository.CollateralValuationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Application service for collateral valuation operations.
 */
@Service
@RequiredArgsConstructor
public class CollateralValuationApplicationService {
    
    private final CollateralValuationRepository valuationRepository;
    private final CollateralValuationDTOMapper dtoMapper;
    private final ValuationEngineService valuationEngineService;
    
    /**
     * Creates a new collateral valuation.
     *
     * @param command The create command
     * @return The created valuation DTO
     */
    @Transactional
    public CollateralValuationDTO createValuation(CreateCollateralValuationCommand command) {
        UUID valuationId = UUID.randomUUID();
        
        CollateralValuation valuation = new CollateralValuation(
            valuationId,
            command.getCollateralId(),
            command.getLoanApplicationId(),
            command.getAssetClass(),
            command.getAssetDescription(),
            command.getValuationMethod()
        );
        
        CollateralValuation savedValuation = valuationRepository.save(valuation);
        return dtoMapper.toDTO(savedValuation);
    }
    
    /**
     * Updates a collateral valuation with estimated value and risk assessment.
     *
     * @param command The update command
     * @return The updated valuation DTO
     */
    @Transactional
    public CollateralValuationDTO updateValuation(UpdateCollateralValuationCommand command) {
        CollateralValuation valuation = valuationRepository.findById(command.getValuationId())
            .orElseThrow(() -> new IllegalArgumentException("Valuation not found with ID: " + command.getValuationId()));
        
        valuation.updateValuation(
            command.getEstimatedValue(),
            command.getHaircut(),
            command.getRiskLevel(),
            command.getValuatedBy(),
            command.getExpirationDate()
        );
        
        CollateralValuation savedValuation = valuationRepository.save(valuation);
        return dtoMapper.toDTO(savedValuation);
    }
    
    /**
     * Adds an external reference to a valuation.
     *
     * @param command The add external reference command
     * @return The updated valuation DTO
     */
    @Transactional
    public CollateralValuationDTO addExternalReference(AddExternalReferenceCommand command) {
        CollateralValuation valuation = valuationRepository.findById(command.getValuationId())
            .orElseThrow(() -> new IllegalArgumentException("Valuation not found with ID: " + command.getValuationId()));
        
        valuation.addExternalReference(
            command.getSourceSystem(),
            command.getReferenceId(),
            command.getReferenceUrl()
        );
        
        CollateralValuation savedValuation = valuationRepository.save(valuation);
        return dtoMapper.toDTO(savedValuation);
    }
    
    /**
     * Adds a risk factor to a valuation.
     *
     * @param command The add risk factor command
     * @return The updated valuation DTO
     */
    @Transactional
    public CollateralValuationDTO addRiskFactor(AddRiskFactorCommand command) {
        CollateralValuation valuation = valuationRepository.findById(command.getValuationId())
            .orElseThrow(() -> new IllegalArgumentException("Valuation not found with ID: " + command.getValuationId()));
        
        valuation.addRiskFactor(
            command.getFactorName(),
            command.getDescription(),
            command.getImpact()
        );
        
        CollateralValuation savedValuation = valuationRepository.save(valuation);
        return dtoMapper.toDTO(savedValuation);
    }
    
    /**
     * Performs an automated valuation for a collateral.
     *
     * @param command The create command
     * @return The valuation DTO with automated valuation results
     */
    @Transactional
    public CollateralValuationDTO performAutomatedValuation(CreateCollateralValuationCommand command) {
        CollateralValuationDTO valuationDTO = createValuation(command);
        
        ValuationResult result = valuationEngineService.performValuation(
            valuationDTO.getId(),
            command.getCollateralId(),
            command.getAssetClass(),
            command.getAssetDescription()
        );
        
        UpdateCollateralValuationCommand updateCommand = UpdateCollateralValuationCommand.builder()
            .valuationId(valuationDTO.getId())
            .estimatedValue(result.getEstimatedValue())
            .haircut(result.getHaircut())
            .riskLevel(result.getRiskLevel())
            .valuatedBy("Automated Valuation System")
            .expirationDate(result.getExpirationDate())
            .build();
        
        return updateValuation(updateCommand);
    }
    
    /**
     * Rejects a valuation.
     *
     * @param valuationId The valuation ID
     * @param reason The rejection reason
     * @return The updated valuation DTO
     */
    @Transactional
    public CollateralValuationDTO rejectValuation(UUID valuationId, String reason) {
        CollateralValuation valuation = valuationRepository.findById(valuationId)
            .orElseThrow(() -> new IllegalArgumentException("Valuation not found with ID: " + valuationId));
        
        valuation.rejectValuation(reason);
        
        CollateralValuation savedValuation = valuationRepository.save(valuation);
        return dtoMapper.toDTO(savedValuation);
    }
    
    /**
     * Requests manual review for a valuation.
     *
     * @param valuationId The valuation ID
     * @param reason The reason for manual review
     * @return The updated valuation DTO
     */
    @Transactional
    public CollateralValuationDTO requestManualReview(UUID valuationId, String reason) {
        CollateralValuation valuation = valuationRepository.findById(valuationId)
            .orElseThrow(() -> new IllegalArgumentException("Valuation not found with ID: " + valuationId));
        
        valuation.requestManualReview(reason);
        
        CollateralValuation savedValuation = valuationRepository.save(valuation);
        return dtoMapper.toDTO(savedValuation);
    }
    
    /**
     * Gets a valuation by ID.
     *
     * @param valuationId The valuation ID
     * @return The valuation DTO
     */
    @Transactional(readOnly = true)
    public CollateralValuationDTO getValuationById(UUID valuationId) {
        CollateralValuation valuation = valuationRepository.findById(valuationId)
            .orElseThrow(() -> new IllegalArgumentException("Valuation not found with ID: " + valuationId));
        
        return dtoMapper.toDTO(valuation);
    }
    
    /**
     * Gets all valuations for a collateral.
     *
     * @param collateralId The collateral ID
     * @return List of valuation DTOs
     */
    @Transactional(readOnly = true)
    public List<CollateralValuationDTO> getValuationsByCollateralId(UUID collateralId) {
        List<CollateralValuation> valuations = valuationRepository.findByCollateralId(collateralId);
        
        return valuations.stream()
            .map(dtoMapper::toDTO)
            .collect(Collectors.toList());
    }
    
    /**
     * Gets all valuations for a loan application.
     *
     * @param loanApplicationId The loan application ID
     * @return List of valuation DTOs
     */
    @Transactional(readOnly = true)
    public List<CollateralValuationDTO> getValuationsByLoanApplicationId(UUID loanApplicationId) {
        List<CollateralValuation> valuations = valuationRepository.findByLoanApplicationId(loanApplicationId);
        
        return valuations.stream()
            .map(dtoMapper::toDTO)
            .collect(Collectors.toList());
    }
}
