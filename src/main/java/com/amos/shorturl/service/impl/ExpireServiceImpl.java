package com.amos.shorturl.service.impl;

import com.amos.shorturl.domain.ShortUrlDao;
import com.amos.shorturl.domain.ShortUrlEntity;
import com.amos.shorturl.service.ExpireService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * DESCRIPTION: 过期数据
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/12/1
 */
@Service("expireService")
public class ExpireServiceImpl implements ExpireService {

    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private ShortUrlDao shortUrlDao;

    private static final String SHORT_URL_EXPIRE = "SHORT_URL_EXPIRE";


    @Override
    public void addExpireInfo(String shortUrlId, Long expireTime) {
        redisTemplate.opsForZSet().add(SHORT_URL_EXPIRE, shortUrlId, expireTime);
    }

    @Override
    public void initExpireInfoJob() {
        List<ShortUrlEntity> deleteEntities = new ArrayList<>();
        List<ShortUrlEntity> needInitExpireInfoEntities = new ArrayList<>();

        Optional<List<ShortUrlEntity>> byExpireTime = shortUrlDao.findAllByExpireTime();
        byExpireTime.ifPresent(shortUrlEntities -> shortUrlEntities.forEach(shortUrlEntity -> {
            if (shortUrlEntity.getExpireTime() <= System.currentTimeMillis()) {
                shortUrlEntity.setDeleteFlag(true);
                deleteEntities.add(shortUrlEntity);
            } else {
                needInitExpireInfoEntities.add(shortUrlEntity);
            }
        }));

        // 删除过期数据
        shortUrlDao.saveAll(deleteEntities);

        // 过期信息保存至 Redis 
        needInitExpireInfoEntities.forEach(entity -> redisTemplate.opsForZSet().add(SHORT_URL_EXPIRE, entity.getId(), entity.getExpireTime()));
    }

}
