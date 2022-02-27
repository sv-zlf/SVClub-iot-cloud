package com.zlf.iot.mqttx.common.consumer;

import com.zlf.iot.mqttx.common.config.BizConfig;
import com.zlf.iot.mqttx.common.constant.InternalMessageEnum;
import com.zlf.iot.mqttx.entity.InternalMessage;

/**
 * 观察者，实现此接口。当 {@link BizConfig#getEnableCluster()} 开启后
 *
 * @author Jun
 * @date 2020-05-14 09:15
 */
public interface Watcher<T> {

    /**
     * 每当有新的集群消息达到是，触发行为。注意：方法不允许出现阻塞操作
     *
     * @param im {@see InternalMessage}
     */
    void action(InternalMessage<T> im);

    /**
     * Watcher 支持的 channel 类别
     *
     * @param channel {@link InternalMessageEnum}
     * @return true if Watcher support
     */
    boolean support(String channel);
}
