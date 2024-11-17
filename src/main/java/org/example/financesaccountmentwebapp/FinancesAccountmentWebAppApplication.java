package org.example.financesaccountmentwebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Главный класс приложения для веб-приложения учета финансов
 */
@SpringBootApplication
public class FinancesAccountmentWebAppApplication {

    /**
     * Точка входа в приложение
     */
    public static void main(String[] args) {
        SpringApplication.run(FinancesAccountmentWebAppApplication.class, args);
    }
}