package com.zlf.iot.mqttx.broker.handler;

import com.zlf.iot.mqttx.service.IPublishMessageService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttMessageType;
import io.netty.handler.codec.mqtt.MqttPubAckMessage;

/**
 * {@link MqttMessageType#PUBACK} 消息处理器
 *
 * @author Jun
 * @date 2020-03-04 15:59
 */
@Handler(type = MqttMessageType.PUBACK)
public class PubAckHandler extends AbstractMqttSessionHandler {

    private IPublishMessageService publishMessageService;

    public PubAckHandler(IPublishMessageService publishMessageService) {
        this.publishMessageService = publishMessageService;
    }

    @Override
    public void process(ChannelHandlerContext ctx, MqttMessage msg) {
        MqttPubAckMessage mqttPubAckMessage = (MqttPubAckMessage) msg;
        int messageId = mqttPubAckMessage.variableHeader().messageId();
        publishMessageService.remove(clientId(ctx), messageId);
    }
}
