package org.example.financesaccountmentwebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

//@EnableWebSecurity
@SpringBootApplication
public class FinancesAccountmentWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancesAccountmentWebAppApplication.class, args);
	}

}
