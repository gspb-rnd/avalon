package com.gspb.avalon.workflow.process.delegate;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Delegate for approving a loan application.
 */
@Component
public class ApproveLoanApplicationDelegate implements JavaDelegate {
    
    private static final Logger logger = LoggerFactory.getLogger(ApproveLoanApplicationDelegate.class);
    
    @Override
    public void execute(DelegateExecution execution) {
        logger.info("Approving loan application: {}", execution.getProcessInstanceId());
        
        String businessObjectId = (String) execution.getVariable("businessObjectId");
        logger.info("Business object ID: {}", businessObjectId);
        
        logger.info("Loan application approved: {}", businessObjectId);
    }
}
