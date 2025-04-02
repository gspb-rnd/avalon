package com.gspb.avalon.client.infrastructure.persistence;

import com.gspb.avalon.client.domain.model.ClientStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * JPA entity for Client.
 */
@Entity
@Table(name = "clients")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientJpaEntity {
    
    @Id
    private UUID id;
    
    @Column(nullable = false)
    private String firstName;
    
    @Column(nullable = false)
    private String lastName;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String phoneNumber;
    
    @Column(nullable = false)
    private LocalDate dateOfBirth;
    
    @Embedded
    private AddressJpaEmbeddable address;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClientStatus status;
    
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KYCDocumentJpaEntity> kycDocuments = new ArrayList<>();
    
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClientRelationshipJpaEntity> relationships = new ArrayList<>();
}
