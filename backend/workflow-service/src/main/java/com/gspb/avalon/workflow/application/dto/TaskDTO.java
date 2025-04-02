package com.gspb.avalon.workflow.application.dto;

import com.gspb.avalon.workflow.domain.model.TaskPriority;
import com.gspb.avalon.workflow.domain.model.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Data Transfer Object for Task.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    
    private UUID id;
    private UUID workflowId;
    private String name;
    private String description;
    private String assignee;
    private TaskStatus status;
    private TaskPriority priority;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime dueDate;
    private LocalDateTime completedAt;
    private String completionNotes;
    private String formKey;
    private String taskDefinitionKey;
    private boolean isOverdue;
}
