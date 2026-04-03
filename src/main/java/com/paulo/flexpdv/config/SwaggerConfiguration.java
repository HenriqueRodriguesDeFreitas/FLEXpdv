package com.paulo.flexpdv.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API para projeto de mini PDV")
                        .version("1.0.0")
                        .description("Mini ponto de venda com cadastro de produtos, venda e devolução de mercadoria")
                        .contact(new Contact()
                                .name("Paulo Henrique")
                                .email("devhenriquerodrigues971@gmail.com")));
    }
}
