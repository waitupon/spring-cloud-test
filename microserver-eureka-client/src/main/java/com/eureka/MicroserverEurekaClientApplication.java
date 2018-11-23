package com.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
//@EnableCircuitBreaker
@ComponentScan(basePackages={"com.controller"})
public class MicroserverEurekaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserverEurekaClientApplication.class, args);
	}


}
