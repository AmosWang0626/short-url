package com.amos.shorturl.service;

import com.amos.shorturl.domain.ShortUrlEntity;

import java.util.List;
import java.util.Optional;

/**
 * DESCRIPTION: 短链接
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/11/29
 */
public interface ShortUrlService {

    /**
     * 保存
     *
     * @param entity 短链接
     */
    void save(ShortUrlEntity entity);

    /**
     * 查询
     *
     * @param shortUrl 短链接
     * @return 短链接
     */
    Optional<String> find(String shortUrl);

    /**
     * 根据短链接查询
     *
     * @param fullUrl 完整链接
     * @return ShortUrlEntity
     */
    Optional<ShortUrlEntity> findByFullUrl(String fullUrl);

    /**
     * 查询全部
     *
     * @return List<ShortUrlEntity>
     */
    List<ShortUrlEntity> findAll();

}
