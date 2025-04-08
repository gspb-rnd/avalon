package com.gspb.avalon.loan.domain.model;

import com.gspb.avalon.shared.domain.AggregateRoot;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Aggregate root representing a loan application in the system.
 */
@Getter
public class LoanApplication extends AggregateRoot<UUID> {
    
    private final UUID clientId;
    private final String advisorId;
    private final LoanType loanType;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LoanStatus status;
    private String purpose;
    private LoanTerms terms;
    private final List<Collateral> collaterals = new ArrayList<>();
    private final List<ValidationRuleResult> validationResults = new ArrayList<>();
    private String rejectionReason;
    private String approvalNotes;
    private UUID documentPackageId;
    
    /**
     * Gets the client ID.
     *
     * @return The client ID
     */
    public UUID getClientId() {
        return clientId;
    }
    
    /**
     * Gets the advisor ID.
     *
     * @return The advisor ID
     */
    public String getAdvisorId() {
        return advisorId;
    }
    
    /**
     * Gets the loan type.
     *
     * @return The loan type
     */
    public LoanType getLoanType() {
        return loanType;
    }
    
    /**
     * Gets the purpose.
     *
     * @return The purpose
     */
    public String getPurpose() {
        return purpose;
    }
    
    /**
     * Gets the rejection reason.
     *
     * @return The rejection reason
     */
    public String getRejectionReason() {
        return rejectionReason;
    }
    
    /**
     * Gets the approval notes.
     *
     * @return The approval notes
     */
    public String getApprovalNotes() {
        return approvalNotes;
    }
    
    /**
     * Gets the document package ID.
     *
     * @return The document package ID
     */
    public UUID getDocumentPackageId() {
        return documentPackageId;
    }
    
    /**
     * Gets the created at timestamp.
     *
     * @return The created at timestamp
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    /**
     * Gets the updated at timestamp.
     *
     * @return The updated at timestamp
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    /**
     * Gets the loan terms.
     *
     * @return The loan terms
     */
    public LoanTerms getTerms() {
        return terms;
    }
    
    /**
     * Gets the loan application status.
     *
     * @return The loan application status
     */
    public LoanStatus getStatus() {
        return status;
    }
    
    /**
     * Creates a new loan application.
     *
     * @param id The loan application ID
     * @param clientId The client ID
     * @param advisorId The advisor ID
     * @param loanType The loan type
     */
    public LoanApplication(UUID id, UUID clientId, String advisorId, LoanType loanType) {
        super(id);
        this.clientId = clientId;
        this.advisorId = advisorId;
        this.loanType = loanType;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
        this.status = LoanStatus.DRAFT;
        
        registerEvent(new LoanApplicationCreatedEvent(id, clientId, loanType, advisorId));
    }
    
    /**
     * Updates the loan application purpose.
     *
     * @param purpose The purpose
     */
    public void updatePurpose(String purpose) {
        this.purpose = purpose;
        this.updatedAt = LocalDateTime.now();
    }
    
    /**
     * Updates the loan terms.
     *
     * @param amount The loan amount
     * @param currency The loan currency
     * @param termInMonths The loan term in months
     * @param interestRate The interest rate
     * @param interestRateType The interest rate type
     * @param paymentFrequency The payment frequency
     * @param startDate The start date
     * @param maturityDate The maturity date
     * @param originationFee The origination fee
     * @param earlyRepaymentAllowed Whether early repayment is allowed
     * @param earlyRepaymentFee The early repayment fee
     */
    public void updateTerms(BigDecimal amount, String currency, int termInMonths, BigDecimal interestRate,
                           InterestRateType interestRateType, PaymentFrequency paymentFrequency,
                           LocalDate startDate, LocalDate maturityDate, BigDecimal originationFee,
                           boolean earlyRepaymentAllowed, BigDecimal earlyRepaymentFee) {
        this.terms = new LoanTerms(amount, currency, termInMonths, interestRate, interestRateType, paymentFrequency,
                                  startDate, maturityDate, originationFee, earlyRepaymentAllowed, earlyRepaymentFee);
        this.updatedAt = LocalDateTime.now();
    }
    
    /**
     * Adds a collateral to the loan application.
     *
     * @param collateral The collateral to add
     */
    public void addCollateral(Collateral collateral) {
        this.collaterals.add(collateral);
        this.updatedAt = LocalDateTime.now();
        
        registerEvent(new CollateralAddedEvent(this.getId(), collateral.getId(), collateral.getType(), collateral.getEstimatedValue()));
    }
    
    /**
     * Submits the loan application for review.
     */
    public void submit() {
        if (this.status != LoanStatus.DRAFT) {
            throw new IllegalStateException("Loan application must be in DRAFT status to be submitted");
        }
        
        if (this.terms == null) {
            throw new IllegalStateException("Loan terms must be set before submission");
        }
        
        if (this.collaterals.isEmpty()) {
            throw new IllegalStateException("At least one collateral must be added before submission");
        }
        
        LoanStatus oldStatus = this.status;
        this.status = LoanStatus.SUBMITTED;
        this.updatedAt = LocalDateTime.now();
        
        registerEvent(new LoanApplicationStatusChangedEvent(this.getId(), oldStatus, this.status, this.advisorId));
    }
    
    /**
     * Starts the review of the loan application.
     *
     * @param reviewerId The reviewer ID
     */
    public void startReview(String reviewerId) {
        if (this.status != LoanStatus.SUBMITTED) {
            throw new IllegalStateException("Loan application must be in SUBMITTED status to start review");
        }
        
        LoanStatus oldStatus = this.status;
        this.status = LoanStatus.UNDER_REVIEW;
        this.updatedAt = LocalDateTime.now();
        
        registerEvent(new LoanApplicationStatusChangedEvent(this.getId(), oldStatus, this.status, reviewerId));
    }
    
    /**
     * Adds a validation result to the loan application.
     *
     * @param validationResult The validation result to add
     */
    public void addValidationResult(ValidationRuleResult validationResult) {
        this.validationResults.add(validationResult);
        this.updatedAt = LocalDateTime.now();
    }
    
    /**
     * Overrides a validation rule.
     *
     * @param ruleId The rule ID
     * @param reason The override reason
     * @param overriddenBy The user who overrode the rule
     */
    public void overrideValidationRule(String ruleId, String reason, String overriddenBy) {
        ValidationRuleResult result = this.validationResults.stream()
                .filter(r -> r.getRuleId().equals(ruleId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Validation rule not found: " + ruleId));
        
        result.override(reason, overriddenBy);
        this.updatedAt = LocalDateTime.now();
    }
    
    /**
     * Approves the loan application.
     *
     * @param approvedBy The user who approved the application
     * @param notes The approval notes
     */
    public void approve(String approvedBy, String notes) {
        if (this.status != LoanStatus.UNDER_REVIEW) {
            throw new IllegalStateException("Loan application must be in UNDER_REVIEW status to be approved");
        }
        
        boolean allRulesPassed = this.validationResults.stream()
                .allMatch(ValidationRuleResult::isEffectivelyPassed);
        
        if (!allRulesPassed) {
            throw new IllegalStateException("All validation rules must be passed or overridden before approval");
        }
        
        LoanStatus oldStatus = this.status;
        this.status = LoanStatus.APPROVED;
        this.approvalNotes = notes;
        this.updatedAt = LocalDateTime.now();
        
        registerEvent(new LoanApplicationStatusChangedEvent(this.getId(), oldStatus, this.status, approvedBy));
    }
    
    /**
     * Rejects the loan application.
     *
     * @param rejectedBy The user who rejected the application
     * @param reason The rejection reason
     */
    public void reject(String rejectedBy, String reason) {
        if (this.status != LoanStatus.UNDER_REVIEW) {
            throw new IllegalStateException("Loan application must be in UNDER_REVIEW status to be rejected");
        }
        
        LoanStatus oldStatus = this.status;
        this.status = LoanStatus.REJECTED;
        this.rejectionReason = reason;
        this.updatedAt = LocalDateTime.now();
        
        registerEvent(new LoanApplicationStatusChangedEvent(this.getId(), oldStatus, this.status, rejectedBy));
    }
    
    /**
     * Requests additional documents for the loan application.
     *
     * @param requestedBy The user who requested the documents
     */
    public void requestDocuments(String requestedBy) {
        if (this.status != LoanStatus.UNDER_REVIEW) {
            throw new IllegalStateException("Loan application must be in UNDER_REVIEW status to request documents");
        }
        
        LoanStatus oldStatus = this.status;
        this.status = LoanStatus.PENDING_DOCUMENTS;
        this.updatedAt = LocalDateTime.now();
        
        registerEvent(new LoanApplicationStatusChangedEvent(this.getId(), oldStatus, this.status, requestedBy));
    }
    
    /**
     * Generates loan documents for the loan application.
     *
     * @param documentPackageId The document package ID
     * @param generatedBy The user who generated the documents
     */
    public void generateDocuments(UUID documentPackageId, String generatedBy) {
        if (this.status != LoanStatus.APPROVED) {
            throw new IllegalStateException("Loan application must be in APPROVED status to generate documents");
        }
        
        LoanStatus oldStatus = this.status;
        this.status = LoanStatus.PENDING_SIGNATURE;
        this.documentPackageId = documentPackageId;
        this.updatedAt = LocalDateTime.now();
        
        registerEvent(new LoanApplicationStatusChangedEvent(this.getId(), oldStatus, this.status, generatedBy));
    }
    
    /**
     * Activates the loan after document signing.
     *
     * @param activatedBy The user who activated the loan
     */
    public void activate(String activatedBy) {
        if (this.status != LoanStatus.PENDING_SIGNATURE) {
            throw new IllegalStateException("Loan application must be in PENDING_SIGNATURE status to be activated");
        }
        
        LoanStatus oldStatus = this.status;
        this.status = LoanStatus.ACTIVE;
        this.updatedAt = LocalDateTime.now();
        
        registerEvent(new LoanApplicationStatusChangedEvent(this.getId(), oldStatus, this.status, activatedBy));
    }
    
    /**
     * Closes the loan after full repayment.
     *
     * @param closedBy The user who closed the loan
     */
    public void close(String closedBy) {
        if (this.status != LoanStatus.ACTIVE) {
            throw new IllegalStateException("Loan application must be in ACTIVE status to be closed");
        }
        
        LoanStatus oldStatus = this.status;
        this.status = LoanStatus.CLOSED;
        this.updatedAt = LocalDateTime.now();
        
        registerEvent(new LoanApplicationStatusChangedEvent(this.getId(), oldStatus, this.status, closedBy));
    }
    
    /**
     * Marks the loan as defaulted.
     *
     * @param defaultedBy The user who marked the loan as defaulted
     */
    public void markAsDefaulted(String defaultedBy) {
        if (this.status != LoanStatus.ACTIVE) {
            throw new IllegalStateException("Loan application must be in ACTIVE status to be marked as defaulted");
        }
        
        LoanStatus oldStatus = this.status;
        this.status = LoanStatus.DEFAULTED;
        this.updatedAt = LocalDateTime.now();
        
        registerEvent(new LoanApplicationStatusChangedEvent(this.getId(), oldStatus, this.status, defaultedBy));
    }
    
    /**
     * Gets the collaterals for the loan application.
     *
     * @return An unmodifiable list of collaterals
     */
    public List<Collateral> getCollaterals() {
        return Collections.unmodifiableList(collaterals);
    }
    
    /**
     * Gets the validation results for the loan application.
     *
     * @return An unmodifiable list of validation results
     */
    public List<ValidationRuleResult> getValidationResults() {
        return Collections.unmodifiableList(validationResults);
    }
    
    /**
     * Calculates the total collateral value.
     *
     * @return The total collateral value
     */
    public BigDecimal calculateTotalCollateralValue() {
        return this.collaterals.stream()
                .map(Collateral::getCurrentValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    /**
     * Calculates the loan-to-value ratio.
     *
     * @return The loan-to-value ratio
     */
    public BigDecimal calculateLoanToValueRatio() {
        if (this.terms == null) {
            return BigDecimal.ZERO;
        }
        
        BigDecimal totalCollateralValue = calculateTotalCollateralValue();
        
        if (totalCollateralValue.equals(BigDecimal.ZERO)) {
            return BigDecimal.ZERO;
        }
        
        return this.terms.getAmount().divide(totalCollateralValue, 4, BigDecimal.ROUND_HALF_UP)
                .multiply(BigDecimal.valueOf(100));
    }
    
    /**
     * Checks if the loan application has a collateral of the specified type.
     *
     * @param type The collateral type to check for
     * @return True if the loan application has a collateral of the specified type, false otherwise
     */
    public boolean hasCollateralOfType(CollateralType type) {
        return this.collaterals.stream()
                .anyMatch(collateral -> collateral.getType() == type);
    }
}
