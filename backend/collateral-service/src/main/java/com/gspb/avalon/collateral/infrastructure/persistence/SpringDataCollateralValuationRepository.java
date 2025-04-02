package com.gspb.avalon.collateral.infrastructure.persistence;

import com.gspb.avalon.collateral.domain.model.AssetClass;
import com.gspb.avalon.collateral.domain.model.ValuationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Spring Data JPA repository for CollateralValuationJpaEntity.
 */
@Repository
public interface SpringDataCollateralValuationRepository extends JpaRepository<CollateralValuationJpaEntity, UUID> {
    
    List<CollateralValuationJpaEntity> findByCollateralId(UUID collateralId);
    
    List<CollateralValuationJpaEntity> findByLoanApplicationId(UUID loanApplicationId);
    
    List<CollateralValuationJpaEntity> findByStatus(ValuationStatus status);
    
    List<CollateralValuationJpaEntity> findByAssetClass(AssetClass assetClass);
}
