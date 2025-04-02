package com.gspb.avalon.loan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Main application class for the loan application service.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class LoanApplicationServiceApplication {
    
    /**
     * Main method to start the application.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(LoanApplicationServiceApplication.class, args);
    }
}
