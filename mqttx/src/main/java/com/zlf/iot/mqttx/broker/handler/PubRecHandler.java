package com.zlf.iot.mqttx.broker.handler;

import com.zlf.iot.mqttx.service.IPubRelMessageService;
import com.zlf.iot.mqttx.service.IPublishMessageService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.*;

/**
 * {@link MqttMessageType#PUBREC} 消息处理器
 *
 * @author Jun
 * @date 2020-03-04 16:01
 */
@Handler(type = MqttMessageType.PUBREC)
public class PubRecHandler extends AbstractMqttSessionHandler {

    private IPubRelMessageService pubRelMessageService;

    private IPublishMessageService publishMessageService;

    public PubRecHandler(IPubRelMessageService pubRelMessageService, IPublishMessageService publishMessageService) {
        this.pubRelMessageService = pubRelMessageService;
        this.publishMessageService = publishMessageService;
    }

    @Override
    public void process(ChannelHandlerContext ctx, MqttMessage msg) {
        //移除消息
        MqttMessageIdVariableHeader mqttMessageIdVariableHeader = (MqttMessageIdVariableHeader) msg.variableHeader();
        int messageId = mqttMessageIdVariableHeader.messageId();
        String clientId = clientId(ctx);
        publishMessageService.remove(clientId, messageId);

        //保存 pubRec
        pubRelMessageService.save(clientId, messageId);

        MqttMessage mqttMessage = MqttMessageFactory.newMessage(
                new MqttFixedHeader(MqttMessageType.PUBREL, false, MqttQoS.AT_LEAST_ONCE, false, 0),
                MqttMessageIdVariableHeader.from(messageId),
                null
        );
        ctx.writeAndFlush(mqttMessage);
    }
}
