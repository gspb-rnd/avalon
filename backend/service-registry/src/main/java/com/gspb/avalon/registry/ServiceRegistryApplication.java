package com.gspb.avalon.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Main application class for the Service Registry.
 */
@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistryApplication {
    
    /**
     * Main method.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(ServiceRegistryApplication.class, args);
    }
}
