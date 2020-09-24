package com.haris.digital.homework.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.haris.digital.homework.*"})
@EnableJpaRepositories({"com.haris.digital.homework.*"})
@EntityScan({"com.haris.digital.homework.*"})
@PropertySource({"classpath:application.properties"})
@SpringBootApplication
public class SpringConfig {

	public static void main(String[] args) {
		SpringApplication.run(SpringConfig.class, args);
	}

}
