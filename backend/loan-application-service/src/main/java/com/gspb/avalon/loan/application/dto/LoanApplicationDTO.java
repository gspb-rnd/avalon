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
    
    public void setTerms(LoanTermsDTO terms) {
        this.terms = terms;
    }
    
    public void setCollaterals(List<CollateralDTO> collaterals) {
        this.collaterals = collaterals;
    }
    
    public void setValidationResults(List<ValidationRuleResultDTO> validationResults) {
        this.validationResults = validationResults;
    }
    
    public static LoanApplicationDTOBuilder builder() {
        return new LoanApplicationDTOBuilder();
    }
}
