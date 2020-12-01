package com.amos.shorturl.service.impl;

import com.amos.shorturl.domain.ShortUrlDao;
import com.amos.shorturl.domain.ShortUrlEntity;
import com.amos.shorturl.service.ShortUrlService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * DESCRIPTION: 短链接服务实现
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/11/29
 */
@Service("shortUrlService")
public class ShortUrlServiceImpl implements ShortUrlService {

    @Resource
    private ShortUrlDao shortUrlDao;

    @Override
    public void save(ShortUrlEntity entity) {
        shortUrlDao.save(entity);
    }

    @Override
    @Cacheable(value = "short:url", cacheManager = "redis", key = "'short_url_' + #shortUrl")
    public Optional<String> find(String shortUrl) {
        Optional<ShortUrlEntity> byUrl = shortUrlDao.findByUrl(shortUrl);

        return byUrl.map(ShortUrlEntity::getFullUrl);
    }

    @Override
    public Optional<ShortUrlEntity> findByFullUrl(String fullUrl) {
        return shortUrlDao.findByFullUrl(fullUrl);
    }

    @Override
    public List<ShortUrlEntity> findAll() {
        return shortUrlDao.findAllValid();
    }
}
