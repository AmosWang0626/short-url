package com.amos.shorturl.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * DESCRIPTION: 短链接Dao层
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/11/26
 */
@Repository
public interface ShortUrlDao extends JpaRepository<ShortUrlEntity, String> {

    Optional<ShortUrlEntity> findByUrl(String shortUrl);

    Optional<ShortUrlEntity> findByFullUrl(String fullUrl);

    @Query("select e from #{#entityName} e where e.deleteFlag = 0")
    List<ShortUrlEntity> findAllValid();

    @Query("select e from #{#entityName} e where e.deleteFlag = 0 and e.expireTime <> -1")
    List<ShortUrlEntity> findHaveExpireTime();

    @Query("select e from #{#entityName} e where e.expireTime <> -1 and e.expireTime < ?1 and e.deleteFlag = 0")
    Optional<List<ShortUrlEntity>> findByExpireTime(Long expireTime);

}
