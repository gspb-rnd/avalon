package com.gspb.avalon.document.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Spring Data JPA repository for document packages.
 */
@Repository
public interface SpringDataDocumentPackageRepository extends JpaRepository<DocumentPackageJpaEntity, UUID> {
    
    /**
     * Finds document packages by loan application ID.
     *
     * @param loanApplicationId The loan application ID
     * @return A list of document package JPA entities for the loan application
     */
    List<DocumentPackageJpaEntity> findByLoanApplicationId(UUID loanApplicationId);
}
