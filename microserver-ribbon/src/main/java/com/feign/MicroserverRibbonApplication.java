package com.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages={"com.*"})
public class MicroserverRibbonApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserverRibbonApplication.class, args);
	}

    @Bean
    @LoadBalanced
	RestTemplate restTemplate(){
         return new RestTemplate();
    }
}
