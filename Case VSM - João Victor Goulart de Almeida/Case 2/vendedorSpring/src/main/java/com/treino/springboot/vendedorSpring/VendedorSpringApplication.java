package com.treino.springboot.vendedorSpring;

import java.text.ParseException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//made by João Goulart
@SpringBootApplication
@ComponentScan(basePackages = "com.treino")
@EnableAutoConfiguration
@EntityScan(basePackages = "com.treino")
@EnableJpaRepositories
public class VendedorSpringApplication {

	public static void main(String[] args) throws ParseException {
		SpringApplication.run(VendedorSpringApplication.class, args);
		
		
	}
}
