package com.amos.shorturl.service.impl;

import com.amos.shorturl.domain.ShortUrlDao;
import com.amos.shorturl.domain.ShortUrlEntity;
import com.amos.shorturl.service.ExpireService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * DESCRIPTION: 过期数据
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/12/1
 */
@Service("expireService")
public class ExpireServiceImpl implements ExpireService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private ShortUrlDao shortUrlDao;

    private static final String SHORT_URL_EXPIRE = "SHORT_URL_EXPIRE";

    @Override
    public void initExpireJob() {
        Optional<List<ShortUrlEntity>> byExpireTime = shortUrlDao.findByExpireTime(System.currentTimeMillis());
        byExpireTime.ifPresent(shortUrlEntities ->
                shortUrlDao.saveAll(shortUrlEntities.stream()
                        .peek(entity -> entity.setDeleteFlag(true))
                        .collect(Collectors.toList())
                ));

        List<ShortUrlEntity> all = shortUrlDao.findHaveExpireTime();
        all.forEach(entity -> redisTemplate.opsForZSet().add(SHORT_URL_EXPIRE, entity.getId(), entity.getExpireTime()));
    }


    /**
     * 初始化 redis 过期数据
     */
    @PostConstruct
    public void initProject() {
        initExpireJob();
    }

}
