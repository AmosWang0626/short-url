package com.amos.shorturl.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query("select e from #{#entityName} e where e.url = ?1 and e.deleteFlag = 0")
    Optional<ShortUrlEntity> findByUrl(String shortUrl);

    @Query("select e from #{#entityName} e where e.fullUrl = ?1 and e.deleteFlag = 0")
    Optional<ShortUrlEntity> findByFullUrl(String fullUrl);

    @Query("select e from #{#entityName} e where e.deleteFlag = 0")
    List<ShortUrlEntity> findValidAll();

    @Query("select e from #{#entityName} e where e.expireTime <> -1 and e.deleteFlag = 0")
    Optional<List<ShortUrlEntity>> findAllByExpireTime();

    @Modifying
    @Query("update #{#entityName} e set e.deleteFlag = 1 where e.id in ?1 and e.deleteFlag = 0")
    void batchDeleteByIds(List<String> ids);

}
