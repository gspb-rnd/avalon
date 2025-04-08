package com.gspb.avalon.loan.application.dto;

import com.gspb.avalon.loan.domain.model.LoanStatus;
import com.gspb.avalon.loan.domain.model.LoanType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Builder class for LoanApplicationDTO.
 */
public class LoanApplicationDTOBuilder {
    
    private UUID id;
    private UUID clientId;
    private String advisorId;
    private LoanType loanType;
    private LoanStatus status;
    private String purpose;
    private LoanTermsDTO terms;
    private List<CollateralDTO> collaterals = new ArrayList<>();
    private List<ValidationRuleResultDTO> validationResults = new ArrayList<>();
    private String rejectionReason;
    private String approvalNotes;
    private UUID documentPackageId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public LoanApplicationDTOBuilder id(UUID id) {
        this.id = id;
        return this;
    }
    
    public LoanApplicationDTOBuilder clientId(UUID clientId) {
        this.clientId = clientId;
        return this;
    }
    
    public LoanApplicationDTOBuilder advisorId(String advisorId) {
        this.advisorId = advisorId;
        return this;
    }
    
    public LoanApplicationDTOBuilder loanType(LoanType loanType) {
        this.loanType = loanType;
        return this;
    }
    
    public LoanApplicationDTOBuilder status(LoanStatus status) {
        this.status = status;
        return this;
    }
    
    public LoanApplicationDTOBuilder purpose(String purpose) {
        this.purpose = purpose;
        return this;
    }
    
    public LoanApplicationDTOBuilder terms(LoanTermsDTO terms) {
        this.terms = terms;
        return this;
    }
    
    public LoanApplicationDTOBuilder collaterals(List<CollateralDTO> collaterals) {
        this.collaterals = collaterals;
        return this;
    }
    
    public LoanApplicationDTOBuilder validationResults(List<ValidationRuleResultDTO> validationResults) {
        this.validationResults = validationResults;
        return this;
    }
    
    public LoanApplicationDTOBuilder rejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
        return this;
    }
    
    public LoanApplicationDTOBuilder approvalNotes(String approvalNotes) {
        this.approvalNotes = approvalNotes;
        return this;
    }
    
    public LoanApplicationDTOBuilder documentPackageId(UUID documentPackageId) {
        this.documentPackageId = documentPackageId;
        return this;
    }
    
    public LoanApplicationDTOBuilder createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }
    
    public LoanApplicationDTOBuilder updatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
    
    public LoanApplicationDTO build() {
        LoanApplicationDTO loanApplicationDTO = new LoanApplicationDTO();
        loanApplicationDTO.setId(id);
        loanApplicationDTO.setClientId(clientId);
        loanApplicationDTO.setAdvisorId(advisorId);
        loanApplicationDTO.setLoanType(loanType);
        loanApplicationDTO.setStatus(status);
        loanApplicationDTO.setPurpose(purpose);
        loanApplicationDTO.setTerms(terms);
        loanApplicationDTO.setCollaterals(collaterals);
        loanApplicationDTO.setValidationResults(validationResults);
        loanApplicationDTO.setRejectionReason(rejectionReason);
        loanApplicationDTO.setApprovalNotes(approvalNotes);
        loanApplicationDTO.setDocumentPackageId(documentPackageId);
        loanApplicationDTO.setCreatedAt(createdAt);
        loanApplicationDTO.setUpdatedAt(updatedAt);
        return loanApplicationDTO;
    }
}
