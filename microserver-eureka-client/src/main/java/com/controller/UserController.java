package com.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by waitupon17 on 2017/8/25.
 */
@RestController
@Component
public class UserController {
    @Value("${server.port}")
    String port;
    @RequestMapping("/hi")
    @HystrixCommand(fallbackMethod = "homeFallback")
    public String home(@RequestParam(value="name",required = false) String name) {
        System.out.println("my port is " + port);
        return "hi "+name+".i am from port:" +port;
    }

    public String homeFallback(String name){
        return "homeFallback:" + name;
    }

}
