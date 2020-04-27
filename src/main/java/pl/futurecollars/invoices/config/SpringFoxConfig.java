package pl.futurecollars.invoices.config;

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
public class SpringFoxConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("pl.futurecollars.invoices"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getApiInfo() {
        final Contact contact = new Contact(
                "Java Developer, group 18",
                "https://github.com/CodersTrustPL/project-18-jakub-slawomir-tomasz-tomasz-wojciech",
                "info@somedomain.com");
        return new ApiInfoBuilder()
                .title("Invoicing System")
                .description("Invoice repository and management system")
                .version("0.0.1")
                .license("Apache License, Version 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .contact(contact)
                .build();
    }
}
