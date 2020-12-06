package com.amos.shorturl;

import com.amos.shorturl.common.util.DateUtils;
import com.amos.shorturl.domain.ShortUrlDao;
import com.amos.shorturl.service.ExpireService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@SpringBootTest
class ShortUrlApplicationTests {

    @Resource
    private ExpireService expireService;
    @Resource
    private ShortUrlDao shortUrlDao;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 生成过期时间
     */
    @Test
    void generateExpireTime() {
        LocalDateTime localDateTime = LocalDateTime.now().plus(2, ChronoUnit.MINUTES);
        System.out.println(DateUtils.toTimeMillis(localDateTime));
        for (int i = 0; i < 17; i++) {
            System.out.println(DateUtils.toTimeMillis(localDateTime.plus(new Random().nextInt(100), ChronoUnit.SECONDS)));
        }
    }

    @Test
    void redisExpire() {
        expireService.initExpireInfoJob();
    }

    @Test
    @Transactional
    @Rollback(false)
    void zSetQuery() {
        String SHORT_URL_EXPIRE = "SHORT_URL_EXPIRE";
        Set<ZSetOperations.TypedTuple<String>> range = redisTemplate.opsForZSet()
                .rangeByScoreWithScores(SHORT_URL_EXPIRE, 0, System.currentTimeMillis(), 0, 10);

        List<String> ids = new ArrayList<>();
        Objects.requireNonNull(range).forEach(objectTypedTuple -> ids.add(objectTypedTuple.getValue()));

        System.out.println(range);
        if (ids.isEmpty()) {
            return;
        }

        shortUrlDao.batchDeleteByIds(ids);

        redisTemplate.opsForZSet().remove(SHORT_URL_EXPIRE, ids.toArray());
    }

    @Test
    void zSetRemove() {
        String SHORT_URL_EXPIRE = "SHORT_URL_EXPIRE";

        List<String> ids = new ArrayList<>();
        ids.add("982664a9-a521-4c1f-b917-29d36d7ce7db");
        ids.add("c55c84e2-700d-4910-8e36-59ef2000ceb1");

        redisTemplate.opsForZSet().remove(SHORT_URL_EXPIRE, ids.toArray());
    }

}
