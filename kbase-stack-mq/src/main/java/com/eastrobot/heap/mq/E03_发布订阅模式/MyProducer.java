/*
 * Powered by http://ibotstat.com
 */
package com.eastrobot.heap.mq.E03_发布订阅模式;

import com.eastrobot.heap.mq.RabbitConstant;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 与工作队列不同的是，发布订阅要求所有的消费者都能得到消息
 * 此时的模型是通过 exchange 将 消息分发到各自的 queue，consumer 监听自己的quene
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/4/21 19:11
 */
public class MyProducer {

    private static final String EXCHANGE_NAME = "exchange_fanout";
    private final static String QUEUE_SMS = "queue.sms";
    private final static String QUEUE_EMAIL = "queue.email";
    private final static String QUEUE_WX = "queue.wx";
    private final static String QUEUE_IOS = "queue.ios";

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
            // 声明一个交换机
            // 四种类型 fanout, direct, topic, headers 区别是啥？
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

            // 声明 QUEUE_SMS 队列
            channel.queueDeclare(QUEUE_SMS, true, false, false, null);
            // 声明 QUEUE_EMAIL 队列
            channel.queueDeclare(QUEUE_EMAIL, true, false, false, null);

            // 绑定到交换机
            channel.queueBind(QUEUE_SMS, EXCHANGE_NAME, "");
            // 绑定到交换机
            channel.queueBind(QUEUE_EMAIL, EXCHANGE_NAME, "");

            // 发送消息
            String prefix = "send inform message";
            for (int i=0;i<10;i++){
                String message = prefix + "_" + i;
                channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
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
