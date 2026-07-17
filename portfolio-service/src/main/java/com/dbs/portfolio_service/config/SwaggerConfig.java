package com.dbs.portfolio_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI portfolioOpenAPI() {

        return new OpenAPI()

                .info(new Info()

                        .title("Portfolio Service API")

                        .version("1.0")

                        .description("Portfolio Service for DBS Financial Platform")

                        .contact(new Contact()

                                .name("Rahul Mali")

                                .email("rahul@example.com")));
    }

}