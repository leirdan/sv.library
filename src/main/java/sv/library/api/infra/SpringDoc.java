package sv.library.api.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SpringDoc {
    @Bean
    public OpenAPI customApi() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes(
                                "bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info()
                        .title("SV Library API")
                        .description("API RESTful que controla os dados de uma biblioteca.")
                        .license(new License().name("MIT")));
    }
}
