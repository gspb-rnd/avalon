package com.gspb.avalon.loan.infrastructure.persistence;

import com.gspb.avalon.loan.domain.model.LoanStatus;
import com.gspb.avalon.loan.domain.model.LoanType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Builder class for LoanApplicationJpaEntity.
 */
public class LoanApplicationJpaEntityBuilder {
    
    private UUID id;
    private UUID clientId;
    private String advisorId;
    private LoanType loanType;
    private LoanStatus status;
    private String purpose;
    private String rejectionReason;
    private String approvalNotes;
    private UUID documentPackageId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LoanTermsEmbeddable terms;
    private List<CollateralJpaEntity> collaterals = new ArrayList<>();
    private List<ValidationRuleResultJpaEntity> validationResults = new ArrayList<>();
    
    public LoanApplicationJpaEntityBuilder id(UUID id) {
        this.id = id;
        return this;
    }
    
    public LoanApplicationJpaEntityBuilder clientId(UUID clientId) {
        this.clientId = clientId;
        return this;
    }
    
    public LoanApplicationJpaEntityBuilder advisorId(String advisorId) {
        this.advisorId = advisorId;
        return this;
    }
    
    public LoanApplicationJpaEntityBuilder loanType(LoanType loanType) {
        this.loanType = loanType;
        return this;
    }
    
    public LoanApplicationJpaEntityBuilder status(LoanStatus status) {
        this.status = status;
        return this;
    }
    
    public LoanApplicationJpaEntityBuilder purpose(String purpose) {
        this.purpose = purpose;
        return this;
    }
    
    public LoanApplicationJpaEntityBuilder rejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
        return this;
    }
    
    public LoanApplicationJpaEntityBuilder approvalNotes(String approvalNotes) {
        this.approvalNotes = approvalNotes;
        return this;
    }
    
    public LoanApplicationJpaEntityBuilder documentPackageId(UUID documentPackageId) {
        this.documentPackageId = documentPackageId;
        return this;
    }
    
    public LoanApplicationJpaEntityBuilder createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }
    
    public LoanApplicationJpaEntityBuilder updatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
    
    public LoanApplicationJpaEntityBuilder terms(LoanTermsEmbeddable terms) {
        this.terms = terms;
        return this;
    }
    
    public LoanApplicationJpaEntityBuilder collaterals(List<CollateralJpaEntity> collaterals) {
        this.collaterals = collaterals;
        return this;
    }
    
    public LoanApplicationJpaEntityBuilder validationResults(List<ValidationRuleResultJpaEntity> validationResults) {
        this.validationResults = validationResults;
        return this;
    }
    
    public LoanApplicationJpaEntity build() {
        LoanApplicationJpaEntity entity = new LoanApplicationJpaEntity();
        entity.setId(id);
        entity.setClientId(clientId);
        entity.setAdvisorId(advisorId);
        entity.setLoanType(loanType);
        entity.setStatus(status);
        entity.setPurpose(purpose);
        entity.setRejectionReason(rejectionReason);
        entity.setApprovalNotes(approvalNotes);
        entity.setDocumentPackageId(documentPackageId);
        entity.setCreatedAt(createdAt);
        entity.setUpdatedAt(updatedAt);
        entity.setTerms(terms);
        entity.setCollaterals(collaterals);
        entity.setValidationResults(validationResults);
        return entity;
    }
}
