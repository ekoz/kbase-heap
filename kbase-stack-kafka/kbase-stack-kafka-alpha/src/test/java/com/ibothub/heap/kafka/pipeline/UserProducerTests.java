package com.ibothub.heap.kafka.pipeline;

import com.ibothub.heap.kafka.model.UserMessage;
import com.ibothub.heap.kafka.pipeline.producer.UserProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/1/30 下午2:16
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserProducerTests {

    @Resource
    UserProducer userProducer;

    @Test
    public void testSend(){

        UserMessage userMessage = UserMessage.builder()
                .username("关羽")
                .age(32)
                .id(1)
                .sex(1)
                .build();

        userProducer.send(userMessage);
    }
}
