package com.amos.shorturl.cache.local;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * DESCRIPTION: 本地缓存
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/11/23
 */
public class LocalCache {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalCache.class);


    public static final Cache<String, Object> CACHE = Caffeine.newBuilder()
            .initialCapacity(256)
            // 保存缓存数量
            .maximumSize(100)
            // 从写入开始保存的时间
            .expireAfterWrite(24, TimeUnit.HOURS)
            // 删除缓存原因
            .removalListener((key, value, cause) -> LOGGER.info(">>> 删除缓存 [{}]({}), reason is [{}]", key, value, cause))
            // 开启状态监控 [ 这样下边才能使用 CACHE.stats() ]
            .recordStats()
            .build();

}
