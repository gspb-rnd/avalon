package com.gspb.avalon.loan.infrastructure.persistence;

import com.gspb.avalon.loan.domain.model.CollateralType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * JPA entity for collateral.
 */
@Entity
@Table(name = "collaterals")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CollateralJpaEntity {
    
    @Id
    private UUID id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_application_id", nullable = false)
    private LoanApplicationJpaEntity loanApplication;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private CollateralType type;
    
    @Column(name = "description", nullable = false)
    private String description;
    
    @Column(name = "estimated_value", precision = 19, scale = 2, nullable = false)
    private BigDecimal estimatedValue;
    
    @Column(name = "appraiser_value", precision = 19, scale = 2)
    private BigDecimal appraiserValue;
    
    @Column(name = "loan_to_value_ratio", precision = 5, scale = 2)
    private BigDecimal loanToValueRatio;
    
    @Column(name = "valuation_date")
    private LocalDate valuationDate;
    
    @Column(name = "appraiser_name")
    private String appraiserName;
    
    @Column(name = "documentation_url")
    private String documentationUrl;
    
    @Column(name = "verified")
    private Boolean verified;
    
    public static CollateralJpaEntityBuilder builder() {
        return new CollateralJpaEntityBuilder();
    }
    
    public String getDocumentationUrl() {
        return documentationUrl;
    }
    
    public void setDocumentationUrl(String documentationUrl) {
        this.documentationUrl = documentationUrl;
    }
    
    public boolean isVerified() {
        return verified != null && verified;
    }
    
    public Boolean getVerified() {
        return verified;
    }
    
    public void setVerified(Boolean verified) {
        this.verified = verified;
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
    
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public LoanApplicationJpaEntity getLoanApplication() {
        return loanApplication;
    }
    
    public void setLoanApplication(LoanApplicationJpaEntity loanApplication) {
        this.loanApplication = loanApplication;
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
}
