package com.zlf.iot.apiconsumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(value = "product-provider")
public interface ProductService {
    @RequestMapping(value = "/test/initialize", method = RequestMethod.GET)
    String sayHiFromClientOne();

}
