package com.gspb.avalon.workflow.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Command for assigning a task.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssignTaskCommand {
    
    @NotNull(message = "Task ID is required")
    private UUID taskId;
    
    @NotBlank(message = "Assignee is required")
    private String assignee;
    
    @NotBlank(message = "Assigned by is required")
    private String assignedBy;
}
