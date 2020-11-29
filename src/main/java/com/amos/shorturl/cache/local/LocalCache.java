package com.amos.shorturl.cache.local;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * DESCRIPTION: 本地缓存
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/11/23
 */
@EnableCaching
@Configuration
public class LocalCache {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalCache.class);

    @Bean("caffeine")
    public CacheManager caffeineCacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .initialCapacity(256)
                .expireAfterWrite(24, TimeUnit.HOURS)
                .maximumSize(100)
                // 删除缓存原因
                .removalListener((key, value, cause) -> LOGGER.info(">>> 删除缓存 [{}]({}), reason is [{}]", key, value, cause))
                // 开启状态监控
                .recordStats()
        );

        return cacheManager;
    }

}
