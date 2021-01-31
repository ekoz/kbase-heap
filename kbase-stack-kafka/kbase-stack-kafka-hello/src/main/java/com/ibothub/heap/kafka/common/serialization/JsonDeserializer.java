package com.ibothub.heap.kafka.common.serialization;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.common.serialization.Deserializer;

import java.lang.reflect.ParameterizedType;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/1/30 下午4:07
 */
public class JsonDeserializer<T> implements Deserializer<T> {
    @Override
    public T deserialize(String topic, byte[] data) {
        Class<T> clazz = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return JSON.parseObject(data, clazz);
    }
}
