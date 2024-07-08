package com.jamsirat.atmapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class deptechApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(deptechApiApplication.class, args);
	}

}
