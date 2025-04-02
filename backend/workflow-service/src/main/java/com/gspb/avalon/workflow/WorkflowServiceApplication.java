package com.gspb.avalon.workflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Main application class for the workflow service.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class WorkflowServiceApplication {
    
    /**
     * Main method to start the application.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(WorkflowServiceApplication.class, args);
    }
}
