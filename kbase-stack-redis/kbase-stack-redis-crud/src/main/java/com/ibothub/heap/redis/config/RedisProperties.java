/*
 * powered by https://zhengxinacc.com
 */
package com.ibothub.heap.redis.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.Map;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/12/17 21:00
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.cache.redis")
public class RedisProperties {

    /**
     * expires 设置单个 key 过期时间(单位秒)
     * 配置样例：
     * spring.cache.redis.expires.user=600s
     * spring.cache.redis.expires.role=300s
     */
    private Map<String, Duration> expires;
}
