package com.evolocity.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.evolocity.springboot.configuration.DatabaseConfiguration;


@Import(DatabaseConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.evolocity.springboot"})
public class Application {

	/**
	 * A simple Spring boot application
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
