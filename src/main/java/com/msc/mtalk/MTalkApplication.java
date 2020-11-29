package com.msc.mtalk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class MTalkApplication {

	public static void main(String[] args) {
		SpringApplication.run(MTalkApplication.class, args);
	}

}
