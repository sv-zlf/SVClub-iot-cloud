package com.zlf.iot.mqttx.service;

import com.zlf.iot.mqttx.common.VO.GlobalResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "product-provider")
public interface ProFeignService {

    @GetMapping("/device/{productKey}/{deviceName}/{deviceSecret}")
    GlobalResult authDevice(@PathVariable String productKey,@PathVariable String deviceName, @PathVariable String deviceSecret);

    @PutMapping("/device/{productKey}/{deviceName}")
    GlobalResult updateclientId(@PathVariable String productKey, @PathVariable String deviceName, @RequestParam String clientId);

    @PutMapping("/device")
    GlobalResult updateStatus(@RequestParam String clientId,@RequestParam String deviceStatus );
}

