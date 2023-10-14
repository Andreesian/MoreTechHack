package ru.clickgroup.vtbmockapi.conf;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerConfiguration {

    @Bean
    public OpenAPI springDocOpenApi() {
        return new OpenAPI()
                .info(springDocapiInfo());

    }

    Info springDocapiInfo() {
        return new Info()
                .title("MoreTech")
                .description("MoreTech")
                //.license("")
                .version("1.0.0");
        //.contact(new Contact("", ""))
    }




}
