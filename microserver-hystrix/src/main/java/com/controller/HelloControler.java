package com.controller;

import com.service.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by waitupon17 on 2017/8/25.
 */
@RestController
public class HelloControler {
    @Autowired
    IHelloService helloService;

    @GetMapping(value = "/hi")
    public String hi(@RequestParam(value="name",required = false) String name){
        return helloService.sayHi(name);
    }
}
