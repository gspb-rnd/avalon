package com.gspb.avalon.loan.infrastructure.persistence;

import com.gspb.avalon.loan.domain.model.LoanApplication;
import com.gspb.avalon.loan.domain.model.LoanStatus;
import com.gspb.avalon.loan.domain.model.LoanType;
import com.gspb.avalon.loan.domain.repository.LoanApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * JPA implementation of the loan application repository.
 */
@Repository
@RequiredArgsConstructor
public class JpaLoanApplicationRepository implements LoanApplicationRepository {
    
    private final SpringDataLoanApplicationRepository springDataRepository;
    private final LoanApplicationMapper mapper;
    
    @Override
    public LoanApplication save(LoanApplication loanApplication) {
        LoanApplicationJpaEntity entity = mapper.toJpaEntity(loanApplication);
        LoanApplicationJpaEntity savedEntity = springDataRepository.save(entity);
        return mapper.toDomainEntity(savedEntity);
    }
    
    @Override
    public Optional<LoanApplication> findById(UUID id) {
        return springDataRepository.findById(id)
                .map(mapper::toDomainEntity);
    }
    
    @Override
    public List<LoanApplication> findAll() {
        return springDataRepository.findAll().stream()
                .map(mapper::toDomainEntity)
                .collect(Collectors.toList());
    }
    
    @Override
    public void deleteById(UUID id) {
        springDataRepository.deleteById(id);
    }
    
    @Override
    public void delete(LoanApplication loanApplication) {
        springDataRepository.deleteById(loanApplication.getId());
    }
    
    @Override
    public boolean existsById(UUID id) {
        return springDataRepository.existsById(id);
    }
    
    @Override
    public List<LoanApplication> findByClientId(UUID clientId) {
        return springDataRepository.findByClientId(clientId).stream()
                .map(mapper::toDomainEntity)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<LoanApplication> findByAdvisorId(String advisorId) {
        return springDataRepository.findByAdvisorId(advisorId).stream()
                .map(mapper::toDomainEntity)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<LoanApplication> findByStatus(LoanStatus status) {
        return springDataRepository.findByStatus(status).stream()
                .map(mapper::toDomainEntity)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<LoanApplication> findByType(LoanType type) {
        return springDataRepository.findByLoanType(type).stream()
                .map(mapper::toDomainEntity)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<LoanApplication> findByClientIdAndStatus(UUID clientId, LoanStatus status) {
        return springDataRepository.findByClientIdAndStatus(clientId, status).stream()
                .map(mapper::toDomainEntity)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<LoanApplication> findByAdvisorIdAndStatus(String advisorId, LoanStatus status) {
        return springDataRepository.findByAdvisorIdAndStatus(advisorId, status).stream()
                .map(mapper::toDomainEntity)
                .collect(Collectors.toList());
    }
}
