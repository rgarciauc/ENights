package com.garcia.hotelmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.garcia.hotelmanager")
public class HotelmanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelmanagerApplication.class, args);
	}

}
