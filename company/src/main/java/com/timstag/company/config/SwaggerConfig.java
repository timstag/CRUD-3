package com.timstag.company.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI departmentOpenApi(AppInfo apiInfo){
        return new OpenAPI()
                .info(new Info().title(apiInfo.getTitle())
                        .description(apiInfo.getDescription())
                        .version(apiInfo.getVersion())
                        .contact(new Contact().email(apiInfo.getContactEmail()).name(apiInfo.getContactName()).url(apiInfo.getContactUrl()))
                        .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0.txt"))
                );

    }
}
