package com.gspb.avalon.client.application.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Command for updating a client.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateClientCommand {
    
    @NotNull(message = "Client ID is required")
    private UUID id;
    
    private String firstName;
    private String lastName;
    
    @Email(message = "Email must be valid")
    private String email;
    
    private String phoneNumber;
    
    @Valid
    private AddressDTO address;
}
