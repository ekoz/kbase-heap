/*
 * Powered by http://ibotstat.com
 */
package com.eastrobot.heap.mq.E06_Boot集成.service;

import com.eastrobot.heap.mq.RabbitConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/4/25 15:13
 */
@Service
@Slf4j
public class MyConsumer {

    @RabbitListener(queues = RabbitConstant.QUEUE_SMS)
    public void consumeSms(@Payload String message){

        log.info("send " + message + " to sms inform");
    }

    @RabbitListener(queues = RabbitConstant.QUEUE_EMAIL)
    public void consumeEmail(@Payload String message){

        log.info("send " + message + " to email inform");
    }

}
