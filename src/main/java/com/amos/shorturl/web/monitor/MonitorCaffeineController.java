package com.amos.shorturl.web.monitor;

import com.amos.shorturl.common.api.CommonResponse;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * DESCRIPTION: 监控
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/11/28
 */
@Api(tags = "监控-Caffeine")
@RestController
@RequestMapping("monitor/caffeine")
public class MonitorCaffeineController {

    @Resource
    private CacheManager caffeine;


    @GetMapping
    public String index() {

        return "Hello Caffeine Monitor!";
    }

    @GetMapping("cacheNames")
    @ApiOperation("所有缓存key")
    public Collection<String> cacheNames() {

        return caffeine.getCacheNames();
    }

    @GetMapping("stats")
    @ApiOperation("根据缓存key查询缓存监控信息")
    public CommonResponse<Map<String, Object>> caffeine(@RequestParam String cacheName) {
        CaffeineCache caffeineCache = (CaffeineCache) caffeine.getCache(cacheName);
        if (caffeineCache == null) {
            return CommonResponse.fail(String.format("缓存 [%s] 不存在 !!!", cacheName));
        }

        CacheStats stats = caffeineCache.getNativeCache().stats();

        Map<String, Object> map = new HashMap<>(16);
        map.put("请求次数", stats.requestCount());
        map.put("命中次数", stats.hitCount());
        map.put("未命中次数", stats.missCount());
        map.put("加载成功次数", stats.loadSuccessCount());
        map.put("加载失败次数", stats.loadFailureCount());
        map.put("加载失败占比", stats.loadFailureRate());
        map.put("加载总耗时", stats.totalLoadTime());
        map.put("回收总次数", stats.evictionCount());
        map.put("回收总权重", stats.evictionWeight());

        return CommonResponse.success(map);
    }

}
