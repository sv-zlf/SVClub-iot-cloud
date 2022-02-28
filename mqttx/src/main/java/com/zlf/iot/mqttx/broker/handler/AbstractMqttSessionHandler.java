package com.zlf.iot.mqttx.broker.handler;


import com.zlf.iot.mqttx.entity.Authentication;
import com.zlf.iot.mqttx.entity.Session;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

/**
 * 改抽象类提供 {@link Session} 相关方法
 *
 * @author Jun
 * @date 2020-03-07 22:20
 */
public abstract class AbstractMqttSessionHandler implements MqttMessageHandler {


    public static final String AUTHORIZED_PUB_TOPICS = "authorizedPubTopics";
    public static final String AUTHORIZED_SUB_TOPICS = "authorizedSubTopics";

    /**
     * 生成消息ID
     *
     * @param ctx {@link ChannelHandlerContext}
     * @return 消息ID
     */
    int nextMessageId(ChannelHandlerContext ctx) {
        Session session = getSession(ctx);
        return session.increaseAndGetMessageId();
    }

    /**
     * 返回客户id
     *
     * @param ctx {@link ChannelHandlerContext}
     * @return clientId
     */
    String clientId(ChannelHandlerContext ctx) {
        Session session = getSession(ctx);
        return session.getClientId();
    }

    /**
     * 获取当前会话的 clearSession
     *
     * @param ctx {@link ChannelHandlerContext}
     * @return true if clearSession = 1
     */
    boolean clearSession(ChannelHandlerContext ctx) {
        Session session = getSession(ctx);
        return session.getClearSession();
    }

    /**
     * \     * 存储当前会话状态
     *
     * @param ctx     {@link ChannelHandlerContext}
     * @param session mqtt会话
     */
    void saveSessionWithChannel(ChannelHandlerContext ctx, Session session) {
        Channel channel = ctx.channel();
        AttributeKey<Object> attr = AttributeKey.valueOf(Session.KEY);
        channel.attr(attr).set(session);
    }

    /**
     * 保存 client 被授权订阅的 topic 列表
     *
     * @param ctx            {@link ChannelHandlerContext}
     * @param authentication {@link Authentication}
     */
    void saveAuthorizedTopics(ChannelHandlerContext ctx, Authentication authentication) {
        if (authentication == null) {
            return;
        }
        Channel channel = ctx.channel();
        channel.attr(AttributeKey.valueOf(AUTHORIZED_SUB_TOPICS)).set(authentication.getAuthorizedSub());
        channel.attr(AttributeKey.valueOf(AUTHORIZED_PUB_TOPICS)).set(authentication.getAuthorizedPub());
    }

    /**
     * 获取客户会话
     *
     * @param ctx {@link ChannelHandlerContext}
     * @return {@link Session}
     */
    private Session getSession(ChannelHandlerContext ctx) {
        return (Session) ctx.channel().attr(AttributeKey.valueOf(Session.KEY)).get();
    }
}
