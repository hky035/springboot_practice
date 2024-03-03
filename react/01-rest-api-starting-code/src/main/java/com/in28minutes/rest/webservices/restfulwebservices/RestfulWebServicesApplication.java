package com.in28minutes.rest.webservices.restfulwebservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class RestfulWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer(){
			return new WebMvcConfigurer() {
				public void addCorsMappings(CorsRegistry registry) { // CORS 설정 커스터마이징
					registry.addMapping("/**")
							.allowedMethods("*")
							.allowedOrigins("http://localhost:3000");
				}
			};
		}
	}

