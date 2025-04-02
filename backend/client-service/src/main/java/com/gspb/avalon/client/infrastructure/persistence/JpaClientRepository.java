package com.gspb.avalon.client.infrastructure.persistence;

import com.gspb.avalon.client.domain.model.Client;
import com.gspb.avalon.client.domain.model.ClientStatus;
import com.gspb.avalon.client.domain.repository.ClientRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * JPA implementation of the ClientRepository interface.
 */
@Repository
public class JpaClientRepository implements ClientRepository {
    
    private final SpringDataClientRepository clientRepository;
    private final ClientMapper clientMapper;
    
    /**
     * Creates a new JPA client repository.
     *
     * @param clientRepository The Spring Data JPA repository
     * @param clientMapper The client mapper
     */
    public JpaClientRepository(SpringDataClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }
    
    @Override
    public Client save(Client client) {
        ClientJpaEntity entity = clientMapper.toJpaEntity(client);
        ClientJpaEntity savedEntity = clientRepository.save(entity);
        return clientMapper.toDomain(savedEntity);
    }
    
    @Override
    public Optional<Client> findById(UUID id) {
        return clientRepository.findById(id)
                .map(clientMapper::toDomain);
    }
    
    @Override
    public List<Client> findAll() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public void delete(Client client) {
        clientRepository.deleteById(client.getId());
    }
    
    @Override
    public boolean existsById(UUID id) {
        return clientRepository.existsById(id);
    }
    
    @Override
    public Optional<Client> findByEmail(String email) {
        return clientRepository.findByEmail(email)
                .map(clientMapper::toDomain);
    }
    
    @Override
    public List<Client> findByStatus(String status) {
        ClientStatus clientStatus = ClientStatus.valueOf(status);
        return clientRepository.findByStatus(clientStatus)
                .stream()
                .map(clientMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Client> findByNameContaining(String name) {
        return clientRepository.findByFirstNameContainingOrLastNameContaining(name, name)
                .stream()
                .map(clientMapper::toDomain)
                .collect(Collectors.toList());
    }
}
