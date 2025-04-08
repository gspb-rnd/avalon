package com.gspb.avalon.loan.infrastructure.persistence;

import com.gspb.avalon.loan.domain.model.LoanStatus;
import com.gspb.avalon.loan.domain.model.LoanType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * JPA entity for loan application.
 */
@Entity
@Table(name = "loan_applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class LoanApplicationJpaEntity {
    
    @Id
    private UUID id;
    
    @Column(name = "client_id", nullable = false)
    private UUID clientId;
    
    @Column(name = "advisor_id", nullable = false)
    private String advisorId;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "loan_type", nullable = false)
    private LoanType loanType;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private LoanStatus status;
    
    @Column(name = "purpose")
    private String purpose;
    
    @Column(name = "rejection_reason")
    private String rejectionReason;
    
    @Column(name = "approval_notes")
    private String approvalNotes;
    
    @Column(name = "document_package_id")
    private UUID documentPackageId;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    
    @Embedded
    private LoanTermsEmbeddable terms;
    
    @OneToMany(mappedBy = "loanApplication", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CollateralJpaEntity> collaterals = new ArrayList<>();
    
    @OneToMany(mappedBy = "loanApplication", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ValidationRuleResultJpaEntity> validationResults = new ArrayList<>();
    
    public static LoanApplicationJpaEntityBuilder builder() {
        return new LoanApplicationJpaEntityBuilder();
    }
    
    public LoanTermsEmbeddable getTerms() {
        return terms;
    }
    
    public List<CollateralJpaEntity> getCollaterals() {
        return collaterals;
    }
    
    public List<ValidationRuleResultJpaEntity> getValidationResults() {
        return validationResults;
    }
    
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public UUID getClientId() {
        return clientId;
    }
    
    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }
    
    public String getAdvisorId() {
        return advisorId;
    }
    
    public void setAdvisorId(String advisorId) {
        this.advisorId = advisorId;
    }
    
    public LoanType getLoanType() {
        return loanType;
    }
    
    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }
    
    public LoanStatus getStatus() {
        return status;
    }
    
    public void setStatus(LoanStatus status) {
        this.status = status;
    }
    
    public String getPurpose() {
        return purpose;
    }
    
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
    
    public String getRejectionReason() {
        return rejectionReason;
    }
    
    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }
    
    public String getApprovalNotes() {
        return approvalNotes;
    }
    
    public void setApprovalNotes(String approvalNotes) {
        this.approvalNotes = approvalNotes;
    }
    
    public UUID getDocumentPackageId() {
        return documentPackageId;
    }
    
    public void setDocumentPackageId(UUID documentPackageId) {
        this.documentPackageId = documentPackageId;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public void setTerms(LoanTermsEmbeddable terms) {
        this.terms = terms;
    }
    
    public void setCollaterals(List<CollateralJpaEntity> collaterals) {
        this.collaterals = collaterals;
    }
    
    public void setValidationResults(List<ValidationRuleResultJpaEntity> validationResults) {
        this.validationResults = validationResults;
    }
}
