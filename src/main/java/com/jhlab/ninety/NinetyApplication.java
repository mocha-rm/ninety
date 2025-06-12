package com.jhlab.ninety;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class NinetyApplication {

	public static void main(String[] args) {
		SpringApplication.run(NinetyApplication.class, args);
	}

}
