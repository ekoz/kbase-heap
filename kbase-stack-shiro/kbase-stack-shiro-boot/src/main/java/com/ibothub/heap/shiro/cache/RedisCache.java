/*
 * powered by https://zhengxinacc.com
 */
package com.ibothub.heap.shiro.cache;

import com.ibothub.heap.shiro.util.SpringContextUtils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Collection;
import java.util.Set;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/11/6 21:00
 */
@NoArgsConstructor
@AllArgsConstructor
public class RedisCache<K, V> implements Cache<K, V> {

    private String cacheName;

    static RedisTemplate getRedisTemplate(){
        RedisTemplate redisTemplate = (RedisTemplate)SpringContextUtils.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    @Override
    public V get(K k) throws CacheException {
        System.out.println("get key: " +  k);
        System.out.println(getRedisTemplate().opsForValue().get(k.toString()));
        if (getRedisTemplate().opsForValue().get(k.toString())!=null){
            return (V)getRedisTemplate().opsForValue().get(k.toString());
        }
        return null;
    }

    @Override
    public V put(K k, V v) throws CacheException {
        System.out.println("put key: " +  k);
        System.out.println("put value: " + v);
        getRedisTemplate().setKeySerializer(new StringRedisSerializer());
        getRedisTemplate().opsForValue().set(k.toString(), v);
        return null;
    }

    @Override
    public V remove(K k) throws CacheException {
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }
}
