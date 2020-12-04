package com.amos.shorturl.adapter.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("获取短链接详细信息")
public class ShortUrlVO {

    /**
     * 短链接
     */
    @ApiModelProperty(value = "短链接")
    private String url;
    /**
     * 完整链接
     */
    @ApiModelProperty(value = "完整链接")
    private String fullUrl;
    /**
     * 过期时间信息
     */
    @ApiModelProperty(value = "短链接过期时间")
    private String expireInfo;
    /**
     * 过期时间
     */
    @ApiModelProperty(value = "过期时间-时间戳")
    private Long expireTime;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
