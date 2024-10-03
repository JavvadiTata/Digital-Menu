package com.karthiksoft.digitalmenu.digital_menu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages ={"com.karthiksoft.digitalmenu.digital_menu"})
public class DigitalMenuApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalMenuApplication.class, args);
	}

}
