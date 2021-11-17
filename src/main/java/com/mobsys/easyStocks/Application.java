package com.mobsys.easyStocks;

import java.util.List;

import com.mobsys.easyStocks.marketstack.CustomApi;
import com.mobsys.marketstack.model.EOD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

	@RequestMapping("/")
	public String home() {
		return "Hello Spring Boot";
	}

	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
		CustomApi api = new CustomApi();
		EOD eod = api.eodGet(List.of("AAPL")).block();
	}

}