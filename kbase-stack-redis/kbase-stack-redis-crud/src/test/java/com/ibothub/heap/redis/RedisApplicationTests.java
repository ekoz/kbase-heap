/*
 * powered by https://zhengxinacc.com
 */
package com.ibothub.heap.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Resource
    RedisTemplate redisTemplate;

    @Test
    public void testSet(){
        ValueOperations<String, String> operation = stringRedisTemplate.opsForValue();
        operation.set("ekoz", "ekozhan");
    }

    @Test
    public void testGet(){
        System.out.println(stringRedisTemplate.opsForValue().get("ekoz"));
    }


//    =================================================================

    @Test
    public void testValueOperations(){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("username", "ekozhan");

        System.out.println(valueOperations.get("username"));
    }

    @Test
    public void testBoundOperations(){
        System.out.println(redisTemplate.boundValueOps("username").get());
    }
}
