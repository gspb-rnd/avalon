package com.gspb.avalon.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Main application class for the client service.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ClientServiceApplication {
    
    /**
     * Main method.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(ClientServiceApplication.class, args);
    }
}
