package com.zlf.iot.mqttx.broker.handler;

import com.zlf.iot.mqttx.service.ISubscriptionService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.*;

/**
 * {@link MqttMessageType#UNSUBSCRIBE} 消息处理器
 *
 * @author Jun
 * @date 2020-03-04 16:07
 */
@Handler(type = MqttMessageType.UNSUBSCRIBE)
public class UnsubscribeHandler extends AbstractMqttSessionHandler {

    private ISubscriptionService subscriptionService;

    public UnsubscribeHandler(ISubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @Override
    public void process(ChannelHandlerContext ctx, MqttMessage msg) {
        MqttUnsubscribeMessage mqttUnsubscribeMessage = (MqttUnsubscribeMessage) msg;
        int messageId = mqttUnsubscribeMessage.variableHeader().messageId();
        MqttUnsubscribePayload payload = mqttUnsubscribeMessage.payload();

        //unsubscribe
        subscriptionService.unsubscribe(clientId(ctx), payload.topics());

        //response
        MqttMessage mqttMessage = MqttMessageFactory.newMessage(
                new MqttFixedHeader(MqttMessageType.UNSUBACK, false, MqttQoS.AT_MOST_ONCE, false, 0),
                MqttMessageIdVariableHeader.from(messageId),
                null
        );
        ctx.writeAndFlush(mqttMessage);
    }
}
