package com.falcon.CostMate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.falcon.CostMate.Entity") // Adjust package as necessary
@ComponentScan(basePackages = "com.falcon.CostMate")
@EnableJpaRepositories(basePackages = "com.falcon.CostMate.Repositories")
public class CostMateApplication {

	public static void main(String[] args) {
		SpringApplication.run(CostMateApplication.class, args);
		System.out.println("Hello");
	}

}
