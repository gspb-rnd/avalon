package com.gspb.avalon.loan.infrastructure.persistence;

import com.gspb.avalon.loan.domain.model.CollateralType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Builder class for CollateralJpaEntity.
 */
public class CollateralJpaEntityBuilder {
    
    private UUID id;
    private LoanApplicationJpaEntity loanApplication;
    private CollateralType type;
    private String description;
    private BigDecimal estimatedValue;
    private BigDecimal appraiserValue;
    private BigDecimal loanToValueRatio;
    private LocalDate valuationDate;
    private String appraiserName;
    private String documentationUrl;
    private Boolean verified;
    
    public CollateralJpaEntityBuilder id(UUID id) {
        this.id = id;
        return this;
    }
    
    public CollateralJpaEntityBuilder loanApplication(LoanApplicationJpaEntity loanApplication) {
        this.loanApplication = loanApplication;
        return this;
    }
    
    public CollateralJpaEntityBuilder type(CollateralType type) {
        this.type = type;
        return this;
    }
    
    public CollateralJpaEntityBuilder description(String description) {
        this.description = description;
        return this;
    }
    
    public CollateralJpaEntityBuilder estimatedValue(BigDecimal estimatedValue) {
        this.estimatedValue = estimatedValue;
        return this;
    }
    
    public CollateralJpaEntityBuilder appraiserValue(BigDecimal appraiserValue) {
        this.appraiserValue = appraiserValue;
        return this;
    }
    
    public CollateralJpaEntityBuilder loanToValueRatio(BigDecimal loanToValueRatio) {
        this.loanToValueRatio = loanToValueRatio;
        return this;
    }
    
    public CollateralJpaEntityBuilder valuationDate(LocalDate valuationDate) {
        this.valuationDate = valuationDate;
        return this;
    }
    
    public CollateralJpaEntityBuilder appraiserName(String appraiserName) {
        this.appraiserName = appraiserName;
        return this;
    }
    
    public CollateralJpaEntityBuilder documentationUrl(String documentationUrl) {
        this.documentationUrl = documentationUrl;
        return this;
    }
    
    public CollateralJpaEntityBuilder verified(Boolean verified) {
        this.verified = verified;
        return this;
    }
    
    public CollateralJpaEntity build() {
        CollateralJpaEntity entity = new CollateralJpaEntity();
        entity.setId(id);
        entity.setLoanApplication(loanApplication);
        entity.setType(type);
        entity.setDescription(description);
        entity.setEstimatedValue(estimatedValue);
        entity.setAppraiserValue(appraiserValue);
        entity.setLoanToValueRatio(loanToValueRatio);
        entity.setValuationDate(valuationDate);
        entity.setAppraiserName(appraiserName);
        entity.setDocumentationUrl(documentationUrl);
        entity.setVerified(verified);
        return entity;
    }
}
