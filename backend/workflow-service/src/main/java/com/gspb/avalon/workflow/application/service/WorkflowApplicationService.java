package com.gspb.avalon.workflow.application.service;

import com.gspb.avalon.workflow.application.dto.*;
import com.gspb.avalon.workflow.domain.model.Task;
import com.gspb.avalon.workflow.domain.model.Workflow;
import com.gspb.avalon.workflow.domain.repository.WorkflowRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Application service for workflows.
 */
@Service
@RequiredArgsConstructor
public class WorkflowApplicationService {
    
    private final WorkflowRepository workflowRepository;
    private final WorkflowDTOMapper mapper;
    private final WorkflowEngineService workflowEngineService;
    
    /**
     * Creates a new workflow.
     *
     * @param command The create workflow command
     * @return The created workflow DTO
     */
    @Transactional
    public WorkflowDTO createWorkflow(CreateWorkflowCommand command) {
        Workflow workflow = new Workflow(
                UUID.randomUUID(),
                command.getType(),
                command.getBusinessObjectId(),
                command.getName(),
                command.getDescription(),
                command.getCreatedBy(),
                command.getProcessDefinitionKey()
        );
        
        String processInstanceId = workflowEngineService.startProcess(
                command.getProcessDefinitionKey(),
                workflow.getId().toString(),
                command.getBusinessObjectId().toString()
        );
        
        workflow.setProcessInstanceId(processInstanceId);
        
        Workflow savedWorkflow = workflowRepository.save(workflow);
        return mapper.toDTO(savedWorkflow);
    }
    
    /**
     * Creates a new task.
     *
     * @param command The create task command
     * @return The updated workflow DTO
     */
    @Transactional
    public WorkflowDTO createTask(CreateTaskCommand command) {
        Workflow workflow = findWorkflowById(command.getWorkflowId());
        
        Task task = new Task(
                UUID.randomUUID(),
                workflow.getId(),
                command.getName(),
                command.getDescription(),
                command.getAssignee(),
                command.getPriority(),
                command.getDueDate(),
                command.getFormKey(),
                command.getTaskDefinitionKey()
        );
        
        workflow.addTask(task);
        
        Workflow savedWorkflow = workflowRepository.save(workflow);
        return mapper.toDTO(savedWorkflow);
    }
    
    /**
     * Assigns a task.
     *
     * @param command The assign task command
     * @return The updated workflow DTO
     */
    @Transactional
    public WorkflowDTO assignTask(AssignTaskCommand command) {
        Workflow workflow = findWorkflowByTaskId(command.getTaskId());
        
        Task task = workflow.getTask(command.getTaskId());
        task.assign(command.getAssignee(), command.getAssignedBy());
        
        Workflow savedWorkflow = workflowRepository.save(workflow);
        return mapper.toDTO(savedWorkflow);
    }
    
    /**
     * Completes a task.
     *
     * @param command The complete task command
     * @return The updated workflow DTO
     */
    @Transactional
    public WorkflowDTO completeTask(CompleteTaskCommand command) {
        Workflow workflow = findWorkflowByTaskId(command.getTaskId());
        
        Task task = workflow.getTask(command.getTaskId());
        task.complete(command.getCompletionNotes(), command.getCompletedBy());
        
        if (task.getTaskDefinitionKey() != null) {
            workflowEngineService.completeTask(
                    workflow.getProcessInstanceId(),
                    task.getTaskDefinitionKey(),
                    command.getCompletionNotes()
            );
        }
        
        Workflow savedWorkflow = workflowRepository.save(workflow);
        return mapper.toDTO(savedWorkflow);
    }
    
    /**
     * Completes a workflow.
     *
     * @param workflowId The workflow ID
     * @param completedBy The user who completed the workflow
     * @return The updated workflow DTO
     */
    @Transactional
    public WorkflowDTO completeWorkflow(UUID workflowId, String completedBy) {
        Workflow workflow = findWorkflowById(workflowId);
        
        workflow.complete(completedBy);
        
        workflowEngineService.completeProcess(workflow.getProcessInstanceId());
        
        Workflow savedWorkflow = workflowRepository.save(workflow);
        return mapper.toDTO(savedWorkflow);
    }
    
    /**
     * Cancels a workflow.
     *
     * @param workflowId The workflow ID
     * @param cancelledBy The user who cancelled the workflow
     * @return The updated workflow DTO
     */
    @Transactional
    public WorkflowDTO cancelWorkflow(UUID workflowId, String cancelledBy) {
        Workflow workflow = findWorkflowById(workflowId);
        
        workflow.cancel(cancelledBy);
        
        workflowEngineService.cancelProcess(workflow.getProcessInstanceId());
        
        Workflow savedWorkflow = workflowRepository.save(workflow);
        return mapper.toDTO(savedWorkflow);
    }
    
    /**
     * Suspends a workflow.
     *
     * @param workflowId The workflow ID
     * @param suspendedBy The user who suspended the workflow
     * @return The updated workflow DTO
     */
    @Transactional
    public WorkflowDTO suspendWorkflow(UUID workflowId, String suspendedBy) {
        Workflow workflow = findWorkflowById(workflowId);
        
        workflow.suspend(suspendedBy);
        
        workflowEngineService.suspendProcess(workflow.getProcessInstanceId());
        
        Workflow savedWorkflow = workflowRepository.save(workflow);
        return mapper.toDTO(savedWorkflow);
    }
    
    /**
     * Resumes a workflow.
     *
     * @param workflowId The workflow ID
     * @param resumedBy The user who resumed the workflow
     * @return The updated workflow DTO
     */
    @Transactional
    public WorkflowDTO resumeWorkflow(UUID workflowId, String resumedBy) {
        Workflow workflow = findWorkflowById(workflowId);
        
        workflow.resume(resumedBy);
        
        workflowEngineService.resumeProcess(workflow.getProcessInstanceId());
        
        Workflow savedWorkflow = workflowRepository.save(workflow);
        return mapper.toDTO(savedWorkflow);
    }
    
    /**
     * Gets a workflow by ID.
     *
     * @param workflowId The workflow ID
     * @return The workflow DTO
     */
    @Transactional(readOnly = true)
    public WorkflowDTO getWorkflow(UUID workflowId) {
        Workflow workflow = findWorkflowById(workflowId);
        return mapper.toDTO(workflow);
    }
    
    /**
     * Gets workflows by business object ID.
     *
     * @param businessObjectId The business object ID
     * @return The workflow DTOs
     */
    @Transactional(readOnly = true)
    public List<WorkflowDTO> getWorkflowsByBusinessObjectId(UUID businessObjectId) {
        List<Workflow> workflows = workflowRepository.findByBusinessObjectId(businessObjectId);
        return workflows.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Gets workflows by business object ID and type.
     *
     * @param businessObjectId The business object ID
     * @param type The workflow type
     * @return The workflow DTOs
     */
    @Transactional(readOnly = true)
    public List<WorkflowDTO> getWorkflowsByBusinessObjectIdAndType(UUID businessObjectId, com.gspb.avalon.workflow.domain.model.WorkflowType type) {
        List<Workflow> workflows = workflowRepository.findByBusinessObjectIdAndType(businessObjectId, type);
        return workflows.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Finds a workflow by ID.
     *
     * @param workflowId The workflow ID
     * @return The workflow
     * @throws EntityNotFoundException if the workflow is not found
     */
    private Workflow findWorkflowById(UUID workflowId) {
        return workflowRepository.findById(workflowId)
                .orElseThrow(() -> new EntityNotFoundException("Workflow not found: " + workflowId));
    }
    
    /**
     * Finds a workflow by task ID.
     *
     * @param taskId The task ID
     * @return The workflow
     * @throws EntityNotFoundException if the workflow is not found
     */
    private Workflow findWorkflowByTaskId(UUID taskId) {
        return workflowRepository.findAll().stream()
                .filter(workflow -> workflow.getTasks().stream()
                        .anyMatch(task -> task.getId().equals(taskId)))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Workflow not found for task: " + taskId));
    }
}
