/*
 * Powered by http://ibotstat.com
 */
package com.eastrobot.heap.mq.E01_简单模式;

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
public class MyConsumer {


    private final static String QUEUE = "helloworld";

    public static void main(String[] args) {
        // 通过连接工厂创建新的连接和 mq 建立连接
        ConnectionFactory connectionFactory = RabbitConstant.getConnectionFactory();

        Connection connection = null;
        Channel channel = null;
        try {
            // 创建连接
            connection = connectionFactory.newConnection();
            // 创建通道
            channel = connection.createChannel();
            // 声明一个队列
            channel.queueDeclare(QUEUE, true, false, false, null);

            DefaultConsumer defaultConsumer = new CommonDefaultConsumer(channel);
            // 消费消息
            channel.basicConsume(QUEUE, true, defaultConsumer);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {

        }
    }
}
