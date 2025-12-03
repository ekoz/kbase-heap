package com.ibothub.heap.kafka.lang;

import com.alibaba.fastjson2.JSON;
import com.ibothub.heap.kafka.model.UserMessage;
import org.junit.Test;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/11/30 12:01
 */
public class JsonDeserializer {

    @Test
    public void testParseObject(){
        String jsonStr = "{\"sex\":1,\"id\":1,\"age\":32,\"username\":\"关羽\"}";

        UserMessage userMessage = JSON.parseObject(jsonStr.getBytes(), UserMessage.class);
        System.out.println(userMessage.toString());

    }
}
