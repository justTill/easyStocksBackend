package com.mobsys.easyStocks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableScheduling
@RestController
public class Application {

	@RequestMapping("/")
	public String home() {
		return "Hello Spring Boot";
	}

	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
	}
}