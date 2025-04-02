package com.gspb.avalon.workflow.application.dto;

import com.gspb.avalon.workflow.domain.model.TaskPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Command for creating a task.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskCommand {
    
    @NotNull(message = "Workflow ID is required")
    private UUID workflowId;
    
    @NotBlank(message = "Name is required")
    private String name;
    
    private String description;
    
    private String assignee;
    
    @NotNull(message = "Priority is required")
    private TaskPriority priority;
    
    private LocalDateTime dueDate;
    
    private String formKey;
    
    private String taskDefinitionKey;
}
