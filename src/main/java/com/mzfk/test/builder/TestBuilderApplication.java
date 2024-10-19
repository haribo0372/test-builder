package com.mzfk.test.builder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class TestBuilderApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().load();
		System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
		System.setProperty("TOKEN_KEY", dotenv.get("TOKEN_KEY"));

		SpringApplication.run(TestBuilderApplication.class, args);
	}

}
