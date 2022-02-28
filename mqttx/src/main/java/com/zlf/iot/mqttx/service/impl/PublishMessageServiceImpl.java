package com.zlf.iot.mqttx.service.impl;

import com.alibaba.fastjson.JSON;
import com.zlf.iot.mqttx.common.config.BizConfig;
import com.zlf.iot.mqttx.entity.PubMsg;
import com.zlf.iot.mqttx.service.IPublishMessageService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * publish message store by redis
 *
 * @author Jun
 * @date 2020-03-13 14:33
 */
@Service
public class PublishMessageServiceImpl implements IPublishMessageService {


    private StringRedisTemplate stringRedisTemplate;

    private String pubMsgSetPrefix;

    public PublishMessageServiceImpl(StringRedisTemplate stringRedisTemplate, BizConfig bizConfig) {
        this.stringRedisTemplate = stringRedisTemplate;

        this.pubMsgSetPrefix = bizConfig.getPubMsgSetPrefix();
        Assert.hasText(pubMsgSetPrefix, "pubMsgSetPrefix can't be null");
    }

    @Override
    public void save(String clientId, PubMsg pubMsg) {
        stringRedisTemplate.opsForHash().put(pubMsgSetPrefix + clientId,
                String.valueOf(pubMsg.getMessageId()), JSON.toJSONString(pubMsg));
    }

    @Override
    public void clear(String clientId) {
        stringRedisTemplate.delete(pubMsgSetPrefix + clientId);
    }

    @Override
    public void remove(String clientId, int messageId) {
        stringRedisTemplate.opsForHash().delete(
                key(clientId),
                String.valueOf(messageId)
        );
    }

    @Override
    public List<PubMsg> search(String clientId) {
        List<Object> values = stringRedisTemplate.opsForHash().values(key(clientId));
        if (values == null) {
            return Collections.EMPTY_LIST;
        }

        return values.stream()
                .map(o -> JSON.parseObject((String) o, PubMsg.class))
                .collect(Collectors.toList());
    }

    private String key(String client) {
        return pubMsgSetPrefix + client;
    }
}
