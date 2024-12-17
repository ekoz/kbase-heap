/*
 * Powered by http://ibotstat.com
 */
package com.eastrobot.heap.mq.E06_Boot集成.web;

import com.eastrobot.heap.mq.E06_Boot集成.service.MyProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

/**
 * http://localhost:8080/rabbit/send
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/4/26 15:35
 */
@RestController
@RequestMapping("/rabbit")
public class RabbitController {

    @Resource
    MyProducer myProducer;

    @PostMapping("/send")
    public void send(String message){
        myProducer.sendMessage(message);
    }

}
