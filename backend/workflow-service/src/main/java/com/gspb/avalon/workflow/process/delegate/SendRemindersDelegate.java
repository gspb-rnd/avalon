package com.gspb.avalon.workflow.process.delegate;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Delegate for sending reminders to signatories.
 */
@Component
public class SendRemindersDelegate implements JavaDelegate {
    
    private static final Logger logger = LoggerFactory.getLogger(SendRemindersDelegate.class);
    
    @Override
    public void execute(DelegateExecution execution) {
        logger.info("Sending reminders: {}", execution.getProcessInstanceId());
        
        String businessObjectId = (String) execution.getVariable("businessObjectId");
        logger.info("Business object ID: {}", businessObjectId);
        
        logger.info("Reminders sent: {}", businessObjectId);
    }
}
