package com.obs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ImportResource("classpath:/views.xml")
public class ObsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ObsApplication.class, args);
	}
}
