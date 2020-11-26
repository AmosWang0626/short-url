package com.amos.shorturl.domain;

import com.amos.shorturl.common.api.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * DESCRIPTION: 短链接实体类
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/11/26
 */
@Getter
@Setter
@Entity
@Table(name = "dev_short_url")
@Accessors(chain = true)
public class ShortUrlEntity extends BaseEntity {

    /**
     * 短链接
     */
    private String url;
    /**
     * 完整链接
     */
    private String fullUrl;
    /**
     * 过期时间-时间戳（-1表示永久有效）
     */
    private Long expireTime;

}
