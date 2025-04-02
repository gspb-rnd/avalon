package com.gspb.avalon.workflow.process.delegate;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Delegate for disbursing a loan.
 */
@Component
public class DisburseLoanDelegate implements JavaDelegate {
    
    private static final Logger logger = LoggerFactory.getLogger(DisburseLoanDelegate.class);
    
    @Override
    public void execute(DelegateExecution execution) {
        logger.info("Disbursing loan: {}", execution.getProcessInstanceId());
        
        String businessObjectId = (String) execution.getVariable("businessObjectId");
        logger.info("Business object ID: {}", businessObjectId);
        
        logger.info("Loan disbursed: {}", businessObjectId);
    }
}
