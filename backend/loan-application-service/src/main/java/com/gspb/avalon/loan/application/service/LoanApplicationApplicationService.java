package com.gspb.avalon.loan.application.service;

import com.gspb.avalon.loan.application.dto.*;
import com.gspb.avalon.loan.domain.model.*;
import com.gspb.avalon.loan.domain.repository.LoanApplicationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Application service for loan applications.
 */
@Service
@RequiredArgsConstructor
public class LoanApplicationApplicationService {
    
    private final LoanApplicationRepository loanApplicationRepository;
    private final LoanApplicationDTOMapper mapper;
    private final ValidationService validationService;
    
    /**
     * Creates a new loan application.
     *
     * @param command The create loan application command
     * @return The created loan application DTO
     */
    @Transactional
    public LoanApplicationDTO createLoanApplication(CreateLoanApplicationCommand command) {
        LoanApplication loanApplication = new LoanApplication(
                UUID.randomUUID(),
                command.getClientId(),
                command.getAdvisorId(),
                command.getLoanType()
        );
        
        if (command.getPurpose() != null) {
            loanApplication.updatePurpose(command.getPurpose());
        }
        
        LoanApplication savedLoanApplication = loanApplicationRepository.save(loanApplication);
        return mapper.toDTO(savedLoanApplication);
    }
    
    /**
     * Updates loan terms.
     *
     * @param command The update loan terms command
     * @return The updated loan application DTO
     */
    @Transactional
    public LoanApplicationDTO updateLoanTerms(UpdateLoanTermsCommand command) {
        LoanApplication loanApplication = findLoanApplicationById(command.getLoanApplicationId());
        
        loanApplication.updateTerms(
                command.getAmount(),
                command.getCurrency(),
                command.getTermInMonths(),
                command.getInterestRate(),
                command.getInterestRateType(),
                command.getPaymentFrequency(),
                command.getStartDate(),
                command.getMaturityDate(),
                command.getOriginationFee(),
                command.isEarlyRepaymentAllowed(),
                command.getEarlyRepaymentFee()
        );
        
        LoanApplication savedLoanApplication = loanApplicationRepository.save(loanApplication);
        return mapper.toDTO(savedLoanApplication);
    }
    
    /**
     * Adds a collateral to a loan application.
     *
     * @param command The add collateral command
     * @return The updated loan application DTO
     */
    @Transactional
    public LoanApplicationDTO addCollateral(AddCollateralCommand command) {
        LoanApplication loanApplication = findLoanApplicationById(command.getLoanApplicationId());
        
        Collateral collateral = mapper.createCollateralFromCommand(command);
        loanApplication.addCollateral(collateral);
        
        LoanApplication savedLoanApplication = loanApplicationRepository.save(loanApplication);
        return mapper.toDTO(savedLoanApplication);
    }
    
    /**
     * Updates a collateral valuation.
     *
     * @param command The update collateral valuation command
     * @return The updated loan application DTO
     */
    @Transactional
    public LoanApplicationDTO updateCollateralValuation(UpdateCollateralValuationCommand command) {
        LoanApplication loanApplication = findLoanApplicationById(command.getLoanApplicationId());
        
        Collateral collateral = loanApplication.getCollaterals().stream()
                .filter(c -> c.getId().equals(command.getCollateralId()))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Collateral not found: " + command.getCollateralId()));
        
        collateral.updateValuation(
                command.getAppraiserValue(),
                command.getAppraiserName(),
                command.getValuationDate(),
                command.getLoanToValueRatio()
        );
        
        LoanApplication savedLoanApplication = loanApplicationRepository.save(loanApplication);
        return mapper.toDTO(savedLoanApplication);
    }
    
    /**
     * Verifies a collateral.
     *
     * @param loanApplicationId The loan application ID
     * @param collateralId The collateral ID
     * @return The updated loan application DTO
     */
    @Transactional
    public LoanApplicationDTO verifyCollateral(UUID loanApplicationId, UUID collateralId) {
        LoanApplication loanApplication = findLoanApplicationById(loanApplicationId);
        
        Collateral collateral = loanApplication.getCollaterals().stream()
                .filter(c -> c.getId().equals(collateralId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Collateral not found: " + collateralId));
        
        collateral.verify();
        
        LoanApplication savedLoanApplication = loanApplicationRepository.save(loanApplication);
        return mapper.toDTO(savedLoanApplication);
    }
    
    /**
     * Submits a loan application for review.
     *
     * @param loanApplicationId The loan application ID
     * @return The updated loan application DTO
     */
    @Transactional
    public LoanApplicationDTO submitLoanApplication(UUID loanApplicationId) {
        LoanApplication loanApplication = findLoanApplicationById(loanApplicationId);
        
        loanApplication.submit();
        
        List<ValidationRuleResult> validationResults = validationService.validateLoanApplication(loanApplication);
        
        validationResults.forEach(loanApplication::addValidationResult);
        
        LoanApplication savedLoanApplication = loanApplicationRepository.save(loanApplication);
        return mapper.toDTO(savedLoanApplication);
    }
    
    /**
     * Starts the review of a loan application.
     *
     * @param loanApplicationId The loan application ID
     * @param reviewerId The reviewer ID
     * @return The updated loan application DTO
     */
    @Transactional
    public LoanApplicationDTO startReview(UUID loanApplicationId, String reviewerId) {
        LoanApplication loanApplication = findLoanApplicationById(loanApplicationId);
        
        loanApplication.startReview(reviewerId);
        
        LoanApplication savedLoanApplication = loanApplicationRepository.save(loanApplication);
        return mapper.toDTO(savedLoanApplication);
    }
    
    /**
     * Overrides a validation rule.
     *
     * @param command The override validation rule command
     * @return The updated loan application DTO
     */
    @Transactional
    public LoanApplicationDTO overrideValidationRule(OverrideValidationRuleCommand command) {
        LoanApplication loanApplication = findLoanApplicationById(command.getLoanApplicationId());
        
        loanApplication.overrideValidationRule(
                command.getRuleId(),
                command.getReason(),
                command.getOverriddenBy()
        );
        
        LoanApplication savedLoanApplication = loanApplicationRepository.save(loanApplication);
        return mapper.toDTO(savedLoanApplication);
    }
    
    /**
     * Approves a loan application.
     *
     * @param loanApplicationId The loan application ID
     * @param approvedBy The user who approved the application
     * @param notes The approval notes
     * @return The updated loan application DTO
     */
    @Transactional
    public LoanApplicationDTO approveLoanApplication(UUID loanApplicationId, String approvedBy, String notes) {
        LoanApplication loanApplication = findLoanApplicationById(loanApplicationId);
        
        loanApplication.approve(approvedBy, notes);
        
        LoanApplication savedLoanApplication = loanApplicationRepository.save(loanApplication);
        return mapper.toDTO(savedLoanApplication);
    }
    
    /**
     * Rejects a loan application.
     *
     * @param loanApplicationId The loan application ID
     * @param rejectedBy The user who rejected the application
     * @param reason The rejection reason
     * @return The updated loan application DTO
     */
    @Transactional
    public LoanApplicationDTO rejectLoanApplication(UUID loanApplicationId, String rejectedBy, String reason) {
        LoanApplication loanApplication = findLoanApplicationById(loanApplicationId);
        
        loanApplication.reject(rejectedBy, reason);
        
        LoanApplication savedLoanApplication = loanApplicationRepository.save(loanApplication);
        return mapper.toDTO(savedLoanApplication);
    }
    
    /**
     * Requests additional documents for a loan application.
     *
     * @param loanApplicationId The loan application ID
     * @param requestedBy The user who requested the documents
     * @return The updated loan application DTO
     */
    @Transactional
    public LoanApplicationDTO requestDocuments(UUID loanApplicationId, String requestedBy) {
        LoanApplication loanApplication = findLoanApplicationById(loanApplicationId);
        
        loanApplication.requestDocuments(requestedBy);
        
        LoanApplication savedLoanApplication = loanApplicationRepository.save(loanApplication);
        return mapper.toDTO(savedLoanApplication);
    }
    
    /**
     * Generates loan documents for a loan application.
     *
     * @param loanApplicationId The loan application ID
     * @param documentPackageId The document package ID
     * @param generatedBy The user who generated the documents
     * @return The updated loan application DTO
     */
    @Transactional
    public LoanApplicationDTO generateDocuments(UUID loanApplicationId, UUID documentPackageId, String generatedBy) {
        LoanApplication loanApplication = findLoanApplicationById(loanApplicationId);
        
        loanApplication.generateDocuments(documentPackageId, generatedBy);
        
        LoanApplication savedLoanApplication = loanApplicationRepository.save(loanApplication);
        return mapper.toDTO(savedLoanApplication);
    }
    
    /**
     * Activates a loan after document signing.
     *
     * @param loanApplicationId The loan application ID
     * @param activatedBy The user who activated the loan
     * @return The updated loan application DTO
     */
    @Transactional
    public LoanApplicationDTO activateLoan(UUID loanApplicationId, String activatedBy) {
        LoanApplication loanApplication = findLoanApplicationById(loanApplicationId);
        
        loanApplication.activate(activatedBy);
        
        LoanApplication savedLoanApplication = loanApplicationRepository.save(loanApplication);
        return mapper.toDTO(savedLoanApplication);
    }
    
    /**
     * Closes a loan after full repayment.
     *
     * @param loanApplicationId The loan application ID
     * @param closedBy The user who closed the loan
     * @return The updated loan application DTO
     */
    @Transactional
    public LoanApplicationDTO closeLoan(UUID loanApplicationId, String closedBy) {
        LoanApplication loanApplication = findLoanApplicationById(loanApplicationId);
        
        loanApplication.close(closedBy);
        
        LoanApplication savedLoanApplication = loanApplicationRepository.save(loanApplication);
        return mapper.toDTO(savedLoanApplication);
    }
    
    /**
     * Marks a loan as defaulted.
     *
     * @param loanApplicationId The loan application ID
     * @param defaultedBy The user who marked the loan as defaulted
     * @return The updated loan application DTO
     */
    @Transactional
    public LoanApplicationDTO markAsDefaulted(UUID loanApplicationId, String defaultedBy) {
        LoanApplication loanApplication = findLoanApplicationById(loanApplicationId);
        
        loanApplication.markAsDefaulted(defaultedBy);
        
        LoanApplication savedLoanApplication = loanApplicationRepository.save(loanApplication);
        return mapper.toDTO(savedLoanApplication);
    }
    
    /**
     * Gets a loan application by ID.
     *
     * @param loanApplicationId The loan application ID
     * @return The loan application DTO
     */
    @Transactional(readOnly = true)
    public LoanApplicationDTO getLoanApplication(UUID loanApplicationId) {
        LoanApplication loanApplication = findLoanApplicationById(loanApplicationId);
        return mapper.toDTO(loanApplication);
    }
    
    /**
     * Gets loan applications by client ID.
     *
     * @param clientId The client ID
     * @return The loan application DTOs
     */
    @Transactional(readOnly = true)
    public List<LoanApplicationDTO> getLoanApplicationsByClientId(UUID clientId) {
        List<LoanApplication> loanApplications = loanApplicationRepository.findByClientId(clientId);
        return loanApplications.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Gets loan applications by advisor ID.
     *
     * @param advisorId The advisor ID
     * @return The loan application DTOs
     */
    @Transactional(readOnly = true)
    public List<LoanApplicationDTO> getLoanApplicationsByAdvisorId(String advisorId) {
        List<LoanApplication> loanApplications = loanApplicationRepository.findByAdvisorId(advisorId);
        return loanApplications.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Gets loan applications by status.
     *
     * @param status The status
     * @return The loan application DTOs
     */
    @Transactional(readOnly = true)
    public List<LoanApplicationDTO> getLoanApplicationsByStatus(LoanStatus status) {
        List<LoanApplication> loanApplications = loanApplicationRepository.findByStatus(status);
        return loanApplications.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Gets loan applications by client ID and status.
     *
     * @param clientId The client ID
     * @param status The status
     * @return The loan application DTOs
     */
    @Transactional(readOnly = true)
    public List<LoanApplicationDTO> getLoanApplicationsByClientIdAndStatus(UUID clientId, LoanStatus status) {
        List<LoanApplication> loanApplications = loanApplicationRepository.findByClientIdAndStatus(clientId, status);
        return loanApplications.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Gets loan applications by advisor ID and status.
     *
     * @param advisorId The advisor ID
     * @param status The status
     * @return The loan application DTOs
     */
    @Transactional(readOnly = true)
    public List<LoanApplicationDTO> getLoanApplicationsByAdvisorIdAndStatus(String advisorId, LoanStatus status) {
        List<LoanApplication> loanApplications = loanApplicationRepository.findByAdvisorIdAndStatus(advisorId, status);
        return loanApplications.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Finds a loan application by ID.
     *
     * @param loanApplicationId The loan application ID
     * @return The loan application
     * @throws EntityNotFoundException if the loan application is not found
     */
    private LoanApplication findLoanApplicationById(UUID loanApplicationId) {
        return loanApplicationRepository.findById(loanApplicationId)
                .orElseThrow(() -> new EntityNotFoundException("Loan application not found: " + loanApplicationId));
    }
}
