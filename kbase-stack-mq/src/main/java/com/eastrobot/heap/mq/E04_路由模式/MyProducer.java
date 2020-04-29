/*
 * Powered by http://ibotstat.com
 */
package com.eastrobot.heap.mq.E04_路由模式;

import com.eastrobot.heap.mq.RabbitConstant;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * exchange type is direct
 * 路由模式是一个增强版的发布订阅模式，发布订阅是每个消费者都能消费消息
 * 而路由模式是可以根据 routingKey 将消息推送到指定的 queue
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/4/21 19:11
 */
public class MyProducer {

    private static final String EXCHANGE_NAME = "exchange_direct";
    private final static String QUEUE_SMS = "queue.sms";
    private final static String QUEUE_EMAIL = "queue.email";
    private final static String KEY_SMS = "key.sms";
    private final static String KEY_EMAIL = "key.email";
    private final static String KEY_EMAIL2 = "key.email2";

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
            // direct 对应 routing 模式
            // fanout 直译是扇形，就是发布订阅模式
            // topic 是通配符模式
            // headers 使用较少，感觉跟topic 差不多，只不过放在 header 里
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

            // 声明 QUEUE_SMS 队列
            channel.queueDeclare(QUEUE_SMS, true, false, false, null);
            // 声明 QUEUE_EMAIL 队列
            channel.queueDeclare(QUEUE_EMAIL, true, false, false, null);

            // 绑定到交换机
            channel.queueBind(QUEUE_SMS, EXCHANGE_NAME, KEY_SMS);
            // 绑定到交换机
            channel.queueBind(QUEUE_EMAIL, EXCHANGE_NAME, KEY_EMAIL);
            // 绑定到交换机
            channel.queueBind(QUEUE_EMAIL, EXCHANGE_NAME, KEY_EMAIL2);

            // 发送消息
            String prefix = "send inform message";
            for (int i=0;i<10;i++){
                String message = prefix + "_" + i;
                // 发送消息的时候，一定要指定 routingKey
                if (i%3==0){
                    channel.basicPublish(EXCHANGE_NAME, KEY_SMS, null, message.getBytes());
                }else if (i%3==1){
                    channel.basicPublish(EXCHANGE_NAME, KEY_EMAIL, null, message.getBytes());
                }else{
                    channel.basicPublish(EXCHANGE_NAME, KEY_EMAIL2, null, message.getBytes());
                }

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
