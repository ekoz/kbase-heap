package com.ibothub.heap.kafka.pipeline.producer;

import com.ibothub.heap.kafka.constant.KafkaConstants;
import com.ibothub.heap.kafka.model.UserMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/1/30 下午1:46
 */
@Service
public class UserProducer {

    @Resource
    KafkaTemplate<String, UserMessage> kafkaTemplate;

    public void send(UserMessage userMessage){
        kafkaTemplate.send(KafkaConstants.USER_TOPIC, userMessage);
    }

}
