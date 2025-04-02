package com.gspb.avalon.loan.application.service;

import com.gspb.avalon.loan.application.dto.*;
import com.gspb.avalon.loan.domain.model.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Mapper between domain model and DTOs for loan applications.
 */
@Component
public class LoanApplicationDTOMapper {
    
    /**
     * Maps a loan application domain model to a DTO.
     *
     * @param loanApplication The loan application domain model
     * @return The loan application DTO
     */
    public LoanApplicationDTO toDTO(LoanApplication loanApplication) {
        LoanApplicationDTO dto = LoanApplicationDTO.builder()
                .id(loanApplication.getId())
                .clientId(loanApplication.getClientId())
                .advisorId(loanApplication.getAdvisorId())
                .loanType(loanApplication.getLoanType())
                .status(loanApplication.getStatus())
                .purpose(loanApplication.getPurpose())
                .rejectionReason(loanApplication.getRejectionReason())
                .approvalNotes(loanApplication.getApprovalNotes())
                .documentPackageId(loanApplication.getDocumentPackageId())
                .createdAt(loanApplication.getCreatedAt())
                .updatedAt(loanApplication.getUpdatedAt())
                .build();
        
        if (loanApplication.getTerms() != null) {
            LoanTermsDTO termsDTO = mapLoanTerms(loanApplication.getTerms());
            termsDTO.setMonthlyPayment(loanApplication.getTerms().calculateMonthlyPayment());
            termsDTO.setTotalCost(loanApplication.getTerms().calculateTotalCost());
            dto.setTerms(termsDTO);
        }
        
        dto.setCollaterals(mapCollaterals(loanApplication.getCollaterals()));
        dto.setValidationResults(mapValidationResults(loanApplication.getValidationResults()));
        
        return dto;
    }
    
    /**
     * Maps loan terms domain model to a DTO.
     *
     * @param terms The loan terms domain model
     * @return The loan terms DTO
     */
    private LoanTermsDTO mapLoanTerms(LoanTerms terms) {
        return LoanTermsDTO.builder()
                .amount(terms.getAmount())
                .currency(terms.getCurrency())
                .termInMonths(terms.getTermInMonths())
                .interestRate(terms.getInterestRate())
                .interestRateType(terms.getInterestRateType())
                .paymentFrequency(terms.getPaymentFrequency())
                .startDate(terms.getStartDate())
                .maturityDate(terms.getMaturityDate())
                .originationFee(terms.getOriginationFee())
                .earlyRepaymentAllowed(terms.isEarlyRepaymentAllowed())
                .earlyRepaymentFee(terms.getEarlyRepaymentFee())
                .build();
    }
    
    /**
     * Maps collateral domain models to DTOs.
     *
     * @param collaterals The collateral domain models
     * @return The collateral DTOs
     */
    private List<CollateralDTO> mapCollaterals(List<Collateral> collaterals) {
        return collaterals.stream()
                .map(this::mapCollateral)
                .collect(Collectors.toList());
    }
    
    /**
     * Maps a collateral domain model to a DTO.
     *
     * @param collateral The collateral domain model
     * @return The collateral DTO
     */
    private CollateralDTO mapCollateral(Collateral collateral) {
        return CollateralDTO.builder()
                .id(collateral.getId())
                .loanApplicationId(collateral.getLoanApplicationId())
                .type(collateral.getType())
                .description(collateral.getDescription())
                .estimatedValue(collateral.getEstimatedValue())
                .appraiserValue(collateral.getAppraiserValue())
                .loanToValueRatio(collateral.getLoanToValueRatio())
                .valuationDate(collateral.getValuationDate())
                .appraiserName(collateral.getAppraiserName())
                .documentationUrl(collateral.getDocumentationUrl())
                .verified(collateral.isVerified())
                .build();
    }
    
    /**
     * Maps validation rule result domain models to DTOs.
     *
     * @param validationResults The validation rule result domain models
     * @return The validation rule result DTOs
     */
    private List<ValidationRuleResultDTO> mapValidationResults(List<ValidationRuleResult> validationResults) {
        return validationResults.stream()
                .map(this::mapValidationRuleResult)
                .collect(Collectors.toList());
    }
    
    /**
     * Maps a validation rule result domain model to a DTO.
     *
     * @param result The validation rule result domain model
     * @return The validation rule result DTO
     */
    private ValidationRuleResultDTO mapValidationRuleResult(ValidationRuleResult result) {
        return ValidationRuleResultDTO.builder()
                .ruleId(result.getRuleId())
                .ruleName(result.getRuleName())
                .passed(result.isPassed())
                .message(result.getMessage())
                .severity(result.getSeverity())
                .overridable(result.isOverridable())
                .overridden(result.isOverridden())
                .overrideReason(result.getOverrideReason())
                .overriddenBy(result.getOverriddenBy())
                .effectivelyPassed(result.isEffectivelyPassed())
                .build();
    }
    
    /**
     * Creates a new collateral from a command.
     *
     * @param command The add collateral command
     * @return The new collateral
     */
    public Collateral createCollateralFromCommand(AddCollateralCommand command) {
        return new Collateral(
                UUID.randomUUID(),
                command.getLoanApplicationId(),
                command.getType(),
                command.getDescription(),
                command.getEstimatedValue()
        );
    }
}
