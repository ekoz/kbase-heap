/*
 * powered by https://zhengxinacc.com
 */
package com.ibothub.heap.shiro.cache;

import com.ibothub.heap.shiro.util.SpringContextUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    @Getter
    @Setter
    private String cacheName;

    private static RedisTemplate getRedisTemplate(){
        RedisTemplate redisTemplate = (RedisTemplate)SpringContextUtils.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    @Override
    public V get(K k) throws CacheException {
        System.out.println("get key: " +  k);
//        System.out.println(getRedisTemplate().opsForValue().get(k.toString()));
//        if (getRedisTemplate().opsForValue().get(k.toString())!=null){
//            return (V)getRedisTemplate().opsForValue().get(k.toString());
//        }
        if (getRedisTemplate().opsForHash().get(getCacheName(), k.toString())!=null){
            return (V)getRedisTemplate().opsForHash().get(getCacheName(), k.toString());
        }
        return null;
    }

    @Override
    public V put(K k, V v) throws CacheException {
        System.out.println("put key: " +  k);
        System.out.println("put value: " + v);
//        getRedisTemplate().opsForValue().set(k.toString(), v);
        getRedisTemplate().opsForHash().put(getCacheName(), k.toString(), v);
        return null;
    }

    @Override
    public V remove(K k) throws CacheException {

        return (V) getRedisTemplate().opsForHash().delete(getCacheName(), k.toString());
    }

    @Override
    public void clear() throws CacheException {
        getRedisTemplate().delete(getCacheName());
    }

    @Override
    public int size() {
        return getRedisTemplate().opsForHash().size(getCacheName()).intValue();
    }

    @Override
    public Set<K> keys() {
        return getRedisTemplate().opsForHash().keys(getCacheName());
    }

    @Override
    public Collection<V> values() {
        return getRedisTemplate().opsForHash().values(getCacheName());
    }
}
