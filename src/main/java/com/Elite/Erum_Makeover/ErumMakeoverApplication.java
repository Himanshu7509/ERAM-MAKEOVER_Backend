package com.Elite.Erum_Makeover;


import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ErumMakeoverApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErumMakeoverApplication.class, args);

	}
	@PostConstruct
	public void checkRedisEnv() {
		System.out.println("HOST: " + System.getenv("REDISHOST"));
		System.out.println("PORT: " + System.getenv("REDISPORT"));
	}
}


