/*
 * Powered by http://ibotstat.com
 */
package com.eastrobot.heap.mq.E04_路由模式;

import com.eastrobot.heap.mq.CommonDefaultConsumer;
import com.eastrobot.heap.mq.RabbitConstant;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/4/21 19:27
 */
public abstract class MyConsumer {

    private static final String EXCHANGE_NAME = "exchange_direct";

    public void execute(){
        // 通过连接工厂创建新的连接和 mq 建立连接
        ConnectionFactory connectionFactory = RabbitConstant.getConnectionFactory();

        Connection connection = null;
        Channel channel = null;
        try {
            // 创建连接
            connection = connectionFactory.newConnection();
            // 创建通道
            channel = connection.createChannel();

            // 四种类型 fanout, direct, topic, headers 区别是啥？
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

            // 声明 QUEUE_SMS 队列
            channel.queueDeclare(getQueue(), true, false, false, null);

            // 绑定到交换机
            bindingExchange(channel, EXCHANGE_NAME);

            DefaultConsumer defaultConsumer = new CommonDefaultConsumer(channel);
            // 消费消息
            channel.basicConsume(getQueue(), true, defaultConsumer);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {

        }
    }

    /**
     * 当前队列
     * @return
     */
    public abstract String getQueue();

    /**
     * routingKey
     * @return
     */
    public abstract String[] getRoutingKeys();

    /**
     * 绑定交换机
     * @param channel
     * @param exchange
     */
    public abstract void bindingExchange(Channel channel, String exchange) throws IOException;
}
