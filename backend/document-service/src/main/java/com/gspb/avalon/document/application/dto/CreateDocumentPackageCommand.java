package com.gspb.avalon.document.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Command for creating a document package.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateDocumentPackageCommand {
    
    @NotNull(message = "Loan application ID is required")
    private UUID loanApplicationId;
    
    @NotBlank(message = "Name is required")
    private String name;
    
    @NotBlank(message = "Created by is required")
    private String createdBy;
}
