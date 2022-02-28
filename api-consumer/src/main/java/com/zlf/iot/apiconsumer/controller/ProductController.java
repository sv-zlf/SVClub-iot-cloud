package com.zlf.iot.apiconsumer.controller;


import com.zlf.iot.apiconsumer.service.ProductService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "物联网云产品服务")
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping(value = "/hi")
    public String sayHi() {
        return productService.sayHiFromClientOne();
    }
}
