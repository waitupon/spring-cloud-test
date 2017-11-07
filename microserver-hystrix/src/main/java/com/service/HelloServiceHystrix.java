package com.service;

public class HelloServiceHystrix implements IHelloService{
    @Override
    public String sayHi(String name) {
        return "hystrix-user";
    }
}
