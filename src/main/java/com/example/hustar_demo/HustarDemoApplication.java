package com.example.hustar_demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;

@OpenAPIDefinition(info = @Info(title = "Hustar API 명세서",
        description = "API 명세서",
        version = "v1",
        contact = @Contact(name = "taehyeonkim", email = "taehyeonkim@kakao.com")
)
)
@SpringBootApplication
public class HustarDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HustarDemoApplication.class, args);
    }


}
