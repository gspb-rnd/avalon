package com.gspb.avalon.loan.infrastructure.persistence;

import com.gspb.avalon.loan.domain.model.LoanStatus;
import com.gspb.avalon.loan.domain.model.LoanType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * JPA entity for loan application.
 */
@Entity
@Table(name = "loan_applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanApplicationJpaEntity {
    
    @Id
    private UUID id;
    
    @Column(name = "client_id", nullable = false)
    private UUID clientId;
    
    @Column(name = "advisor_id", nullable = false)
    private String advisorId;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "loan_type", nullable = false)
    private LoanType loanType;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private LoanStatus status;
    
    @Column(name = "purpose")
    private String purpose;
    
    @Column(name = "rejection_reason")
    private String rejectionReason;
    
    @Column(name = "approval_notes")
    private String approvalNotes;
    
    @Column(name = "document_package_id")
    private UUID documentPackageId;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    
    @Embedded
    private LoanTermsEmbeddable terms;
    
    @OneToMany(mappedBy = "loanApplication", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CollateralJpaEntity> collaterals = new ArrayList<>();
    
    @OneToMany(mappedBy = "loanApplication", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ValidationRuleResultJpaEntity> validationResults = new ArrayList<>();
}
