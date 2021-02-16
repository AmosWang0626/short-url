package com.amos.shorturl.service.impl;

import com.amos.common.util.date.DateUtils;
import com.amos.shorturl.adapter.algorithm.UniqueShortUrl;
import com.amos.shorturl.adapter.model.ShortUrlForm;
import com.amos.shorturl.adapter.model.ShortUrlVO;
import com.amos.shorturl.domain.ShortUrlEntity;
import com.amos.shorturl.service.ShortUrlBusiness;
import com.amos.shorturl.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * DESCRIPTION: 短链接服务实现
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/11/26
 */
@Service("shortUrlBusiness")
public class ShortUrlBusinessImpl implements ShortUrlBusiness {

    @Value("${short.url.base_url}")
    private String baseUrl;

    @Resource
    private ShortUrlService shortUrlService;

    @Override
    public ShortUrlVO save(ShortUrlForm form) {
        ShortUrlEntity entity = new ShortUrlEntity();
        entity.setFullUrl(form.getFullUrl());
        entity.setExpireTime(-1L);

        // 校验 full_url, 存在就直接返回
        Optional<ShortUrlEntity> byFullUrl = shortUrlService.findByFullUrl(entity.getFullUrl());
        if (byFullUrl.isPresent()) {
            return parseVO(byFullUrl.get());
        }

        // 设置默认过期时间
        if (form.getExpire() == null || form.getTimeUnit() == null) {
            form.setExpire(-1);
        }

        // 设置过期时间（时间戳）
        if (form.getExpire() != -1) {
            LocalDateTime expireDateTime = form.getTimeUnit().setTime(LocalDateTime.now(), form.getExpire());
            entity.setExpireTime(DateUtils.toTimeMillis(expireDateTime));
        }

        entity.setUrl(UniqueShortUrl.getShortUrl(entity.getFullUrl()));
        shortUrlService.save(entity);

        return parseVO(entity);
    }

    @Override
    @Cacheable(value = "short:url", cacheManager = "caffeine", key = "'short_url_' + #key")
    public String find(String key) {

        return shortUrlService.find(key);
    }

    @Override
    public List<ShortUrlVO> findAll() {
        List<ShortUrlEntity> all = shortUrlService.findAll();

        return all.stream()
                .map(this::parseVO)
                .collect(Collectors.toList());
    }

    private ShortUrlVO parseVO(ShortUrlEntity entity) {

        return new ShortUrlVO()
                .setUrl(baseUrl + entity.getUrl())
                .setFullUrl(entity.getFullUrl())
                .setExpireInfo(expireInfo(entity.getExpireTime()))
                .setExpireTime(entity.getExpireTime());
    }

    private String expireInfo(Long expireTime) {
        if (expireTime == -1) {
            return "永久有效";
        }
        if (expireTime < System.currentTimeMillis()) {
            return "已过期";
        }

        return DateUtils.getDateTime(DateUtils.toLocalDateTime(expireTime));
    }
}
