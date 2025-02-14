package com.namma.metro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.namma.metro.client")
public class MetroApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetroApplication.class, args);
	}

}
