/*
 * powered by https://zhengxinacc.com
 */
package com.ibothub.heap.redis.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import jakarta.annotation.Resource;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/12/17 20:57
 */
@Slf4j
@Configuration
@EnableCaching
@ConditionalOnBean(name = "redisProperties")
@AutoConfigureAfter(value = {RedisAutoConfiguration.class})
public class RedisConfig extends CachingConfigurerSupport {

    @Resource
    RedisProperties redisProperties;

    @Bean
    @Primary
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        /// set default serializer
        // 如果用 StringRedisSerializer ，那么不支持 Number 存储
        // template.setDefaultSerializer(new StringRedisSerializer());
        // template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }

    @Bean
    public RedisCacheManagerBuilderCustomizer myRedisCacheManagerBuilderCustomizer() {
        return (builder) -> {
            RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
            if (redisProperties.getExpires()!=null){
                redisProperties.getExpires().forEach((key, value) -> {
                    builder.withCacheConfiguration(key, RedisCacheConfiguration.defaultCacheConfig().entryTtl(value));
                });
            }
            builder.cacheDefaults(redisCacheConfiguration);
        };
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuffer sb = new StringBuffer();

            /*
             * 理论上来说是没有必要加 className ，实际应用中需要规避 Service 对应的 cacheNames
             * 多个 Service 指定同一个 cacheNames 的情况下，需要用到 className
             */
            sb.append(target.getClass().getName())
                    .append(":")
                    .append(method.getName());
            if (params.length > 0) {
                sb.append(":");
                Arrays.stream(params).forEach(param -> sb.append(Objects.nonNull(param) ? param.toString() : ""));
            }

            log.debug("redis key: " + sb.toString());
            return sb.toString();
        };
    }

}
