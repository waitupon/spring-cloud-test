package com.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2017/9/21 0021.
 */
//@Repository
@FeignClient("service-hi")
public interface IHelloService {

    @RequestMapping(value="/hi")
    public String home(@RequestParam("name") String name);
}
