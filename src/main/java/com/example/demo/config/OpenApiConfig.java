package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    /**
     * Global OpenAPI configuration
     * - Defines JWT bearer authentication
     * - Applies security globally (except where overridden)
     */
    @Bean
    public OpenAPI openAPI() {

        SecurityScheme bearerAuth = new SecurityScheme()
                .name("Authorization")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        return new OpenAPI()
                .info(new Info()
                        .title("Menu Profitability Calculator API")
                        .version("1.0")
                        .description(
                                "APIs for managing ingredients, menu items, categories, recipes, and profit calculations"
                        )
                )
                .components(
                        new Components()
                                .addSecuritySchemes("bearerAuth", bearerAuth)
                )
                // 🔐 Apply JWT security globally (Swagger Authorize button)
                .addSecurityItem(
                        new SecurityRequirement().addList("bearerAuth")
                );
    }

    /**
     * Group for secured business APIs
     * All endpoints under /api/**
     */
    @Bean
    public GroupedOpenApi apiGroup() {
        return GroupedOpenApi.builder()
                .group("api")
                .pathsToMatch("/api/**")
                .build();
    }

    /**
     * Group for authentication APIs
     * Public endpoints: /auth/register, /auth/login
     */
    @Bean
    public GroupedOpenApi authGroup() {
        return GroupedOpenApi.builder()
                .group("auth")
                .pathsToMatch("/auth/**")
                .build();
    }
}
