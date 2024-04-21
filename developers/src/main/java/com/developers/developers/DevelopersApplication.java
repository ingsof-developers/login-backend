package com.developers.developers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class DevelopersApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevelopersApplication.class, args);
	}

}
