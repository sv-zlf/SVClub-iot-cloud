package com.zlf.iot.authserver.controller;

import com.zlf.iot.authserver.service.SelfUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/oauth")
public class check_token {
    @Autowired
    SelfUserDetailsService selfUserDetailsService;
    @RequestMapping("/check_token")
    public Object getCurrentUser(@RequestParam("token") String token) {
        System.out.println(token);
        return selfUserDetailsService.check_token(token);
    }
    @RequestMapping("/test")
    public String getCurrentUser() {

        return "ssss";
    }
}
