package com.hl.travel.model.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * redis数据库工具类
 */
@Repository
@RequiredArgsConstructor
public class RedisDao<K,V> {

    /**
     * 过期时间是3600秒，既是1个小时
     */
    private static final long EXPIRATION = 3600L;


    private final RedisTemplate<K, V> redisTemplate;



    /**
     * 判断key是否存在
     * @param key 键
     * @return
     */
    public boolean hasKey(K key){
        return redisTemplate.hasKey(key);
    }



    /**
     * 设置key值
     * @param key
     * @param value
     */
    public void setKey(K key,V value){
        ValueOperations<K, V> ops = redisTemplate.opsForValue();
        ops.set(key,value);
    }

    /**
     * 设置key值
     * @param key
     * @param value
     * @param expiration 过期时间（秒）
     */
    public void setKey(K key,V value,Long expiration){
        if(expiration == null){
            expiration = EXPIRATION;
        }
        ValueOperations<K, V> ops = redisTemplate.opsForValue();
        ops.set(key,value,expiration,TimeUnit.SECONDS);
    }

    /**
     * 获得Key值
     * @param key
     * @return
     */
    public V getValue(K key){
        ValueOperations<K, V> ops = this.redisTemplate.opsForValue();
        return ops.get(key);
    }

    public Map<Object, Object> getHash(K key) {

        Map<Object, Object> sessionData = this.redisTemplate.opsForHash().entries(key);
        return sessionData;

    }

    /**
     * 指定缓存失效时间
     * @param key 键
     * @param time 时间(秒)
     * @return
     */
    public void expire(K key,Long time){
        if(time == null){
            time = EXPIRATION;
        }
        redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    /**
     * 根据key 获取过期时间
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(K key){
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }

    /**
     * 根据key 和时间单位获取过期时间，单位{@link TimeUnit}
     * @param key 键 不能为null
     * @param timeUnit 时间单位 {@link TimeUnit}
     * @return 返回0代表为永久有效
     */
    public long getExpire(K key,TimeUnit timeUnit){
        return redisTemplate.getExpire(key,timeUnit);
    }

    /**
     * 删除缓存
     * @param key 可以传一个值 或多个
     */
    public void del(K ... key){
        if(key!=null&&key.length>0){
            if(key.length==1){
                redisTemplate.delete(key[0]);
            }else{
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }
}