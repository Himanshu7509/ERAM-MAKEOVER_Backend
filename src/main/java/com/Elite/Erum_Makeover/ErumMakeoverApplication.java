package com.Elite.Erum_Makeover;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ErumMakeoverApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErumMakeoverApplication.class, args);
	}

}
