package com.vikram.macquarie.bankdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot app to support a web application that allows an user to view transactions
 * on any of the accounts they hold.
 */
@SpringBootApplication
public class BankdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankdemoApplication.class, args);
	}

}
