package com.gspb.avalon.workflow.infrastructure.persistence;

import com.gspb.avalon.workflow.domain.model.WorkflowStatus;
import com.gspb.avalon.workflow.domain.model.WorkflowType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Spring Data JPA repository for workflows.
 */
@Repository
public interface SpringDataWorkflowRepository extends JpaRepository<WorkflowJpaEntity, UUID> {
    
    /**
     * Finds workflows by business object ID.
     *
     * @param businessObjectId The business object ID
     * @return A list of workflow JPA entities for the business object
     */
    List<WorkflowJpaEntity> findByBusinessObjectId(UUID businessObjectId);
    
    /**
     * Finds workflows by type.
     *
     * @param type The workflow type
     * @return A list of workflow JPA entities with the given type
     */
    List<WorkflowJpaEntity> findByType(WorkflowType type);
    
    /**
     * Finds workflows by status.
     *
     * @param status The workflow status
     * @return A list of workflow JPA entities with the given status
     */
    List<WorkflowJpaEntity> findByStatus(WorkflowStatus status);
    
    /**
     * Finds workflows by business object ID and type.
     *
     * @param businessObjectId The business object ID
     * @param type The workflow type
     * @return A list of workflow JPA entities for the business object with the given type
     */
    List<WorkflowJpaEntity> findByBusinessObjectIdAndType(UUID businessObjectId, WorkflowType type);
    
    /**
     * Finds workflows by business object ID and status.
     *
     * @param businessObjectId The business object ID
     * @param status The workflow status
     * @return A list of workflow JPA entities for the business object with the given status
     */
    List<WorkflowJpaEntity> findByBusinessObjectIdAndStatus(UUID businessObjectId, WorkflowStatus status);
    
    /**
     * Finds workflows by process instance ID.
     *
     * @param processInstanceId The process instance ID
     * @return A list of workflow JPA entities with the given process instance ID
     */
    List<WorkflowJpaEntity> findByProcessInstanceId(String processInstanceId);
}
