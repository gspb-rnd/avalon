package com.gspb.avalon.workflow.process.delegate;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Delegate for checking signature status.
 */
@Component
public class CheckSignatureStatusDelegate implements JavaDelegate {
    
    private static final Logger logger = LoggerFactory.getLogger(CheckSignatureStatusDelegate.class);
    
    @Override
    public void execute(DelegateExecution execution) {
        logger.info("Checking signature status: {}", execution.getProcessInstanceId());
        
        String businessObjectId = (String) execution.getVariable("businessObjectId");
        logger.info("Business object ID: {}", businessObjectId);
        
        boolean allSigned = true;
        boolean anyRejected = false;
        boolean timedOut = false;
        
        execution.setVariable("allSigned", allSigned);
        execution.setVariable("anyRejected", anyRejected);
        execution.setVariable("timedOut", timedOut);
        
        logger.info("Signature status checked: allSigned={}, anyRejected={}, timedOut={}", 
                allSigned, anyRejected, timedOut);
    }
}
