package com.gspb.avalon.workflow.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Command for completing a task.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompleteTaskCommand {
    
    @NotNull(message = "Task ID is required")
    private UUID taskId;
    
    private String completionNotes;
    
    @NotBlank(message = "Completed by is required")
    private String completedBy;
}
