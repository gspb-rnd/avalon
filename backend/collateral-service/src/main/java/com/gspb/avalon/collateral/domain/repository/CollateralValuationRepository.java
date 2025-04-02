package com.gspb.avalon.collateral.domain.repository;

import com.gspb.avalon.collateral.domain.model.AssetClass;
import com.gspb.avalon.collateral.domain.model.CollateralValuation;
import com.gspb.avalon.collateral.domain.model.ValuationStatus;
import com.gspb.avalon.shared.domain.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for CollateralValuation aggregate.
 */
public interface CollateralValuationRepository extends Repository<CollateralValuation> {
    
    /**
     * Find a collateral valuation by its ID.
     *
     * @param id The valuation ID
     * @return Optional containing the valuation if found
     */
    Optional<CollateralValuation> findById(UUID id);
    
    /**
     * Find all valuations for a specific collateral.
     *
     * @param collateralId The collateral ID
     * @return List of valuations for the collateral
     */
    List<CollateralValuation> findByCollateralId(UUID collateralId);
    
    /**
     * Find all valuations for a specific loan application.
     *
     * @param loanApplicationId The loan application ID
     * @return List of valuations for the loan application
     */
    List<CollateralValuation> findByLoanApplicationId(UUID loanApplicationId);
    
    /**
     * Find all valuations with a specific status.
     *
     * @param status The valuation status
     * @return List of valuations with the specified status
     */
    List<CollateralValuation> findByStatus(ValuationStatus status);
    
    /**
     * Find all valuations for a specific asset class.
     *
     * @param assetClass The asset class
     * @return List of valuations for the specified asset class
     */
    List<CollateralValuation> findByAssetClass(AssetClass assetClass);
    
    /**
     * Save a collateral valuation.
     *
     * @param collateralValuation The valuation to save
     * @return The saved valuation
     */
    CollateralValuation save(CollateralValuation collateralValuation);
    
    /**
     * Delete a collateral valuation.
     *
     * @param id The ID of the valuation to delete
     */
    void deleteById(UUID id);
}
