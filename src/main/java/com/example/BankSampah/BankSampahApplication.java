package com.example.BankSampah;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class BankSampahApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankSampahApplication.class, args);
	}

}
