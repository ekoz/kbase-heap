/*
 * Powered by http://ibotstat.com
 */
package com.eastrobot.heap.mq;

import com.rabbitmq.client.ConnectionFactory;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/4/24 18:33
 */
public class RabbitConstant {

    public static final String HOST = "172.16.8.139";
    public static final int PORT = 5672;
    public static final String USERNAME = "admin";
    public static final String PASSWORD = "admin";
    public static final String VIRTUAL_HOST = "rabbitmq_vhost";

    public static final String EXCHANGE_BOOT = "exchange_fanout_boot";
    public static final String EXCHANGE_NAME = "exchange_fanout";
    public final static String QUEUE_SMS = "queue.sms";
    public final static String QUEUE_EMAIL = "queue.email";
    public final static String QUEUE_WX = "queue.wx";
    public final static String QUEUE_IOS = "queue.ios";



    /**
     * 通过连接工厂创建新的连接和 mq 建立连接
     * @return
     */
    public static ConnectionFactory getConnectionFactory(){
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost(HOST);
        connectionFactory.setPort(PORT);
        connectionFactory.setUsername(USERNAME);
        connectionFactory.setPassword(PASSWORD);
        // 一个 mq 服务可以设置多个虚拟机，每个虚拟机相当于一个独立的mq
        connectionFactory.setVirtualHost(VIRTUAL_HOST);
        return connectionFactory;
    }
}
