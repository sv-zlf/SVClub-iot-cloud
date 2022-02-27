package com.zlf.iot.mqttx.service.impl;

import com.alibaba.fastjson.JSON;

import com.zlf.iot.mqttx.service.IInternalMessagePublishService;
import com.zlf.iot.mqttx.entity.InternalMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 基于 Redis 的实现
 *
 * @author Jun
 * @date 2020-05-14 10:30
 */
@Slf4j
public class InternalMessagePublishServiceImpl implements IInternalMessagePublishService {

    private StringRedisTemplate stringRedisTemplate;

    public InternalMessagePublishServiceImpl(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public <T> void publish(InternalMessage<T> internalMessage, String channel) {
        stringRedisTemplate.convertAndSend(channel, JSON.toJSONString(internalMessage));
    }
}
