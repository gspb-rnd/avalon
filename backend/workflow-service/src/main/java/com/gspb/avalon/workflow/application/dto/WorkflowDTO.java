package com.gspb.avalon.workflow.application.dto;

import com.gspb.avalon.workflow.domain.model.WorkflowStatus;
import com.gspb.avalon.workflow.domain.model.WorkflowType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Data Transfer Object for Workflow.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkflowDTO {
    
    private UUID id;
    private WorkflowType type;
    private UUID businessObjectId;
    private String name;
    private String description;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private WorkflowStatus status;
    private String processInstanceId;
    private String processDefinitionKey;
    private List<TaskDTO> tasks = new ArrayList<>();
}
