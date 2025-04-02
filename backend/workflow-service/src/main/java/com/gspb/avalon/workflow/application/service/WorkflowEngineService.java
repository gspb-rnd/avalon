package com.gspb.avalon.workflow.application.service;

import lombok.RequiredArgsConstructor;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service for interacting with the workflow engine.
 */
@Service
@RequiredArgsConstructor
public class WorkflowEngineService {
    
    private final ProcessEngine processEngine;
    private final RuntimeService runtimeService;
    private final TaskService taskService;
    
    /**
     * Starts a process.
     *
     * @param processDefinitionKey The process definition key
     * @param workflowId The workflow ID
     * @param businessObjectId The business object ID
     * @return The process instance ID
     */
    public String startProcess(String processDefinitionKey, String workflowId, String businessObjectId) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("workflowId", workflowId);
        variables.put("businessObjectId", businessObjectId);
        
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(
                processDefinitionKey,
                workflowId,
                variables
        );
        
        return processInstance.getId();
    }
    
    /**
     * Completes a task.
     *
     * @param processInstanceId The process instance ID
     * @param taskDefinitionKey The task definition key
     * @param completionNotes The completion notes
     */
    public void completeTask(String processInstanceId, String taskDefinitionKey, String completionNotes) {
        List<Task> tasks = taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .taskDefinitionKey(taskDefinitionKey)
                .list();
        
        if (!tasks.isEmpty()) {
            Task task = tasks.get(0);
            
            Map<String, Object> variables = new HashMap<>();
            variables.put("completionNotes", completionNotes);
            
            taskService.complete(task.getId(), variables);
        }
    }
    
    /**
     * Completes a process.
     *
     * @param processInstanceId The process instance ID
     */
    public void completeProcess(String processInstanceId) {
        List<Task> tasks = taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .list();
        
        for (Task task : tasks) {
            taskService.complete(task.getId());
        }
    }
    
    /**
     * Cancels a process.
     *
     * @param processInstanceId The process instance ID
     */
    public void cancelProcess(String processInstanceId) {
        runtimeService.deleteProcessInstance(processInstanceId, "Cancelled by user");
    }
    
    /**
     * Suspends a process.
     *
     * @param processInstanceId The process instance ID
     */
    public void suspendProcess(String processInstanceId) {
        runtimeService.suspendProcessInstanceById(processInstanceId);
    }
    
    /**
     * Resumes a process.
     *
     * @param processInstanceId The process instance ID
     */
    public void resumeProcess(String processInstanceId) {
        runtimeService.activateProcessInstanceById(processInstanceId);
    }
    
    /**
     * Gets tasks for a process instance.
     *
     * @param processInstanceId The process instance ID
     * @return The tasks
     */
    public List<Task> getTasksForProcessInstance(String processInstanceId) {
        return taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .list();
    }
    
    /**
     * Gets tasks for a user.
     *
     * @param assignee The assignee
     * @return The tasks
     */
    public List<Task> getTasksForUser(String assignee) {
        return taskService.createTaskQuery()
                .taskAssignee(assignee)
                .list();
    }
}
