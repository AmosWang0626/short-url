package com.amos.shorturl.service.impl;

import com.amos.shorturl.adapter.algorithm.UniqueShortUrl;
import com.amos.shorturl.adapter.model.ShortUrlForm;
import com.amos.shorturl.adapter.model.ShortUrlVO;
import com.amos.shorturl.common.api.CommonResponse;
import com.amos.shorturl.common.util.DateUtils;
import com.amos.shorturl.domain.ShortUrlDao;
import com.amos.shorturl.domain.ShortUrlEntity;
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
@Service("shortUrlService")
public class ShortUrlServiceImpl implements ShortUrlService {

    @Value("${short.url.base_url}")
    private String baseUrl;

    @Resource
    private ShortUrlDao shortUrlDao;

    @Override
    public CommonResponse<ShortUrlVO> save(ShortUrlForm form) {
        ShortUrlEntity entity = new ShortUrlEntity();
        entity.setFullUrl(form.getFullUrl());
        entity.setExpireTime(-1L);

        if (form.getExpire() == null || form.getTimeUnit() == null) {
            form.setExpire(-1);
        }

        // 校验 full_url, 存在就直接返回
        Optional<ShortUrlEntity> byFullUrl = shortUrlDao.findByFullUrl(entity.getFullUrl());
        if (byFullUrl.isPresent()) {
            return CommonResponse.success(parseVO(byFullUrl.get()));
        }

        // 设置过期时间（时间戳）
        if (form.getExpire() != -1) {
            LocalDateTime expireDateTime = form.getTimeUnit().setTime(LocalDateTime.now(), form.getExpire());
            entity.setExpireTime(DateUtils.toTimeMillis(expireDateTime));
        }

        entity.setUrl(UniqueShortUrl.getShortUrl(entity.getFullUrl()));
        shortUrlDao.save(entity);

        return CommonResponse.success(parseVO(entity));
    }

    @Override
    @Cacheable(value = "short:url", cacheManager = "caffeine", key = "'short_url_' + #key")
    public CommonResponse<String> find(String key) {
        Optional<ShortUrlEntity> byUrl = shortUrlDao.findByUrl(key);
        if (byUrl.isPresent()) {
            return CommonResponse.success(byUrl.get().getFullUrl());
        }

        return CommonResponse.FAIL;
    }

    @Override
    public CommonResponse<List<ShortUrlVO>> findAll() {
        List<ShortUrlEntity> all = shortUrlDao.findAll();
        List<ShortUrlVO> list = all.stream()
                .map(this::parseVO)
                .collect(Collectors.toList());

        return CommonResponse.success(list);
    }

    private ShortUrlVO parseVO(ShortUrlEntity entity) {

        return new ShortUrlVO()
                .setUrl(baseUrl + entity.getUrl())
                .setFullUrl(entity.getFullUrl())
                .setExpireInfo(entity.getExpireTime() == -1 ? "永久有效" : DateUtils.toString(entity.getExpireTime()));
    }
}
