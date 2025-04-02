package com.gspb.avalon.client.application.dto;

import com.gspb.avalon.client.domain.model.ClientStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Data Transfer Object for Client.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
    
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private AddressDTO address;
    private ClientStatus status;
    private List<KYCDocumentDTO> kycDocuments = new ArrayList<>();
    private List<ClientRelationshipDTO> relationships = new ArrayList<>();
}
