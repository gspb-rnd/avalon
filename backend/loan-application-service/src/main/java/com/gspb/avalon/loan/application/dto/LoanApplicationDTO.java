package com.gspb.avalon.loan.application.dto;

import com.gspb.avalon.loan.domain.model.LoanStatus;
import com.gspb.avalon.loan.domain.model.LoanType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Data Transfer Object for LoanApplication.
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class LoanApplicationDTO {
    
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
    
    public LoanTermsDTO getTerms() {
        return terms;
    }
    
    public void setTerms(LoanTermsDTO terms) {
        this.terms = terms;
    }
    
    public List<CollateralDTO> getCollaterals() {
        return collaterals;
    }
    
    public void setCollaterals(List<CollateralDTO> collaterals) {
        this.collaterals = collaterals;
    }
    
    public List<ValidationRuleResultDTO> getValidationResults() {
        return validationResults;
    }
    
    public void setValidationResults(List<ValidationRuleResultDTO> validationResults) {
        this.validationResults = validationResults;
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
    
    public static LoanApplicationDTOBuilder builder() {
        return new LoanApplicationDTOBuilder();
    }
}
