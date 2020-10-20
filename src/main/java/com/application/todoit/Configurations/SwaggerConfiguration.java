package com.application.todoit.Configurations;

import org.springframework.context.annotation.*;
import springfox.documentation.builders.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
               // .apiInfo(apiInfo());
    }

//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder().title("API Documentation").description("")
//                .termsOfServiceUrl("http://www.kaptea.info")
//                .contact(new Contact("Developers", "NA", "kapteatutorials@gmail.com"))
//                .licenseUrl("\"https://www.apache.org/licenses/LICENSE-2.0")
//                .version("2.0.0")
//                .build();
//    }
}