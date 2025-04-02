package com.gspb.avalon.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for API Gateway routes.
 */
@Configuration
public class GatewayConfig {
    
    /**
     * Configures routes for the API Gateway.
     *
     * @param builder The route locator builder
     * @return The route locator
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("client-service", r -> r.path("/api/clients/**")
                        .uri("lb://client-service"))
                
                .route("loan-application-service", r -> r.path("/api/loan-applications/**")
                        .uri("lb://loan-application-service"))
                
                .route("collateral-service", r -> r.path("/api/collaterals/**")
                        .uri("lb://collateral-service"))
                
                .route("document-service", r -> r.path("/api/documents/**")
                        .uri("lb://document-service"))
                
                .route("workflow-service", r -> r.path("/api/workflows/**")
                        .uri("lb://workflow-service"))
                
                .route("audit-service", r -> r.path("/api/audit/**")
                        .uri("lb://audit-service"))
                
                .build();
    }
}
