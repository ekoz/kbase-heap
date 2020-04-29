/*
 * Powered by http://ibotstat.com
 */
package com.eastrobot.heap.mq.E02_工作队列模式;

import com.eastrobot.heap.mq.RabbitConstant;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * consumer 和 简单模式 下的共用，起N个即可，分布式消费消息，
 * 消费速度越快，那么消费的消息就越多，调试的时候，可以在Consumer里修改休眠时间来观察
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/4/21 19:11
 */
public class MyProducer {

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
            // 发送消息
            String prefix = "你好世界";
            for (int i=0;i<10;i++){
                String message = prefix + "_" + i;
                channel.basicPublish("", QUEUE, null, message.getBytes());
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
