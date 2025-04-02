package com.gspb.avalon.collateral.presentation.rest;

import com.gspb.avalon.collateral.application.dto.*;
import com.gspb.avalon.collateral.application.service.CollateralValuationApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * REST controller for collateral valuation operations.
 */
@RestController
@RequestMapping("/valuations")
@RequiredArgsConstructor
public class CollateralValuationController {
    
    private final CollateralValuationApplicationService valuationService;
    
    /**
     * Creates a new collateral valuation.
     *
     * @param command The create command
     * @return The created valuation DTO
     */
    @PostMapping
    public ResponseEntity<CollateralValuationDTO> createValuation(@Valid @RequestBody CreateCollateralValuationCommand command) {
        CollateralValuationDTO valuation = valuationService.createValuation(command);
        return new ResponseEntity<>(valuation, HttpStatus.CREATED);
    }
    
    /**
     * Performs an automated valuation for a collateral.
     *
     * @param command The create command
     * @return The valuation DTO with automated valuation results
     */
    @PostMapping("/automated")
    public ResponseEntity<CollateralValuationDTO> performAutomatedValuation(@Valid @RequestBody CreateCollateralValuationCommand command) {
        CollateralValuationDTO valuation = valuationService.performAutomatedValuation(command);
        return new ResponseEntity<>(valuation, HttpStatus.CREATED);
    }
    
    /**
     * Updates a collateral valuation with estimated value and risk assessment.
     *
     * @param command The update command
     * @return The updated valuation DTO
     */
    @PutMapping("/update")
    public ResponseEntity<CollateralValuationDTO> updateValuation(@Valid @RequestBody UpdateCollateralValuationCommand command) {
        CollateralValuationDTO valuation = valuationService.updateValuation(command);
        return new ResponseEntity<>(valuation, HttpStatus.OK);
    }
    
    /**
     * Adds an external reference to a valuation.
     *
     * @param command The add external reference command
     * @return The updated valuation DTO
     */
    @PostMapping("/external-reference")
    public ResponseEntity<CollateralValuationDTO> addExternalReference(@Valid @RequestBody AddExternalReferenceCommand command) {
        CollateralValuationDTO valuation = valuationService.addExternalReference(command);
        return new ResponseEntity<>(valuation, HttpStatus.OK);
    }
    
    /**
     * Adds a risk factor to a valuation.
     *
     * @param command The add risk factor command
     * @return The updated valuation DTO
     */
    @PostMapping("/risk-factor")
    public ResponseEntity<CollateralValuationDTO> addRiskFactor(@Valid @RequestBody AddRiskFactorCommand command) {
        CollateralValuationDTO valuation = valuationService.addRiskFactor(command);
        return new ResponseEntity<>(valuation, HttpStatus.OK);
    }
    
    /**
     * Rejects a valuation.
     *
     * @param valuationId The valuation ID
     * @param reason The rejection reason
     * @return The updated valuation DTO
     */
    @PostMapping("/{valuationId}/reject")
    public ResponseEntity<CollateralValuationDTO> rejectValuation(
            @PathVariable UUID valuationId,
            @RequestParam String reason) {
        CollateralValuationDTO valuation = valuationService.rejectValuation(valuationId, reason);
        return new ResponseEntity<>(valuation, HttpStatus.OK);
    }
    
    /**
     * Requests manual review for a valuation.
     *
     * @param valuationId The valuation ID
     * @param reason The reason for manual review
     * @return The updated valuation DTO
     */
    @PostMapping("/{valuationId}/request-review")
    public ResponseEntity<CollateralValuationDTO> requestManualReview(
            @PathVariable UUID valuationId,
            @RequestParam String reason) {
        CollateralValuationDTO valuation = valuationService.requestManualReview(valuationId, reason);
        return new ResponseEntity<>(valuation, HttpStatus.OK);
    }
    
    /**
     * Gets a valuation by ID.
     *
     * @param valuationId The valuation ID
     * @return The valuation DTO
     */
    @GetMapping("/{valuationId}")
    public ResponseEntity<CollateralValuationDTO> getValuationById(@PathVariable UUID valuationId) {
        CollateralValuationDTO valuation = valuationService.getValuationById(valuationId);
        return new ResponseEntity<>(valuation, HttpStatus.OK);
    }
    
    /**
     * Gets all valuations for a collateral.
     *
     * @param collateralId The collateral ID
     * @return List of valuation DTOs
     */
    @GetMapping("/collateral/{collateralId}")
    public ResponseEntity<List<CollateralValuationDTO>> getValuationsByCollateralId(@PathVariable UUID collateralId) {
        List<CollateralValuationDTO> valuations = valuationService.getValuationsByCollateralId(collateralId);
        return new ResponseEntity<>(valuations, HttpStatus.OK);
    }
    
    /**
     * Gets all valuations for a loan application.
     *
     * @param loanApplicationId The loan application ID
     * @return List of valuation DTOs
     */
    @GetMapping("/loan-application/{loanApplicationId}")
    public ResponseEntity<List<CollateralValuationDTO>> getValuationsByLoanApplicationId(@PathVariable UUID loanApplicationId) {
        List<CollateralValuationDTO> valuations = valuationService.getValuationsByLoanApplicationId(loanApplicationId);
        return new ResponseEntity<>(valuations, HttpStatus.OK);
    }
}
