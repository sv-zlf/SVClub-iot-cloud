package com.zlf.iot.mqttx.broker.handler;

import com.zlf.iot.mqttx.broker.BrokerHandler;
import com.zlf.iot.mqttx.common.constant.InternalMessageEnum;
import com.zlf.iot.mqttx.common.consumer.Watcher;
import com.zlf.iot.mqttx.entity.InternalMessage;
import com.zlf.iot.mqttx.entity.Session;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.handler.codec.mqtt.*;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;

/**
 * {@link MqttMessageType#DISCONNECT} 消息处理器
 *
 * @author Jun
 * @date 2020-03-03 23:30
 */
@Slf4j
@Handler(type = MqttMessageType.DISCONNECT)
public final class DisconnectHandler extends AbstractMqttSessionHandler implements Watcher<String> {


    private ConnectHandler connectHandler;

    public DisconnectHandler(ConnectHandler connectHandler) {
        this.connectHandler = connectHandler;
    }

    @Override
    public void process(ChannelHandlerContext ctx, MqttMessage msg) {
        if (clearSession(ctx)) {
            connectHandler.actionOnCleanSession(clientId(ctx));
        }


        //[MQTT-3.1.2-8]
        //If the Will Flag is set to 1 this indicates that, if the Connect request is accepted, a Will Message MUST be
        //stored on the Server and associated with the Network Connection. The Will Message MUST be published when the
        //Network Connection is subsequently closed unless the Will Message has been deleted by the Server on receipt of
        //a DISCONNECT Packet.
        Session session = (Session) ctx.channel().attr(AttributeKey.valueOf(Session.KEY)).get();
        session.clearWillMessage();

        ctx.close();
    }

    @Override
    public void action(InternalMessage<String> im) {
        ChannelId channelId = ConnectHandler.clientMap.get(im.getData());
        if (channelId != null) {
            BrokerHandler.channels.find(channelId).close();
        }
    }

    @Override
    public boolean support(String channel) {
        return InternalMessageEnum.DISCONNECT.getChannel().equals(channel);
    }
}
