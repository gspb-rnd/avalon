package com.gspb.avalon.document.domain.repository;

import com.gspb.avalon.document.domain.model.DocumentPackage;
import com.gspb.avalon.shared.domain.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for DocumentPackage aggregate root.
 */
public interface DocumentPackageRepository extends Repository<DocumentPackage, UUID> {
    
    /**
     * Finds document packages by loan application ID.
     *
     * @param loanApplicationId The loan application ID
     * @return A list of document packages for the loan application
     */
    List<DocumentPackage> findByLoanApplicationId(UUID loanApplicationId);
}
