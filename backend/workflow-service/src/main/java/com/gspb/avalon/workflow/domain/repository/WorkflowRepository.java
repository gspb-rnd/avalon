package com.gspb.avalon.workflow.domain.repository;

import com.gspb.avalon.shared.domain.Repository;
import com.gspb.avalon.workflow.domain.model.Workflow;
import com.gspb.avalon.workflow.domain.model.WorkflowStatus;
import com.gspb.avalon.workflow.domain.model.WorkflowType;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for Workflow aggregate root.
 */
public interface WorkflowRepository extends Repository<Workflow, UUID> {
    
    /**
     * Finds workflows by business object ID.
     *
     * @param businessObjectId The business object ID
     * @return A list of workflows for the business object
     */
    List<Workflow> findByBusinessObjectId(UUID businessObjectId);
    
    /**
     * Finds workflows by type.
     *
     * @param type The workflow type
     * @return A list of workflows with the given type
     */
    List<Workflow> findByType(WorkflowType type);
    
    /**
     * Finds workflows by status.
     *
     * @param status The workflow status
     * @return A list of workflows with the given status
     */
    List<Workflow> findByStatus(WorkflowStatus status);
    
    /**
     * Finds workflows by business object ID and type.
     *
     * @param businessObjectId The business object ID
     * @param type The workflow type
     * @return A list of workflows for the business object with the given type
     */
    List<Workflow> findByBusinessObjectIdAndType(UUID businessObjectId, WorkflowType type);
    
    /**
     * Finds workflows by business object ID and status.
     *
     * @param businessObjectId The business object ID
     * @param status The workflow status
     * @return A list of workflows for the business object with the given status
     */
    List<Workflow> findByBusinessObjectIdAndStatus(UUID businessObjectId, WorkflowStatus status);
    
    /**
     * Finds workflows by process instance ID.
     *
     * @param processInstanceId The process instance ID
     * @return A list of workflows with the given process instance ID
     */
    List<Workflow> findByProcessInstanceId(String processInstanceId);
}
