package com.gspb.avalon.loan.application.dto;

import com.gspb.avalon.loan.domain.model.CollateralType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Builder class for CollateralDTO.
 */
public class CollateralDTOBuilder {
    
    private UUID id;
    private UUID loanApplicationId;
    private CollateralType type;
    private String description;
    private BigDecimal estimatedValue;
    private BigDecimal appraiserValue;
    private BigDecimal loanToValueRatio;
    private LocalDate valuationDate;
    private String appraiserName;
    private String documentationUrl;
    private boolean verified;
    
    public CollateralDTOBuilder id(UUID id) {
        this.id = id;
        return this;
    }
    
    public CollateralDTOBuilder loanApplicationId(UUID loanApplicationId) {
        this.loanApplicationId = loanApplicationId;
        return this;
    }
    
    public CollateralDTOBuilder type(CollateralType type) {
        this.type = type;
        return this;
    }
    
    public CollateralDTOBuilder description(String description) {
        this.description = description;
        return this;
    }
    
    public CollateralDTOBuilder estimatedValue(BigDecimal estimatedValue) {
        this.estimatedValue = estimatedValue;
        return this;
    }
    
    public CollateralDTOBuilder appraiserValue(BigDecimal appraiserValue) {
        this.appraiserValue = appraiserValue;
        return this;
    }
    
    public CollateralDTOBuilder loanToValueRatio(BigDecimal loanToValueRatio) {
        this.loanToValueRatio = loanToValueRatio;
        return this;
    }
    
    public CollateralDTOBuilder valuationDate(LocalDate valuationDate) {
        this.valuationDate = valuationDate;
        return this;
    }
    
    public CollateralDTOBuilder appraiserName(String appraiserName) {
        this.appraiserName = appraiserName;
        return this;
    }
    
    public CollateralDTOBuilder documentationUrl(String documentationUrl) {
        this.documentationUrl = documentationUrl;
        return this;
    }
    
    public CollateralDTOBuilder verified(boolean verified) {
        this.verified = verified;
        return this;
    }
    
    public CollateralDTO build() {
        CollateralDTO collateralDTO = new CollateralDTO();
        collateralDTO.setId(id);
        collateralDTO.setLoanApplicationId(loanApplicationId);
        collateralDTO.setType(type);
        collateralDTO.setDescription(description);
        collateralDTO.setEstimatedValue(estimatedValue);
        collateralDTO.setAppraiserValue(appraiserValue);
        collateralDTO.setLoanToValueRatio(loanToValueRatio);
        collateralDTO.setValuationDate(valuationDate);
        collateralDTO.setAppraiserName(appraiserName);
        collateralDTO.setDocumentationUrl(documentationUrl);
        collateralDTO.setVerified(verified);
        return collateralDTO;
    }
}
