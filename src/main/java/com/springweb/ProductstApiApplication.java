package com.springweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
//@EnableAuthorizationServer
public class ProductstApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductstApiApplication.class, args);      
	}  
}
