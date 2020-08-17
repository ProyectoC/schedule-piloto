package com.schedulepiloto.core.config;

import com.schedulepiloto.core.constants.ApplicationConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String SPRING_ADMIN = "/actuator";
    public static final String SWAGGER_UI = "/swagger-ui.html";
    public static final String SWAGGER_URL = "http://localhost:8080/api/v1/swagger-ui.html";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(ApplicationConstants.APPLICATION_NAME)
                .version(ApplicationConstants.APPLICATION_VERSION)
                .description(ApplicationConstants.APPLICATION_DESCRIPTION)
                .termsOfServiceUrl(ApplicationConstants.APPLICATION_SERVICES)
                .contact(new Contact("Felipe Galicia", "https://github.com/ProyectoC/", "frediih@hotmail.es"))
                .license("License MIT")
                .licenseUrl("https://opensource.org/licenses/MIT")
                .build();
    }
}
