package com.gspb.avalon.document.infrastructure.persistence;

import com.gspb.avalon.document.domain.model.Document;
import com.gspb.avalon.document.domain.model.DocumentStatus;
import com.gspb.avalon.document.domain.model.DocumentType;
import com.gspb.avalon.document.domain.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * JPA implementation of the document repository.
 */
@Repository
@RequiredArgsConstructor
public class JpaDocumentRepository implements DocumentRepository {
    
    private final SpringDataDocumentRepository springDataRepository;
    private final DocumentMapper mapper;
    
    @Override
    public Document save(Document document) {
        DocumentJpaEntity entity = mapper.toJpaEntity(document);
        DocumentJpaEntity savedEntity = springDataRepository.save(entity);
        return mapper.toDomainModel(savedEntity);
    }
    
    @Override
    public Optional<Document> findById(UUID id) {
        return springDataRepository.findById(id)
                .map(mapper::toDomainModel);
    }
    
    @Override
    public List<Document> findAll() {
        return springDataRepository.findAll().stream()
                .map(mapper::toDomainModel)
                .collect(Collectors.toList());
    }
    
    @Override
    public void deleteById(UUID id) {
        springDataRepository.deleteById(id);
    }
    
    @Override
    public List<Document> findByPackageId(UUID packageId) {
        return springDataRepository.findByPackageId(packageId).stream()
                .map(mapper::toDomainModel)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Document> findByStatus(DocumentStatus status) {
        return springDataRepository.findByStatus(status).stream()
                .map(mapper::toDomainModel)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Document> findByType(DocumentType type) {
        return springDataRepository.findByType(type).stream()
                .map(mapper::toDomainModel)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Document> findByPackageIdAndStatus(UUID packageId, DocumentStatus status) {
        return springDataRepository.findByPackageIdAndStatus(packageId, status).stream()
                .map(mapper::toDomainModel)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Document> findByPackageIdAndType(UUID packageId, DocumentType type) {
        return springDataRepository.findByPackageIdAndType(packageId, type).stream()
                .map(mapper::toDomainModel)
                .collect(Collectors.toList());
    }
}
