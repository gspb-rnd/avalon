package com.gspb.avalon.document.application.dto;

import com.gspb.avalon.document.domain.model.DocumentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Command for creating a document.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateDocumentCommand {
    
    @NotNull(message = "Package ID is required")
    private UUID packageId;
    
    @NotNull(message = "Document type is required")
    private DocumentType type;
    
    @NotBlank(message = "Name is required")
    private String name;
    
    @NotBlank(message = "Created by is required")
    private String createdBy;
}
