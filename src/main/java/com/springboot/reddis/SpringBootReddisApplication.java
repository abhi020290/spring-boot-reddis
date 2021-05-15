package com.springboot.reddis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRedisRepositories
public class SpringBootReddisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootReddisApplication.class, args);
	}

}
