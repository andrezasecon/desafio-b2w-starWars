package br.com.andrezasecon.b2w.apiplanet.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class ApiSwapiSwagger {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.andrezasecon.b2w.apiplanet"))
                .paths(regex("/api.*"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "API  REST Planetas",
                "API Rest para busca de planetas, consumindo a SWAPI para buscar número de aparições" +
                        " em filmes para cada planeta",
                "1.0",
                " ",
                new Contact("Andreza Secon", "https://www.linkedin.com/in/andreza-secon-b5736788/",
                        "andrezasecon@gmail.com"),
                " ", " ", Collections.emptyList());

    }
}