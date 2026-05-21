package com.example.firstclub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.firstclub.repository")
public class FirstclubApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstclubApplication.class, args);
	}

}
