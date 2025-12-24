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
                                "APIs for authentication, ingredients, menu items, categories, recipes, and profit calculations"
                        )
                )
                .components(
                        new Components()
                                .addSecuritySchemes("bearerAuth", bearerAuth)
                )
                // Global JWT (Authorize button)
                .addSecurityItem(
                        new SecurityRequirement().addList("bearerAuth")
                );
    }

    /**
     * SINGLE Swagger group:
     * - Includes BOTH /api/** and /auth/**
     */
    @Bean
    public GroupedOpenApi apiGroup() {
        return GroupedOpenApi.builder()
                .group("api")
                .pathsToMatch(
                        "/api/**",
                        "/auth/**"
                )
                .build();
    }
}
