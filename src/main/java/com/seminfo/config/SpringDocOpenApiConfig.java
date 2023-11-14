package com.seminfo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class SpringDocOpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                //.components(new Components().addSecuritySchemes("security", securityScheme()))
                .info(
                        new Info()
                                .title("REST API - Spring")
                                .description("API de Blog de conteúdos")
                                .version("v1")
                                .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0"))
                                .contact(new Contact().name("Mateus Elias Vieira").email("mateus.vieira@estudante.ifgoiano.edu.br"))
                );
    }
    private SecurityScheme securityScheme() {
            return new SecurityScheme()
                    .description("Insira um bearer token válido para prosseguir")
                    .type(SecurityScheme.Type.HTTP)
                    .in(SecurityScheme.In.HEADER)
                    .scheme("bearer")
                    .bearerFormat("JWT")
                    .name("security");
    }

}


