package com.poly.config;

import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;

@Configuration
@SecurityScheme(name = "Bearer Authentication", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
public class SwaggerConfig {

    private static final String description = """
            🔐 **Default Credentials for JWT Login**
            - Username: `admin`
            - Password: `admin`
            
            👉 Use this account to log in via `/auth-controller/login`, obtain the JWT token, 
            then click "Authorize" in Swagger UI and paste the token in the `Bearer` format.
            """;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("E-commerce API Rework")
                        .version("1.0")
                        .description(description))
                        .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .addServersItem(new Server().url("https://congtoan-app.koyeb.app").description("Production Server"))
                .addServersItem(new Server().url("http://localhost:8081").description("Local Development Server"));
    }
}
