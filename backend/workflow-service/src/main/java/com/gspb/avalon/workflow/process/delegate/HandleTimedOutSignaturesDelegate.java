package com.gspb.avalon.workflow.process.delegate;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Delegate for handling timed out signatures.
 */
@Component
public class HandleTimedOutSignaturesDelegate implements JavaDelegate {
    
    private static final Logger logger = LoggerFactory.getLogger(HandleTimedOutSignaturesDelegate.class);
    
    @Override
    public void execute(DelegateExecution execution) {
        logger.info("Handling timed out signatures: {}", execution.getProcessInstanceId());
        
        String businessObjectId = (String) execution.getVariable("businessObjectId");
        logger.info("Business object ID: {}", businessObjectId);
        
        logger.info("Timed out signatures handled: {}", businessObjectId);
    }
}
