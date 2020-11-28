package com.amos.shorturl.adapter.algorithm;

import com.amos.shorturl.common.util.SpringBeanUtils;
import com.amos.shorturl.domain.ShortUrlDao;
import com.amos.shorturl.domain.ShortUrlEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.UUID;

/**
 * DESCRIPTION: 短链接唯一算法
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/11/28
 */
public class UniqueShortUrl {

    private static final Logger LOGGER = LoggerFactory.getLogger(UniqueShortUrl.class);


    /**
     * 获取短链接-653
     *
     * @param fullUrl 完整链接
     * @return 短链接
     */
    public static String getShortUrl(String fullUrl) {
        String[] strings = ShortUrlAlgorithm.shortText(fullUrl);

        int index = 0, len = strings.length;

        ShortUrlDao shortUrlDao = SpringBeanUtils.getBean(ShortUrlDao.class);
        while (index < len) {
            Optional<ShortUrlEntity> byFullUrl = shortUrlDao.findByUrl(strings[index]);
            if (byFullUrl.isPresent()) {
                // 小概率事件
                LOGGER.info("小概率事件 (URL > SHORT_URL) 重复 :: 重复URL全路径 [{}], 当前URL全路径 [{}]", byFullUrl.get().getFullUrl(), fullUrl);
                index++;
            } else {
                return strings[index];
            }
        }

        // 极小概率事件--真优秀
        LOGGER.error("极小概率事件 (URL > SHORT_URL) 重复 :: 当前URL全路径 [{}]", fullUrl);

        // 那就根据 UUID 生成短链接吧；在这死循环的话，这个项目就可以上天了
        return getShortUrl(UUID.randomUUID().toString());
    }

}
