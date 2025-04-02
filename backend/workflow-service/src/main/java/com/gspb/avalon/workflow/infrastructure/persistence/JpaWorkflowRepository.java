package com.gspb.avalon.workflow.infrastructure.persistence;

import com.gspb.avalon.workflow.domain.model.Workflow;
import com.gspb.avalon.workflow.domain.model.WorkflowStatus;
import com.gspb.avalon.workflow.domain.model.WorkflowType;
import com.gspb.avalon.workflow.domain.repository.WorkflowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * JPA implementation of the workflow repository.
 */
@Repository
@RequiredArgsConstructor
public class JpaWorkflowRepository implements WorkflowRepository {
    
    private final SpringDataWorkflowRepository springDataRepository;
    private final WorkflowMapper mapper;
    
    @Override
    public Workflow save(Workflow workflow) {
        WorkflowJpaEntity entity = mapper.toJpaEntity(workflow);
        WorkflowJpaEntity savedEntity = springDataRepository.save(entity);
        return mapper.toDomainModel(savedEntity);
    }
    
    @Override
    public Optional<Workflow> findById(UUID id) {
        return springDataRepository.findById(id)
                .map(mapper::toDomainModel);
    }
    
    @Override
    public List<Workflow> findAll() {
        return springDataRepository.findAll().stream()
                .map(mapper::toDomainModel)
                .collect(Collectors.toList());
    }
    
    @Override
    public void deleteById(UUID id) {
        springDataRepository.deleteById(id);
    }
    
    @Override
    public List<Workflow> findByBusinessObjectId(UUID businessObjectId) {
        return springDataRepository.findByBusinessObjectId(businessObjectId).stream()
                .map(mapper::toDomainModel)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Workflow> findByType(WorkflowType type) {
        return springDataRepository.findByType(type).stream()
                .map(mapper::toDomainModel)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Workflow> findByStatus(WorkflowStatus status) {
        return springDataRepository.findByStatus(status).stream()
                .map(mapper::toDomainModel)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Workflow> findByBusinessObjectIdAndType(UUID businessObjectId, WorkflowType type) {
        return springDataRepository.findByBusinessObjectIdAndType(businessObjectId, type).stream()
                .map(mapper::toDomainModel)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Workflow> findByBusinessObjectIdAndStatus(UUID businessObjectId, WorkflowStatus status) {
        return springDataRepository.findByBusinessObjectIdAndStatus(businessObjectId, status).stream()
                .map(mapper::toDomainModel)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Workflow> findByProcessInstanceId(String processInstanceId) {
        return springDataRepository.findByProcessInstanceId(processInstanceId).stream()
                .map(mapper::toDomainModel)
                .collect(Collectors.toList());
    }
}
