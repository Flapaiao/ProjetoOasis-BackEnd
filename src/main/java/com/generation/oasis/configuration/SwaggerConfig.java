package com.generation.oasis.configuration;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Configuration
public class SwaggerConfig {
	@Bean
    OpenAPI springOasisOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Ecommerce Oasis")
                .description("Este é um projeto integrador desenvolvido como parte do curso de Java React, focado na criação de um E-commerce voltado para a promoção da Agricultura Sustentável e o combate à Fome.")
                .version("v0.0.1")
                .license(new License()
                    .name("Anderson Morais - Fernanda Sasso - Flávia Paião - Guilherme Abreu - Isabella Alburquerque - Lauane Gonçalves - Rafael Seiji")
                    .url("https://github.com/Oasis05"))
                .contact(new Contact()
                    .name("Anderson Morais - Fernanda Sasso - Flávia Paião - Guilherme Abreu - Isabella Alburquerque - Lauane Gonçalves - Rafael Seiji")
                    .url("https://github.com/Oasis05/OASIS")
                    .email("ecommerceoasis.gp5@gmail.com")))
            .externalDocs(new ExternalDocumentation()
                .description("Github")
                .url("https://github.com/Oasis05/OASIS/"));
    }
	
	@Bean
	OpenApiCustomizer customerGlobalHeaderOpenApiCustomiser() {

		return openApi -> {
			openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {

				ApiResponses apiResponses = operation.getResponses();

				apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
				apiResponses.addApiResponse("201", createApiResponse("Objeto Persistido!"));
				apiResponses.addApiResponse("204", createApiResponse("Objeto Excluído!"));
				apiResponses.addApiResponse("400", createApiResponse("Erro na Requisição!"));
				apiResponses.addApiResponse("401", createApiResponse("Acesso Não Autorizado!"));
				apiResponses.addApiResponse("403", createApiResponse("Acesso Proibido!"));
				apiResponses.addApiResponse("404", createApiResponse("Objeto Não Encontrado!"));
				apiResponses.addApiResponse("500", createApiResponse("Erro na Aplicação!"));

			}));
		};
	}

	private ApiResponse createApiResponse(String message) {
		
		return new ApiResponse().description(message);
	}
}