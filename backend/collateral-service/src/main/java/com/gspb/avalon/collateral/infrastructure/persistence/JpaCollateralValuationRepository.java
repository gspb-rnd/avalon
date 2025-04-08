package com.gspb.avalon.collateral.infrastructure.persistence;

import com.gspb.avalon.collateral.domain.model.AssetClass;
import com.gspb.avalon.collateral.domain.model.CollateralValuation;
import com.gspb.avalon.collateral.domain.model.ValuationStatus;
import com.gspb.avalon.collateral.domain.repository.CollateralValuationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * JPA implementation of the CollateralValuationRepository.
 */
@Repository
@RequiredArgsConstructor
public class JpaCollateralValuationRepository implements CollateralValuationRepository {
    
    private final SpringDataCollateralValuationRepository repository;
    private final CollateralValuationMapper mapper;
    
    @Override
    public Optional<CollateralValuation> findById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }
    
    @Override
    public List<CollateralValuation> findByCollateralId(UUID collateralId) {
        return repository.findByCollateralId(collateralId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<CollateralValuation> findByLoanApplicationId(UUID loanApplicationId) {
        return repository.findByLoanApplicationId(loanApplicationId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<CollateralValuation> findByStatus(ValuationStatus status) {
        return repository.findByStatus(status).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<CollateralValuation> findByAssetClass(AssetClass assetClass) {
        return repository.findByAssetClass(assetClass).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public CollateralValuation save(CollateralValuation collateralValuation) {
        CollateralValuationJpaEntity entity = mapper.toEntity(collateralValuation);
        CollateralValuationJpaEntity savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }
    
    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
    
    @Override
    public boolean existsById(UUID id) {
        return repository.existsById(id);
    }
    
    @Override
    public List<CollateralValuation> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public void delete(CollateralValuation collateralValuation) {
        repository.deleteById(collateralValuation.getId());
    }
}
