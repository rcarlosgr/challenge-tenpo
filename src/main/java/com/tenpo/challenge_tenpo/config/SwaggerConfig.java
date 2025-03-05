package com.tenpo.challenge_tenpo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Calculation API")
                        .version("1.0")
                        .description("API para realizar cálculos con porcentaje dinámico y almacenar historial de llamadas."));
    }
}
