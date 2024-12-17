/*
 * powered by http://ibothub.com
 */
package com.ibothub.heap.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import jakarta.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:eko.zhan@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/1/6 21:33
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class RedisStringTests {

    @Resource
    RedisTemplate redisTemplate;

    @Test
    public void testStringBoundOperations(){
        BoundValueOperations username = redisTemplate.boundValueOps("username");

        username.set("司马光", 10, TimeUnit.SECONDS);
        username.expire(20, TimeUnit.SECONDS);
    }

    @Test
    public void testSetNxOperations(){
        BoundValueOperations username = redisTemplate.boundValueOps("username");
        System.out.println(username.setIfAbsent("宋江"));
        System.out.println(username.setIfAbsent("曹操", 10, TimeUnit.SECONDS));
    }

    @Test
    public void testDeleteOperations(){
        redisTemplate.delete("username");
        redisTemplate.delete("stars");
    }

    @Test
    public void testIncrement(){
        BoundValueOperations stars = redisTemplate.boundValueOps("stars");
        stars.increment();
        stars.increment(2);
    }

    @Test
    public void testStock(){
        BoundValueOperations stock = redisTemplate.boundValueOps("stock");
        stock.set(100, 1, TimeUnit.HOURS);
    }

    @Test
    public void testStockDecrement(){
        BoundValueOperations stock = redisTemplate.boundValueOps("stock");
        if (stock.decrement()<0) {
            System.out.println("库存不足");
        }
        System.out.println("秒杀成功");
    }
}
