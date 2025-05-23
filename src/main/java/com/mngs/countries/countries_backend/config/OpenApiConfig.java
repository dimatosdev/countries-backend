package com.mngs.countries.countries_backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestão de Países")
                        .version("1.0.0")
                        .description("API para gerenciamento de países, com funcionalidades de listar, salvar, excluir e pesquisar países."));
    }
}