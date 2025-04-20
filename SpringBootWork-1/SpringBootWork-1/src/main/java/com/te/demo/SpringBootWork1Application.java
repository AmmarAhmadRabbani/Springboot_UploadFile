package com.te.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootApplication
public class SpringBootWork1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWork1Application.class, args);
	}

	@Bean
	public ObjectMapper mapper() {
		return new ObjectMapper().registerModule(new JavaTimeModule());
	}

}
