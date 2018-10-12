package com.kafka.example.kafka.example.configurations

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import java.util.*


@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun api(): Docket = Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kafka.example.kafka.example.resources"))
                .paths(PathSelectors.ant("/api/*"))
                .build()
                .apiInfo(apiInfo())


    private fun apiInfo(): ApiInfo = ApiInfo(
                "Kafka Example Doc API",
                "Some custom description of API.",
                "API KAFKA EXAMPLE",
                "Terms of service",
                Contact("Leonardo Mendes", "www.github.com/leonardo-mendes", "leonardocm92@hotmail.com"),
                "License of API", "API license URL", Collections.emptyList())

}