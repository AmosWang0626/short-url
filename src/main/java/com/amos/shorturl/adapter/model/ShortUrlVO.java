package com.amos.shorturl.adapter.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * DESCRIPTION: 短链接-VO
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/11/26
 */
@Setter
@Getter
@Accessors(chain = true)
public class ShortUrlVO {

    /**
     * 短链接
     */
    private String url;
    /**
     * 长链接
     */
    private String fullUrl;
    /**
     * 时间（-1表示永久有效）
     */
    private Integer expire;
    /**
     * 时间单位
     */
    private TimeUnitEnum timeUnit;
    /**
     * 过期时间信息
     */
    private String expireInfo;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
