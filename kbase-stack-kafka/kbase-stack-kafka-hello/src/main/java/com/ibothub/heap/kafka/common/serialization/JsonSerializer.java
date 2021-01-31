package com.ibothub.heap.kafka.common.serialization;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.common.serialization.Serializer;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/1/30 下午3:55
 */
public class JsonSerializer<T> implements Serializer<T> {
    @Override
    public byte[] serialize(String topic, T data) {
        return JSON.toJSONBytes(data);
    }
}
