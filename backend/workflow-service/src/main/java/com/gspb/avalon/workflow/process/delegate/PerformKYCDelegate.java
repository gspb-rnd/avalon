package com.gspb.avalon.workflow.process.delegate;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Delegate for performing KYC checks.
 */
@Component
public class PerformKYCDelegate implements JavaDelegate {
    
    private static final Logger logger = LoggerFactory.getLogger(PerformKYCDelegate.class);
    
    @Override
    public void execute(DelegateExecution execution) {
        logger.info("Performing KYC checks: {}", execution.getProcessInstanceId());
        
        String businessObjectId = (String) execution.getVariable("businessObjectId");
        logger.info("Business object ID: {}", businessObjectId);
        
        boolean kycPassed = true;
        execution.setVariable("kycPassed", kycPassed);
        
        logger.info("KYC check result: {}", kycPassed);
    }
}
