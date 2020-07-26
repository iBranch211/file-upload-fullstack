package spring.files.upload.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("spring.files.upload.controller"))
                .build()
                .apiInfo(metaData());

    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("File Upload REST API")
                .description("\"Spring Boot REST API for File Upload\"")
                .version("1.0.0")
                .build();
    }

}
