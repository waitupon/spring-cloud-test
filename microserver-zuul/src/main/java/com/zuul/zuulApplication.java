package com.zuul;


import com.zuul.filter.PreRequestLogFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class zuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(zuulApplication.class, args);
    }


    @Bean
    public PreRequestLogFilter preRequestLogFilter(){
        return new PreRequestLogFilter();
    }
}
