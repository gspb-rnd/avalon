package com.gspb.avalon.loan.domain.repository;

import com.gspb.avalon.loan.domain.model.LoanApplication;
import com.gspb.avalon.loan.domain.model.LoanStatus;
import com.gspb.avalon.loan.domain.model.LoanType;
import com.gspb.avalon.shared.domain.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for LoanApplication aggregate root.
 */
public interface LoanApplicationRepository extends Repository<LoanApplication, UUID> {
    
    /**
     * Finds loan applications by client ID.
     *
     * @param clientId The client ID
     * @return A list of loan applications for the client
     */
    List<LoanApplication> findByClientId(UUID clientId);
    
    /**
     * Finds loan applications by advisor ID.
     *
     * @param advisorId The advisor ID
     * @return A list of loan applications for the advisor
     */
    List<LoanApplication> findByAdvisorId(String advisorId);
    
    /**
     * Finds loan applications by status.
     *
     * @param status The status
     * @return A list of loan applications with the given status
     */
    List<LoanApplication> findByStatus(LoanStatus status);
    
    /**
     * Finds loan applications by type.
     *
     * @param type The loan type
     * @return A list of loan applications with the given type
     */
    List<LoanApplication> findByType(LoanType type);
    
    /**
     * Finds loan applications by client ID and status.
     *
     * @param clientId The client ID
     * @param status The status
     * @return A list of loan applications for the client with the given status
     */
    List<LoanApplication> findByClientIdAndStatus(UUID clientId, LoanStatus status);
    
    /**
     * Finds loan applications by advisor ID and status.
     *
     * @param advisorId The advisor ID
     * @param status The status
     * @return A list of loan applications for the advisor with the given status
     */
    List<LoanApplication> findByAdvisorIdAndStatus(String advisorId, LoanStatus status);
}
