package com.gspb.avalon.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Main application class for the API Gateway.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {
    
    /**
     * Main method.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
