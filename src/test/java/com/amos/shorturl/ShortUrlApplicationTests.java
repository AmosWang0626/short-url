package com.amos.shorturl;

import com.amos.shorturl.domain.ShortUrlDao;
import com.amos.shorturl.domain.ShortUrlEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class ShortUrlApplicationTests {

    @Resource
    private ShortUrlDao shortUrlDao;

    @Test
    void contextLoads() {
        Optional<List<ShortUrlEntity>> byExpireTime = shortUrlDao.findByExpireTime(System.currentTimeMillis());
        byExpireTime.ifPresent(shortUrlEntities -> shortUrlEntities.forEach(entity -> {
            entity.setDeleteFlag(true);
            shortUrlDao.save(entity);
            System.out.println(entity);
        }));
    }

}
