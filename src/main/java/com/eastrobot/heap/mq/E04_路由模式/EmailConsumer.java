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
public class EmailConsumer extends MyConsumer {


    private final static String QUEUE_EMAIL = "queue.email";
    private static final String KEY_EMAIL = "key.email";
    private static final String KEY_EMAIL2 = "key.email2";


    public static void main(String[] args) {
        EmailConsumer emailConsumer = new EmailConsumer();
        emailConsumer.execute();
    }

    @Override
    public String getQueue() {
        return QUEUE_EMAIL;
    }

    @Override
    public String[] getRoutingKeys() {
        return new String[]{KEY_EMAIL, KEY_EMAIL2};
    }

    @Override
    public void bindingExchange(Channel channel, String exchange) throws IOException {

        for (String routingKey : getRoutingKeys()){
            channel.queueBind(getQueue(), exchange, routingKey);
        }
    }
}
