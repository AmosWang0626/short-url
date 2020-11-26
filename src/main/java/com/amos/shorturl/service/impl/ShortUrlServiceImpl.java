package com.amos.shorturl.service.impl;

import com.amos.shorturl.adapter.ShortUrlAlgorithm;
import com.amos.shorturl.adapter.ShortUrlForm;
import com.amos.shorturl.adapter.ShortUrlVO;
import com.amos.shorturl.common.api.CommonResponse;
import com.amos.shorturl.domain.ShortUrlDao;
import com.amos.shorturl.domain.ShortUrlEntity;
import com.amos.shorturl.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * DESCRIPTION: 短链接服务实现
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/11/26
 */
@Service
public class ShortUrlServiceImpl implements ShortUrlService {

    @Value("${short.url.base_url}")
    private String baseUrl;

    @Resource
    private ShortUrlDao shortUrlDao;

    @Override
    public CommonResponse<ShortUrlVO> save(ShortUrlForm form) {
        ShortUrlEntity entity = new ShortUrlEntity();
        entity.setFullUrl(form.getFullUrl());

        if (form.getExpire() == null || form.getTimeUnit() == null) {
            form.setExpire(-1);
        }

        if (form.getExpire() != -1) {
            long expireTime = form.getTimeUnit()
                    .setTime(LocalDateTime.now(), form.getExpire())
                    .toInstant(ZoneOffset.of("+8"))
                    .toEpochMilli();

            entity.setExpireTime(expireTime);
        }

        shortUrlDao.save(entity);

        entity.setUrl(ShortUrlAlgorithm.random(entity.getFullUrl()));

        ShortUrlVO shortUrlVO = new ShortUrlVO();
        shortUrlVO.setUrl(baseUrl + entity.getUrl());
        return CommonResponse.success(shortUrlVO);
    }

    @Override
    @Cacheable(value = "short:url", cacheManager = "caffeine", key = "'short_url_' + #key")
    public CommonResponse<ShortUrlVO> find(String key) {

        return null;
    }
}
