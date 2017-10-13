package com.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan("com.*")
public class MicroserverFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserverFeignApplication.class, args);
	}

    @Bean
    @LoadBalanced
	RestTemplate restTemplate(){
         return new RestTemplate();
    }
}
