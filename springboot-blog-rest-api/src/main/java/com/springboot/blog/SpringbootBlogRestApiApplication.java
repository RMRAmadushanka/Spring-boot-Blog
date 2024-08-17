package com.springboot.blog;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info=@Info(
				title = "Spring Boot Blog APP APIs",
				description = "Spring boot Blog APP API documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Rolan"
				)
		)
)
public class SpringbootBlogRestApiApplication {
	@Bean
	public ModelMapper modelMapper (){
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringbootBlogRestApiApplication.class, args);
	}

}
