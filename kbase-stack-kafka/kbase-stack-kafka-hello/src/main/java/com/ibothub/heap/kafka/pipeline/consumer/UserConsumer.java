package com.ibothub.heap.kafka.pipeline.consumer;

import com.ibothub.heap.kafka.constant.KafkaConstants;
import com.ibothub.heap.kafka.model.UserMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/1/30 下午1:46
 */
@Service
@Slf4j
public class UserConsumer {

    @KafkaListener(topics = KafkaConstants.USER_TOPIC)
    public void consumer(@Payload List<UserMessage> userMessageList, Acknowledgment ack){
        try{
            log.debug("接收到 {} 条消息", userMessageList.size());
            userMessageList.forEach(System.out::println);
        } finally {
            /* commit kafka offset */
            ack.acknowledge();
        }
    }
}
