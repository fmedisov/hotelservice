package ru.medisov.hotelservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static com.google.common.collect.Lists.newArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        final String basePackage= "ru.medisov.hotelservice.endpoint";
        ArrayList<ResponseMessage> messages = newArrayList(new ResponseMessageBuilder()
                .code(400)
                .message("Invalid hotel model")
                .build());

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build().useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.POST, messages)
                .globalResponseMessage(RequestMethod.PUT, messages);
    }
}