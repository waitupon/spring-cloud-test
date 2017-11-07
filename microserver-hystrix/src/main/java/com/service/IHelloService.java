package com.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2017/9/21 0021.
 */
@FeignClient(name ="service-hi",fallback = HelloServiceHystrix.class)
public interface IHelloService {

    @RequestMapping(method = RequestMethod.GET, value = "/hi")
    public String sayHi(@RequestParam(value="name",required = false) String name);
}
