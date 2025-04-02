package com.gspb.avalon.workflow.process.delegate;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Delegate for handling rejected signatures.
 */
@Component
public class HandleRejectedSignaturesDelegate implements JavaDelegate {
    
    private static final Logger logger = LoggerFactory.getLogger(HandleRejectedSignaturesDelegate.class);
    
    @Override
    public void execute(DelegateExecution execution) {
        logger.info("Handling rejected signatures: {}", execution.getProcessInstanceId());
        
        String businessObjectId = (String) execution.getVariable("businessObjectId");
        logger.info("Business object ID: {}", businessObjectId);
        
        logger.info("Rejected signatures handled: {}", businessObjectId);
    }
}
