package com.mtk.card;

import com.mtk.card.dto.CardsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
/*@ComponentScans({ @ComponentScan("com.mtk.cards.controller") })
@EnableJpaRepositories("com.mtk.cards.repository")
@EntityScan("com.mtk.cards.model")*/
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {CardsContactInfoDto.class})
@OpenAPIDefinition(
        info = @Info(
                title = "Cards microservice REST API Documentation",
                description = "MTK Bank Cards microservice REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Phoenix",
                        email = "phoenix.com",
                        url = "https://www.phoenix.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.phoenix.com"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "MTK Cards microservice REST API Documentation",
                url = "https://www.phoenix.com/swagger-ui.html"
        )
)
public class CardApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardApplication.class, args);
	}

}
