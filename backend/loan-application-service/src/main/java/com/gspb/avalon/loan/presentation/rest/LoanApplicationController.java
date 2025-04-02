package com.gspb.avalon.loan.presentation.rest;

import com.gspb.avalon.loan.application.dto.*;
import com.gspb.avalon.loan.application.service.LoanApplicationApplicationService;
import com.gspb.avalon.loan.domain.model.LoanStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST controller for loan applications.
 */
@RestController
@RequestMapping("/api/loan-applications")
@RequiredArgsConstructor
public class LoanApplicationController {
    
    private final LoanApplicationApplicationService loanApplicationService;
    
    /**
     * Creates a new loan application.
     *
     * @param command The create loan application command
     * @return The created loan application DTO
     */
    @PostMapping
    public ResponseEntity<LoanApplicationDTO> createLoanApplication(@Valid @RequestBody CreateLoanApplicationCommand command) {
        LoanApplicationDTO loanApplicationDTO = loanApplicationService.createLoanApplication(command);
        return new ResponseEntity<>(loanApplicationDTO, HttpStatus.CREATED);
    }
    
    /**
     * Gets a loan application by ID.
     *
     * @param id The loan application ID
     * @return The loan application DTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<LoanApplicationDTO> getLoanApplication(@PathVariable UUID id) {
        LoanApplicationDTO loanApplicationDTO = loanApplicationService.getLoanApplication(id);
        return ResponseEntity.ok(loanApplicationDTO);
    }
    
    /**
     * Gets loan applications by client ID.
     *
     * @param clientId The client ID
     * @return The loan application DTOs
     */
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<LoanApplicationDTO>> getLoanApplicationsByClientId(@PathVariable UUID clientId) {
        List<LoanApplicationDTO> loanApplicationDTOs = loanApplicationService.getLoanApplicationsByClientId(clientId);
        return ResponseEntity.ok(loanApplicationDTOs);
    }
    
    /**
     * Gets loan applications by advisor ID.
     *
     * @param advisorId The advisor ID
     * @return The loan application DTOs
     */
    @GetMapping("/advisor/{advisorId}")
    public ResponseEntity<List<LoanApplicationDTO>> getLoanApplicationsByAdvisorId(@PathVariable String advisorId) {
        List<LoanApplicationDTO> loanApplicationDTOs = loanApplicationService.getLoanApplicationsByAdvisorId(advisorId);
        return ResponseEntity.ok(loanApplicationDTOs);
    }
    
    /**
     * Gets loan applications by status.
     *
     * @param status The status
     * @return The loan application DTOs
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<LoanApplicationDTO>> getLoanApplicationsByStatus(@PathVariable LoanStatus status) {
        List<LoanApplicationDTO> loanApplicationDTOs = loanApplicationService.getLoanApplicationsByStatus(status);
        return ResponseEntity.ok(loanApplicationDTOs);
    }
    
    /**
     * Updates loan terms.
     *
     * @param id The loan application ID
     * @param command The update loan terms command
     * @return The updated loan application DTO
     */
    @PutMapping("/{id}/terms")
    public ResponseEntity<LoanApplicationDTO> updateLoanTerms(@PathVariable UUID id, @Valid @RequestBody UpdateLoanTermsCommand command) {
        command.setLoanApplicationId(id);
        LoanApplicationDTO loanApplicationDTO = loanApplicationService.updateLoanTerms(command);
        return ResponseEntity.ok(loanApplicationDTO);
    }
    
    /**
     * Adds a collateral to a loan application.
     *
     * @param id The loan application ID
     * @param command The add collateral command
     * @return The updated loan application DTO
     */
    @PostMapping("/{id}/collaterals")
    public ResponseEntity<LoanApplicationDTO> addCollateral(@PathVariable UUID id, @Valid @RequestBody AddCollateralCommand command) {
        command.setLoanApplicationId(id);
        LoanApplicationDTO loanApplicationDTO = loanApplicationService.addCollateral(command);
        return ResponseEntity.ok(loanApplicationDTO);
    }
    
    /**
     * Updates a collateral valuation.
     *
     * @param id The loan application ID
     * @param collateralId The collateral ID
     * @param command The update collateral valuation command
     * @return The updated loan application DTO
     */
    @PutMapping("/{id}/collaterals/{collateralId}/valuation")
    public ResponseEntity<LoanApplicationDTO> updateCollateralValuation(
            @PathVariable UUID id,
            @PathVariable UUID collateralId,
            @Valid @RequestBody UpdateCollateralValuationCommand command) {
        command.setLoanApplicationId(id);
        command.setCollateralId(collateralId);
        LoanApplicationDTO loanApplicationDTO = loanApplicationService.updateCollateralValuation(command);
        return ResponseEntity.ok(loanApplicationDTO);
    }
    
    /**
     * Verifies a collateral.
     *
     * @param id The loan application ID
     * @param collateralId The collateral ID
     * @return The updated loan application DTO
     */
    @PutMapping("/{id}/collaterals/{collateralId}/verify")
    public ResponseEntity<LoanApplicationDTO> verifyCollateral(@PathVariable UUID id, @PathVariable UUID collateralId) {
        LoanApplicationDTO loanApplicationDTO = loanApplicationService.verifyCollateral(id, collateralId);
        return ResponseEntity.ok(loanApplicationDTO);
    }
    
    /**
     * Submits a loan application for review.
     *
     * @param id The loan application ID
     * @return The updated loan application DTO
     */
    @PutMapping("/{id}/submit")
    public ResponseEntity<LoanApplicationDTO> submitLoanApplication(@PathVariable UUID id) {
        LoanApplicationDTO loanApplicationDTO = loanApplicationService.submitLoanApplication(id);
        return ResponseEntity.ok(loanApplicationDTO);
    }
    
    /**
     * Starts the review of a loan application.
     *
     * @param id The loan application ID
     * @param reviewerId The reviewer ID
     * @return The updated loan application DTO
     */
    @PutMapping("/{id}/review")
    public ResponseEntity<LoanApplicationDTO> startReview(@PathVariable UUID id, @RequestParam String reviewerId) {
        LoanApplicationDTO loanApplicationDTO = loanApplicationService.startReview(id, reviewerId);
        return ResponseEntity.ok(loanApplicationDTO);
    }
    
    /**
     * Overrides a validation rule.
     *
     * @param id The loan application ID
     * @param command The override validation rule command
     * @return The updated loan application DTO
     */
    @PutMapping("/{id}/override-validation")
    public ResponseEntity<LoanApplicationDTO> overrideValidationRule(@PathVariable UUID id, @Valid @RequestBody OverrideValidationRuleCommand command) {
        command.setLoanApplicationId(id);
        LoanApplicationDTO loanApplicationDTO = loanApplicationService.overrideValidationRule(command);
        return ResponseEntity.ok(loanApplicationDTO);
    }
    
    /**
     * Approves a loan application.
     *
     * @param id The loan application ID
     * @param approvedBy The user who approved the application
     * @param notes The approval notes
     * @return The updated loan application DTO
     */
    @PutMapping("/{id}/approve")
    public ResponseEntity<LoanApplicationDTO> approveLoanApplication(
            @PathVariable UUID id,
            @RequestParam String approvedBy,
            @RequestParam String notes) {
        LoanApplicationDTO loanApplicationDTO = loanApplicationService.approveLoanApplication(id, approvedBy, notes);
        return ResponseEntity.ok(loanApplicationDTO);
    }
    
    /**
     * Rejects a loan application.
     *
     * @param id The loan application ID
     * @param rejectedBy The user who rejected the application
     * @param reason The rejection reason
     * @return The updated loan application DTO
     */
    @PutMapping("/{id}/reject")
    public ResponseEntity<LoanApplicationDTO> rejectLoanApplication(
            @PathVariable UUID id,
            @RequestParam String rejectedBy,
            @RequestParam String reason) {
        LoanApplicationDTO loanApplicationDTO = loanApplicationService.rejectLoanApplication(id, rejectedBy, reason);
        return ResponseEntity.ok(loanApplicationDTO);
    }
    
    /**
     * Requests additional documents for a loan application.
     *
     * @param id The loan application ID
     * @param requestedBy The user who requested the documents
     * @return The updated loan application DTO
     */
    @PutMapping("/{id}/request-documents")
    public ResponseEntity<LoanApplicationDTO> requestDocuments(
            @PathVariable UUID id,
            @RequestParam String requestedBy) {
        LoanApplicationDTO loanApplicationDTO = loanApplicationService.requestDocuments(id, requestedBy);
        return ResponseEntity.ok(loanApplicationDTO);
    }
    
    /**
     * Generates loan documents for a loan application.
     *
     * @param id The loan application ID
     * @param documentPackageId The document package ID
     * @param generatedBy The user who generated the documents
     * @return The updated loan application DTO
     */
    @PutMapping("/{id}/generate-documents")
    public ResponseEntity<LoanApplicationDTO> generateDocuments(
            @PathVariable UUID id,
            @RequestParam UUID documentPackageId,
            @RequestParam String generatedBy) {
        LoanApplicationDTO loanApplicationDTO = loanApplicationService.generateDocuments(id, documentPackageId, generatedBy);
        return ResponseEntity.ok(loanApplicationDTO);
    }
    
    /**
     * Activates a loan after document signing.
     *
     * @param id The loan application ID
     * @param activatedBy The user who activated the loan
     * @return The updated loan application DTO
     */
    @PutMapping("/{id}/activate")
    public ResponseEntity<LoanApplicationDTO> activateLoan(
            @PathVariable UUID id,
            @RequestParam String activatedBy) {
        LoanApplicationDTO loanApplicationDTO = loanApplicationService.activateLoan(id, activatedBy);
        return ResponseEntity.ok(loanApplicationDTO);
    }
    
    /**
     * Closes a loan after full repayment.
     *
     * @param id The loan application ID
     * @param closedBy The user who closed the loan
     * @return The updated loan application DTO
     */
    @PutMapping("/{id}/close")
    public ResponseEntity<LoanApplicationDTO> closeLoan(
            @PathVariable UUID id,
            @RequestParam String closedBy) {
        LoanApplicationDTO loanApplicationDTO = loanApplicationService.closeLoan(id, closedBy);
        return ResponseEntity.ok(loanApplicationDTO);
    }
    
    /**
     * Marks a loan as defaulted.
     *
     * @param id The loan application ID
     * @param defaultedBy The user who marked the loan as defaulted
     * @return The updated loan application DTO
     */
    @PutMapping("/{id}/default")
    public ResponseEntity<LoanApplicationDTO> markAsDefaulted(
            @PathVariable UUID id,
            @RequestParam String defaultedBy) {
        LoanApplicationDTO loanApplicationDTO = loanApplicationService.markAsDefaulted(id, defaultedBy);
        return ResponseEntity.ok(loanApplicationDTO);
    }
}
