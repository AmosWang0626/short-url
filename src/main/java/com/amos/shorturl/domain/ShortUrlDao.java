package com.amos.shorturl.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * DESCRIPTION: 短链接Dao层
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/11/26
 */
@Repository
public interface ShortUrlDao extends CrudRepository<ShortUrlEntity, String> {
}
