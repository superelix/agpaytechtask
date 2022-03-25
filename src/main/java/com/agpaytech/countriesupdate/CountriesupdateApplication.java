package com.agpaytech.countriesupdate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.agpaytech.countriesupdate")
public class CountriesupdateApplication {

	public static void main(String[] args) {
		SpringApplication.run(CountriesupdateApplication.class, args);
	}

}
