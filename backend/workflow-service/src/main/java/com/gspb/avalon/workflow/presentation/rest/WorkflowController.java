package com.gspb.avalon.workflow.presentation.rest;

import com.gspb.avalon.workflow.application.dto.*;
import com.gspb.avalon.workflow.application.service.WorkflowApplicationService;
import com.gspb.avalon.workflow.domain.model.WorkflowType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST controller for workflows.
 */
@RestController
@RequestMapping("/api/workflows")
@RequiredArgsConstructor
public class WorkflowController {
    
    private final WorkflowApplicationService workflowService;
    
    /**
     * Creates a new workflow.
     *
     * @param command The create workflow command
     * @return The created workflow DTO
     */
    @PostMapping
    public ResponseEntity<WorkflowDTO> createWorkflow(@Valid @RequestBody CreateWorkflowCommand command) {
        WorkflowDTO workflowDTO = workflowService.createWorkflow(command);
        return new ResponseEntity<>(workflowDTO, HttpStatus.CREATED);
    }
    
    /**
     * Gets a workflow by ID.
     *
     * @param id The workflow ID
     * @return The workflow DTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<WorkflowDTO> getWorkflow(@PathVariable UUID id) {
        WorkflowDTO workflowDTO = workflowService.getWorkflow(id);
        return ResponseEntity.ok(workflowDTO);
    }
    
    /**
     * Gets workflows by business object ID.
     *
     * @param businessObjectId The business object ID
     * @return The workflow DTOs
     */
    @GetMapping("/business-object/{businessObjectId}")
    public ResponseEntity<List<WorkflowDTO>> getWorkflowsByBusinessObjectId(@PathVariable UUID businessObjectId) {
        List<WorkflowDTO> workflowDTOs = workflowService.getWorkflowsByBusinessObjectId(businessObjectId);
        return ResponseEntity.ok(workflowDTOs);
    }
    
    /**
     * Gets workflows by business object ID and type.
     *
     * @param businessObjectId The business object ID
     * @param type The workflow type
     * @return The workflow DTOs
     */
    @GetMapping("/business-object/{businessObjectId}/type/{type}")
    public ResponseEntity<List<WorkflowDTO>> getWorkflowsByBusinessObjectIdAndType(
            @PathVariable UUID businessObjectId,
            @PathVariable WorkflowType type) {
        List<WorkflowDTO> workflowDTOs = workflowService.getWorkflowsByBusinessObjectIdAndType(businessObjectId, type);
        return ResponseEntity.ok(workflowDTOs);
    }
    
    /**
     * Creates a new task.
     *
     * @param command The create task command
     * @return The updated workflow DTO
     */
    @PostMapping("/tasks")
    public ResponseEntity<WorkflowDTO> createTask(@Valid @RequestBody CreateTaskCommand command) {
        WorkflowDTO workflowDTO = workflowService.createTask(command);
        return ResponseEntity.ok(workflowDTO);
    }
    
    /**
     * Assigns a task.
     *
     * @param command The assign task command
     * @return The updated workflow DTO
     */
    @PutMapping("/tasks/assign")
    public ResponseEntity<WorkflowDTO> assignTask(@Valid @RequestBody AssignTaskCommand command) {
        WorkflowDTO workflowDTO = workflowService.assignTask(command);
        return ResponseEntity.ok(workflowDTO);
    }
    
    /**
     * Completes a task.
     *
     * @param command The complete task command
     * @return The updated workflow DTO
     */
    @PutMapping("/tasks/complete")
    public ResponseEntity<WorkflowDTO> completeTask(@Valid @RequestBody CompleteTaskCommand command) {
        WorkflowDTO workflowDTO = workflowService.completeTask(command);
        return ResponseEntity.ok(workflowDTO);
    }
    
    /**
     * Completes a workflow.
     *
     * @param id The workflow ID
     * @param completedBy The user who completed the workflow
     * @return The updated workflow DTO
     */
    @PutMapping("/{id}/complete")
    public ResponseEntity<WorkflowDTO> completeWorkflow(
            @PathVariable UUID id,
            @RequestParam String completedBy) {
        WorkflowDTO workflowDTO = workflowService.completeWorkflow(id, completedBy);
        return ResponseEntity.ok(workflowDTO);
    }
    
    /**
     * Cancels a workflow.
     *
     * @param id The workflow ID
     * @param cancelledBy The user who cancelled the workflow
     * @return The updated workflow DTO
     */
    @PutMapping("/{id}/cancel")
    public ResponseEntity<WorkflowDTO> cancelWorkflow(
            @PathVariable UUID id,
            @RequestParam String cancelledBy) {
        WorkflowDTO workflowDTO = workflowService.cancelWorkflow(id, cancelledBy);
        return ResponseEntity.ok(workflowDTO);
    }
    
    /**
     * Suspends a workflow.
     *
     * @param id The workflow ID
     * @param suspendedBy The user who suspended the workflow
     * @return The updated workflow DTO
     */
    @PutMapping("/{id}/suspend")
    public ResponseEntity<WorkflowDTO> suspendWorkflow(
            @PathVariable UUID id,
            @RequestParam String suspendedBy) {
        WorkflowDTO workflowDTO = workflowService.suspendWorkflow(id, suspendedBy);
        return ResponseEntity.ok(workflowDTO);
    }
    
    /**
     * Resumes a workflow.
     *
     * @param id The workflow ID
     * @param resumedBy The user who resumed the workflow
     * @return The updated workflow DTO
     */
    @PutMapping("/{id}/resume")
    public ResponseEntity<WorkflowDTO> resumeWorkflow(
            @PathVariable UUID id,
            @RequestParam String resumedBy) {
        WorkflowDTO workflowDTO = workflowService.resumeWorkflow(id, resumedBy);
        return ResponseEntity.ok(workflowDTO);
    }
}
