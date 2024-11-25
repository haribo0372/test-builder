package com.mzfk.test.builder;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestBuilderApplication {

    public static void main(String[] args) {
        configureEnvironment();
        SpringApplication.run(TestBuilderApplication.class, args);
    }

    private static void configureEnvironment() {
        Dotenv dotenv = Dotenv.configure().load();
        System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
        System.setProperty("TOKEN_KEY", dotenv.get("TOKEN_KEY"));
    }
}
