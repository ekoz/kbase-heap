/*
 * powered by https://zhengxinacc.com
 */
package com.ibothub.heap.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/11/6 19:45
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class RedisApplicationTests {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Test
    public void testSet(){
        ValueOperations<String, String> operation = stringRedisTemplate.opsForValue();

        operation.set("ekoz", "ekozhan");

    }

    @Test
    public void testGet(){
        System.out.println(stringRedisTemplate.opsForValue().get("ekoz"));
    }

}
