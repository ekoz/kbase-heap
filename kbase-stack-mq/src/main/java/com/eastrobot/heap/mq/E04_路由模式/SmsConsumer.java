/*
 * Powered by http://ibotstat.com
 */
package com.eastrobot.heap.mq.E04_路由模式;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/4/21 19:27
 */
public class SmsConsumer extends MyConsumer{

    private final static String QUEUE_SMS = "queue.sms";
    private final static String KEY_SMS = "key.sms";

    public static void main(String[] args) {
        SmsConsumer smsConsumer = new SmsConsumer();
        smsConsumer.execute();
    }

    @Override
    public String getQueue() {
        return QUEUE_SMS;
    }

    @Override
    public String[] getRoutingKeys() {
        return new String[]{KEY_SMS};
    }

    @Override
    public void bindingExchange(Channel channel, String exchange) throws IOException {
        for (String routingKey : getRoutingKeys()){
            channel.queueBind(getQueue(), exchange, routingKey);
        }
    }
}
