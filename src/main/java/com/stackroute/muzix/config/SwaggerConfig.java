package com.stackroute.muzix.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.stackroute")).paths(regex("/api/v1/.*")).build().apiInfo(app());
    }

    private ApiInfo app(){
        ApiInfo apiInfo = new ApiInfo("Muzix","Muzix Spring boot Application ","1.0","Terms Of Service","Muzix aka playmusic","Apache License Version 2.0","https://www.apache.org/license.html");
        return apiInfo;
    }
}