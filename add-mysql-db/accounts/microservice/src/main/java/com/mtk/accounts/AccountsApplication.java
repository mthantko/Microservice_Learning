package com.mtk.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
/*@ComponentScans({ @ComponentScan("com.mtk.accounts.controller") })
@EnableJpaRepositories("com.mtk.accounts.repository")
@EntityScan("com.mtk.accounts.model")*/
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Accounts microservice REST API Documentation",
                description = "MTKBank Accounts microservice REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Phoenix",
                        email = "mithant.ko@gamil.com",
                        url = "https://www.phoenix.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.phoenix.com"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description =  "MTKBank Accounts microservice REST API Documentation",
                url = "https://www.phoenix.com/swagger-ui.html"
        )
)
public class AccountsApplication {

	public static void main(String[] args) {

        SpringApplication.run(AccountsApplication.class, args);
	}

}
