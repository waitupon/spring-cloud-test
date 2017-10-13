package com.controller;

import com.service.IHelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by waitupon17 on 2017/8/25.
 */
@RestController
public class HelloControler {
    @Resource
    IHelloService helloService;

    @GetMapping(value = "/hi")
    public String hi(@RequestParam String name){
        return helloService.sayHi(name);
    }
}
