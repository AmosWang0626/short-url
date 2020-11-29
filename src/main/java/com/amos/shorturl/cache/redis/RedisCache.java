package com.amos.shorturl.cache.redis;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 * DESCRIPTION: Redis缓存
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/11/29
 */
@Configuration
public class RedisCache {

    @Primary
    @Bean("redis")
    public CacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {

        return RedisCacheManager.create(redisConnectionFactory);
    }

}
