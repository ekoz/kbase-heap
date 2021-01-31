package com.ibothub.heap.kafka.pipeline.consumer;

import com.ibothub.heap.kafka.constant.KafkaConstants;
import com.ibothub.heap.kafka.model.UserMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/1/30 下午1:46
 */
@Service
public class UserConsumer {

    @KafkaListener(topics = KafkaConstants.USER_TOPIC)
    public void consumer(UserMessage userMessage){
        System.out.println(userMessage.toString());
    }
}
