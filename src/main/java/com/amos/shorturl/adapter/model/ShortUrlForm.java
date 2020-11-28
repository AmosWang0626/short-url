package com.amos.shorturl.adapter.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * DESCRIPTION: 短链接-Form
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/11/26
 */
@Setter
@Getter
@Accessors(chain = true)
public class ShortUrlForm {

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

}
