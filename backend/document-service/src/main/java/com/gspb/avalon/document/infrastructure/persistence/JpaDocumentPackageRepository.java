package com.gspb.avalon.document.infrastructure.persistence;

import com.gspb.avalon.document.domain.model.DocumentPackage;
import com.gspb.avalon.document.domain.repository.DocumentPackageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * JPA implementation of the document package repository.
 */
@Repository
@RequiredArgsConstructor
public class JpaDocumentPackageRepository implements DocumentPackageRepository {
    
    private final SpringDataDocumentPackageRepository springDataRepository;
    private final DocumentPackageMapper mapper;
    
    @Override
    public DocumentPackage save(DocumentPackage documentPackage) {
        DocumentPackageJpaEntity entity = mapper.toJpaEntity(documentPackage);
        DocumentPackageJpaEntity savedEntity = springDataRepository.save(entity);
        return mapper.toDomainModel(savedEntity);
    }
    
    @Override
    public Optional<DocumentPackage> findById(UUID id) {
        return springDataRepository.findById(id)
                .map(mapper::toDomainModel);
    }
    
    @Override
    public List<DocumentPackage> findAll() {
        return springDataRepository.findAll().stream()
                .map(mapper::toDomainModel)
                .collect(Collectors.toList());
    }
    
    @Override
    public void deleteById(UUID id) {
        springDataRepository.deleteById(id);
    }
    
    @Override
    public List<DocumentPackage> findByLoanApplicationId(UUID loanApplicationId) {
        return springDataRepository.findByLoanApplicationId(loanApplicationId).stream()
                .map(mapper::toDomainModel)
                .collect(Collectors.toList());
    }
}
