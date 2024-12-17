/*
 * powered by https://zhengxinacc.com
 */
package com.ibothub.heap.redis.web.controller;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/11/13 20:31
 */
@RestController
@RequestMapping("user")
@CacheConfig(cacheNames = "user")
public class UserController {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("add")
    public void add(String key, String value){
        ValueOperations<String, String> opts = stringRedisTemplate.opsForValue();
        opts.set(key, value);
    }

    @GetMapping("get")
    public String get(String key){
        ValueOperations<String, String> opts = stringRedisTemplate.opsForValue();
        return opts.get(key);
    }

    @GetMapping("getCache")
    @Cacheable
    public String getCache(String key){
        return key;
    }

}
