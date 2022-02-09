package com.sha.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@SpringBootApplication(exclude= SecurityAutoConfiguration.class)
@PropertySource("classpath:application-${spring.profiles.active:default}.properties")
public class SpringBootMicroservice3GatewayApplication {


	public static void main(String[] args) {
		SpringApplication.run(SpringBootMicroservice3GatewayApplication.class, args);
	}

}
