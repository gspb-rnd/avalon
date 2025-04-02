package com.gspb.avalon.workflow.infrastructure.persistence;

import com.gspb.avalon.workflow.domain.model.WorkflowStatus;
import com.gspb.avalon.workflow.domain.model.WorkflowType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * JPA entity for workflow.
 */
@Entity
@Table(name = "workflows")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkflowJpaEntity {
    
    @Id
    private UUID id;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private WorkflowType type;
    
    @Column(name = "business_object_id", nullable = false)
    private UUID businessObjectId;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "created_by", nullable = false)
    private String createdBy;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private WorkflowStatus status;
    
    @Column(name = "process_instance_id")
    private String processInstanceId;
    
    @Column(name = "process_definition_key")
    private String processDefinitionKey;
    
    @OneToMany(mappedBy = "workflow", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskJpaEntity> tasks = new ArrayList<>();
}
