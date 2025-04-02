package com.gspb.avalon.workflow.application.dto;

import com.gspb.avalon.workflow.domain.model.WorkflowType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Command for creating a workflow.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateWorkflowCommand {
    
    @NotNull(message = "Workflow type is required")
    private WorkflowType type;
    
    @NotNull(message = "Business object ID is required")
    private UUID businessObjectId;
    
    @NotBlank(message = "Name is required")
    private String name;
    
    private String description;
    
    @NotBlank(message = "Created by is required")
    private String createdBy;
    
    @NotBlank(message = "Process definition key is required")
    private String processDefinitionKey;
}
