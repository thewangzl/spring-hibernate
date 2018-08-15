package com.thewangzl.sh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.ComponentScan.Filter;

@SpringBootApplication
@Configuration
@ComponentScan(excludeFilters={@Filter(type=FilterType.ANNOTATION,classes=Repository.class)})
public class ShApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShApplication.class, args);
	}
	
}
