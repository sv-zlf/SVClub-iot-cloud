package com.zlf.iot.mqttx.broker.handler;

import com.zlf.iot.mqttx.service.IPubRelMessageService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.*;

/**
 * {@link MqttMessageType#PUBCOMP} 消息处理器
 *
 * @author Jun
 * @date 2020-03-04 16:03
 */
@Handler(type = MqttMessageType.PUBCOMP)
public class PubComHandler extends AbstractMqttSessionHandler {

    private IPubRelMessageService pubRelMessageService;

    public PubComHandler(IPubRelMessageService pubRelMessageService) {
        this.pubRelMessageService = pubRelMessageService;
    }

    @Override
    public void process(ChannelHandlerContext ctx, MqttMessage msg) {
        MqttMessageIdVariableHeader mqttMessageIdVariableHeader = (MqttMessageIdVariableHeader) msg.variableHeader();
        int messageId = mqttMessageIdVariableHeader.messageId();
        String clientId = clientId(ctx);
        pubRelMessageService.remove(clientId, messageId);

        MqttMessage mqttMessage = MqttMessageFactory.newMessage(
                new MqttFixedHeader(MqttMessageType.PUBCOMP, false, MqttQoS.AT_MOST_ONCE, false, 0),
                MqttMessageIdVariableHeader.from(messageId),
                null
        );
        ctx.writeAndFlush(mqttMessage);
    }

}
