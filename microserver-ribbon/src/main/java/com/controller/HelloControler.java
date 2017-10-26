package com.controller;

import com.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by waitupon17 on 2017/8/25.
 */
@RestController
public class HelloControler {
    @Autowired
    HelloService helloService;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping(value = "/hi")
    public String hi(@RequestParam String name){
        return helloService.hiService(name);
    }


    @GetMapping(value = "/test")
    public void test(){
        ServiceInstance instance = loadBalancerClient.choose("service-hi");
        System.out.println(instance.getHost() + ":" + instance.getPort());
    }
}
