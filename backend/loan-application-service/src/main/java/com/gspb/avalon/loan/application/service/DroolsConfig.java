package com.gspb.avalon.loan.application.service;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

/**
 * Configuration for Drools rule engine.
 */
@Configuration
public class DroolsConfig {

    private static final String RULES_PATH = "rules/";
    
    @Bean
    public KieContainer kieContainer() throws IOException {
        KieServices kieServices = KieServices.Factory.get();
        
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resourcePatternResolver.getResources("classpath*:" + RULES_PATH + "**/*.*");
        
        for (Resource resource : resources) {
            kieFileSystem.write(ResourceFactory.newClassPathResource(
                    RULES_PATH + resource.getFilename(), "UTF-8"));
        }
        
        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
        kieBuilder.buildAll();
        
        KieModule kieModule = kieBuilder.getKieModule();
        
        return kieServices.newKieContainer(kieModule.getReleaseId());
    }
    
    @Bean
    public KieSession kieSession() throws IOException {
        return kieContainer().newKieSession();
    }
    
    /**
     * Creates a new KieSession for each validation to ensure thread safety.
     * 
     * @return A new KieSession
     * @throws IOException If rule files cannot be loaded
     */
    public KieSession createKieSession() throws IOException {
        return kieContainer().newKieSession();
    }
}
