package com.gspb.avalon.loan.application.dto;

import com.gspb.avalon.loan.domain.model.CollateralType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Data Transfer Object for Collateral.
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CollateralDTO {
    
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
    
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public UUID getLoanApplicationId() {
        return loanApplicationId;
    }
    
    public void setLoanApplicationId(UUID loanApplicationId) {
        this.loanApplicationId = loanApplicationId;
    }
    
    public CollateralType getType() {
        return type;
    }
    
    public void setType(CollateralType type) {
        this.type = type;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public BigDecimal getEstimatedValue() {
        return estimatedValue;
    }
    
    public void setEstimatedValue(BigDecimal estimatedValue) {
        this.estimatedValue = estimatedValue;
    }
    
    public BigDecimal getAppraiserValue() {
        return appraiserValue;
    }
    
    public void setAppraiserValue(BigDecimal appraiserValue) {
        this.appraiserValue = appraiserValue;
    }
    
    public BigDecimal getLoanToValueRatio() {
        return loanToValueRatio;
    }
    
    public void setLoanToValueRatio(BigDecimal loanToValueRatio) {
        this.loanToValueRatio = loanToValueRatio;
    }
    
    public LocalDate getValuationDate() {
        return valuationDate;
    }
    
    public void setValuationDate(LocalDate valuationDate) {
        this.valuationDate = valuationDate;
    }
    
    public String getAppraiserName() {
        return appraiserName;
    }
    
    public void setAppraiserName(String appraiserName) {
        this.appraiserName = appraiserName;
    }
    
    public String getDocumentationUrl() {
        return documentationUrl;
    }
    
    public void setDocumentationUrl(String documentationUrl) {
        this.documentationUrl = documentationUrl;
    }
    
    public boolean isVerified() {
        return verified;
    }
    
    public void setVerified(boolean verified) {
        this.verified = verified;
    }
    
    public static CollateralDTOBuilder builder() {
        return new CollateralDTOBuilder();
    }
}
