package com.malik.CelioShop.CelioShop;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@OpenAPIDefinition(
		info = @Info(
				title = "CelioShop API",
				description = "CelioSho is an e-commerce API",
				version = "v1.0"
		),
		externalDocs = @ExternalDocumentation(
				description = "Link to the github repository of the project",
				url = "https://github.com/NullPointerFound/CelioShop"
		)
)
@SpringBootApplication
public class CelioShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(CelioShopApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
