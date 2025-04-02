package com.gspb.avalon.workflow.process.delegate;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Delegate for notifying that signature is complete.
 */
@Component
public class NotifySignatureCompleteDelegate implements JavaDelegate {
    
    private static final Logger logger = LoggerFactory.getLogger(NotifySignatureCompleteDelegate.class);
    
    @Override
    public void execute(DelegateExecution execution) {
        logger.info("Notifying signature complete: {}", execution.getProcessInstanceId());
        
        String businessObjectId = (String) execution.getVariable("businessObjectId");
        logger.info("Business object ID: {}", businessObjectId);
        
        logger.info("Signature complete notification sent: {}", businessObjectId);
    }
}
