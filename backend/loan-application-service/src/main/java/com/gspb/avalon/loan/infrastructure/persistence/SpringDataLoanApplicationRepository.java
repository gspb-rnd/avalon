package com.gspb.avalon.loan.infrastructure.persistence;

import com.gspb.avalon.loan.domain.model.LoanStatus;
import com.gspb.avalon.loan.domain.model.LoanType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Spring Data JPA repository for loan applications.
 */
@Repository
public interface SpringDataLoanApplicationRepository extends JpaRepository<LoanApplicationJpaEntity, UUID> {
    
    /**
     * Finds loan applications by client ID.
     *
     * @param clientId The client ID
     * @return A list of loan application JPA entities for the client
     */
    List<LoanApplicationJpaEntity> findByClientId(UUID clientId);
    
    /**
     * Finds loan applications by advisor ID.
     *
     * @param advisorId The advisor ID
     * @return A list of loan application JPA entities for the advisor
     */
    List<LoanApplicationJpaEntity> findByAdvisorId(String advisorId);
    
    /**
     * Finds loan applications by status.
     *
     * @param status The status
     * @return A list of loan application JPA entities with the given status
     */
    List<LoanApplicationJpaEntity> findByStatus(LoanStatus status);
    
    /**
     * Finds loan applications by type.
     *
     * @param loanType The loan type
     * @return A list of loan application JPA entities with the given type
     */
    List<LoanApplicationJpaEntity> findByLoanType(LoanType loanType);
    
    /**
     * Finds loan applications by client ID and status.
     *
     * @param clientId The client ID
     * @param status The status
     * @return A list of loan application JPA entities for the client with the given status
     */
    List<LoanApplicationJpaEntity> findByClientIdAndStatus(UUID clientId, LoanStatus status);
    
    /**
     * Finds loan applications by advisor ID and status.
     *
     * @param advisorId The advisor ID
     * @param status The status
     * @return A list of loan application JPA entities for the advisor with the given status
     */
    List<LoanApplicationJpaEntity> findByAdvisorIdAndStatus(String advisorId, LoanStatus status);
}
