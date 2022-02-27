package com.zlf.iot.productprovider.controller;


import com.zlf.iot.productprovider.common.VO.GlobalResult;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: 钟林锋
 * @Date: 2021 12) 27) 14:24
 * @Description:
 */

@RestController
@RequestMapping("/test")
@CrossOrigin

public class test {

    @GetMapping("/initialize")
    // @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记", required = true) })
    public GlobalResult initialize() {

        GlobalResult result = GlobalResult.build(200, "初始化成功", null);
        return result;
    }

    @GetMapping("/initialize2")
    public GlobalResult initialize2() {

        GlobalResult result = GlobalResult.build(200, "初始化成功", null);
        return result;
    }
}
