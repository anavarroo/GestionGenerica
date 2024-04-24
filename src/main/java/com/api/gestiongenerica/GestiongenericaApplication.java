package com.api.gestiongenerica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class GestiongenericaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestiongenericaApplication.class, args);
	}

}

