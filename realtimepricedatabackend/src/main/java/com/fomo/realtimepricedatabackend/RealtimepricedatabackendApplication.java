package com.fomo.realtimepricedatabackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RealtimepricedatabackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(RealtimepricedatabackendApplication.class, args);
	}
}
