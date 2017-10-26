package com.feign;

import com.config.MyRobbinConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
//@RibbonClient(name="service-hi",configuration = MyRobbinConfig.class)
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
