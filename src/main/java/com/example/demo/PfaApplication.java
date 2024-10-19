package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(proxyBeanMethods = false)
public class PfaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PfaApplication.class, args);
	}

}
