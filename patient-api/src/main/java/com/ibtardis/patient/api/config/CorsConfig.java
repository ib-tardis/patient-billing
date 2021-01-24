package com.ibtardis.patient.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Value( "${allow.origin}" )
    private String[] allowOrigins;

    @Value( "${allow.methods}" )
    private String allowMethods;

    public void addCorsMappings(CorsRegistry registry) {
        System.out.println(allowMethods);
        registry.addMapping("/**")
                .allowedOrigins(allowOrigins)
                .allowedMethods(allowMethods.split(","))
                .allowedHeaders("Content-Type")
                .exposedHeaders();
    }
}
