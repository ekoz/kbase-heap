/*
 * Powered by http://ibotstat.com
 */
package com.eastrobot.heap.mq.E06_Boot集成.service;

import com.eastrobot.heap.mq.RabbitConstant;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/4/25 15:12
 */
@Service
public class MyProducer {

    @Resource
    RabbitTemplate rabbitTemplate;

    /**
     * send message
     * @param message
     */
    public void sendMessage(String message){
        rabbitTemplate.convertAndSend(RabbitConstant.EXCHANGE_BOOT, "", message);
    }



}
