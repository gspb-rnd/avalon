package com.gspb.avalon.loan.infrastructure.persistence;

import com.gspb.avalon.loan.domain.model.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Mapper between domain model and JPA entities for loan applications.
 */
@Component
public class LoanApplicationMapper {
    
    /**
     * Maps a loan application domain model to a JPA entity.
     *
     * @param loanApplication The loan application domain model
     * @return The loan application JPA entity
     */
    public LoanApplicationJpaEntity toJpaEntity(LoanApplication loanApplication) {
        LoanApplicationJpaEntity entity = LoanApplicationJpaEntity.builder()
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
            entity.setTerms(mapLoanTerms(loanApplication.getTerms()));
        }
        
        entity.setCollaterals(mapCollaterals(loanApplication.getCollaterals(), entity));
        entity.setValidationResults(mapValidationResults(loanApplication.getValidationResults(), entity));
        
        return entity;
    }
    
    /**
     * Maps a loan application JPA entity to a domain model.
     *
     * @param entity The loan application JPA entity
     * @return The loan application domain model
     */
    public LoanApplication toDomainModel(LoanApplicationJpaEntity entity) {
        LoanApplication loanApplication = new LoanApplication(
                entity.getId(),
                entity.getClientId(),
                entity.getAdvisorId(),
                entity.getLoanType()
        );
        
        try {
            java.lang.reflect.Field statusField = LoanApplication.class.getDeclaredField("status");
            statusField.setAccessible(true);
            statusField.set(loanApplication, entity.getStatus());
            
            java.lang.reflect.Field purposeField = LoanApplication.class.getDeclaredField("purpose");
            purposeField.setAccessible(true);
            purposeField.set(loanApplication, entity.getPurpose());
            
            java.lang.reflect.Field rejectionReasonField = LoanApplication.class.getDeclaredField("rejectionReason");
            rejectionReasonField.setAccessible(true);
            rejectionReasonField.set(loanApplication, entity.getRejectionReason());
            
            java.lang.reflect.Field approvalNotesField = LoanApplication.class.getDeclaredField("approvalNotes");
            approvalNotesField.setAccessible(true);
            approvalNotesField.set(loanApplication, entity.getApprovalNotes());
            
            java.lang.reflect.Field documentPackageIdField = LoanApplication.class.getDeclaredField("documentPackageId");
            documentPackageIdField.setAccessible(true);
            documentPackageIdField.set(loanApplication, entity.getDocumentPackageId());
            
            java.lang.reflect.Field createdAtField = LoanApplication.class.getDeclaredField("createdAt");
            createdAtField.setAccessible(true);
            createdAtField.set(loanApplication, entity.getCreatedAt());
            
            java.lang.reflect.Field updatedAtField = LoanApplication.class.getDeclaredField("updatedAt");
            updatedAtField.setAccessible(true);
            updatedAtField.set(loanApplication, entity.getUpdatedAt());
            
            if (entity.getTerms() != null) {
                java.lang.reflect.Field termsField = LoanApplication.class.getDeclaredField("terms");
                termsField.setAccessible(true);
                termsField.set(loanApplication, mapLoanTerms(entity.getTerms()));
            }
            
            java.lang.reflect.Field collateralsField = LoanApplication.class.getDeclaredField("collaterals");
            collateralsField.setAccessible(true);
            List<Collateral> collaterals = entity.getCollaterals().stream()
                    .map(this::mapCollateral)
                    .collect(Collectors.toList());
            ((List<Collateral>) collateralsField.get(loanApplication)).addAll(collaterals);
            
            java.lang.reflect.Field validationResultsField = LoanApplication.class.getDeclaredField("validationResults");
            validationResultsField.setAccessible(true);
            List<ValidationRuleResult> validationResults = entity.getValidationResults().stream()
                    .map(this::mapValidationRuleResult)
                    .collect(Collectors.toList());
            ((List<ValidationRuleResult>) validationResultsField.get(loanApplication)).addAll(validationResults);
            
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Error mapping loan application entity to domain model", e);
        }
        
        return loanApplication;
    }
    
    /**
     * Maps loan terms domain model to an embeddable JPA entity.
     *
     * @param terms The loan terms domain model
     * @return The loan terms embeddable JPA entity
     */
    private LoanTermsEmbeddable mapLoanTerms(LoanTerms terms) {
        return LoanTermsEmbeddable.builder()
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
     * Maps an embeddable loan terms JPA entity to a domain model.
     *
     * @param embeddable The loan terms embeddable JPA entity
     * @return The loan terms domain model
     */
    private LoanTerms mapLoanTerms(LoanTermsEmbeddable embeddable) {
        return new LoanTerms(
                embeddable.getAmount(),
                embeddable.getCurrency(),
                embeddable.getTermInMonths(),
                embeddable.getInterestRate(),
                embeddable.getInterestRateType(),
                embeddable.getPaymentFrequency(),
                embeddable.getStartDate(),
                embeddable.getMaturityDate(),
                embeddable.getOriginationFee(),
                embeddable.getEarlyRepaymentAllowed(),
                embeddable.getEarlyRepaymentFee()
        );
    }
    
    /**
     * Maps collateral domain models to JPA entities.
     *
     * @param collaterals The collateral domain models
     * @param loanApplicationEntity The loan application JPA entity
     * @return The collateral JPA entities
     */
    private List<CollateralJpaEntity> mapCollaterals(List<Collateral> collaterals, LoanApplicationJpaEntity loanApplicationEntity) {
        return collaterals.stream()
                .map(collateral -> CollateralJpaEntity.builder()
                        .id(collateral.getId())
                        .loanApplication(loanApplicationEntity)
                        .type(collateral.getType())
                        .description(collateral.getDescription())
                        .estimatedValue(collateral.getEstimatedValue())
                        .appraiserValue(collateral.getAppraiserValue())
                        .loanToValueRatio(collateral.getLoanToValueRatio())
                        .valuationDate(collateral.getValuationDate())
                        .appraiserName(collateral.getAppraiserName())
                        .documentationUrl(collateral.getDocumentationUrl())
                        .verified(collateral.isVerified())
                        .build())
                .collect(Collectors.toList());
    }
    
    /**
     * Maps a collateral JPA entity to a domain model.
     *
     * @param entity The collateral JPA entity
     * @return The collateral domain model
     */
    private Collateral mapCollateral(CollateralJpaEntity entity) {
        Collateral collateral = new Collateral(
                entity.getId(),
                entity.getLoanApplication().getId(),
                entity.getType(),
                entity.getDescription(),
                entity.getEstimatedValue()
        );
        
        try {
            if (entity.getAppraiserValue() != null) {
                java.lang.reflect.Field appraiserValueField = Collateral.class.getDeclaredField("appraiserValue");
                appraiserValueField.setAccessible(true);
                appraiserValueField.set(collateral, entity.getAppraiserValue());
                
                java.lang.reflect.Field loanToValueRatioField = Collateral.class.getDeclaredField("loanToValueRatio");
                loanToValueRatioField.setAccessible(true);
                loanToValueRatioField.set(collateral, entity.getLoanToValueRatio());
                
                java.lang.reflect.Field valuationDateField = Collateral.class.getDeclaredField("valuationDate");
                valuationDateField.setAccessible(true);
                valuationDateField.set(collateral, entity.getValuationDate());
                
                java.lang.reflect.Field appraiserNameField = Collateral.class.getDeclaredField("appraiserName");
                appraiserNameField.setAccessible(true);
                appraiserNameField.set(collateral, entity.getAppraiserName());
            }
            
            if (entity.getDocumentationUrl() != null) {
                java.lang.reflect.Field documentationUrlField = Collateral.class.getDeclaredField("documentationUrl");
                documentationUrlField.setAccessible(true);
                documentationUrlField.set(collateral, entity.getDocumentationUrl());
            }
            
            java.lang.reflect.Field verifiedField = Collateral.class.getDeclaredField("verified");
            verifiedField.setAccessible(true);
            verifiedField.set(collateral, entity.getVerified());
            
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Error mapping collateral entity to domain model", e);
        }
        
        return collateral;
    }
    
    /**
     * Maps validation rule result domain models to JPA entities.
     *
     * @param validationResults The validation rule result domain models
     * @param loanApplicationEntity The loan application JPA entity
     * @return The validation rule result JPA entities
     */
    private List<ValidationRuleResultJpaEntity> mapValidationResults(List<ValidationRuleResult> validationResults, LoanApplicationJpaEntity loanApplicationEntity) {
        return validationResults.stream()
                .map(result -> ValidationRuleResultJpaEntity.builder()
                        .loanApplication(loanApplicationEntity)
                        .ruleId(result.getRuleId())
                        .ruleName(result.getRuleName())
                        .passed(result.isPassed())
                        .message(result.getMessage())
                        .severity(result.getSeverity())
                        .overridable(result.isOverridable())
                        .overridden(result.isOverridden())
                        .overrideReason(result.getOverrideReason())
                        .overriddenBy(result.getOverriddenBy())
                        .build())
                .collect(Collectors.toList());
    }
    
    /**
     * Maps a validation rule result JPA entity to a domain model.
     *
     * @param entity The validation rule result JPA entity
     * @return The validation rule result domain model
     */
    private ValidationRuleResult mapValidationRuleResult(ValidationRuleResultJpaEntity entity) {
        ValidationRuleResult result = new ValidationRuleResult(
                entity.getRuleId(),
                entity.getRuleName(),
                entity.getPassed(),
                entity.getMessage(),
                entity.getSeverity(),
                entity.getOverridable()
        );
        
        if (entity.getOverridden()) {
            try {
                java.lang.reflect.Field overriddenField = ValidationRuleResult.class.getDeclaredField("overridden");
                overriddenField.setAccessible(true);
                overriddenField.set(result, true);
                
                java.lang.reflect.Field overrideReasonField = ValidationRuleResult.class.getDeclaredField("overrideReason");
                overrideReasonField.setAccessible(true);
                overrideReasonField.set(result, entity.getOverrideReason());
                
                java.lang.reflect.Field overriddenByField = ValidationRuleResult.class.getDeclaredField("overriddenBy");
                overriddenByField.setAccessible(true);
                overriddenByField.set(result, entity.getOverriddenBy());
                
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException("Error mapping validation rule result entity to domain model", e);
            }
        }
        
        return result;
    }
}
