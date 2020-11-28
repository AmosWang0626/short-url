package com.amos.shorturl.adapter.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("生成短链接表单")
public class ShortUrlForm {

    /**
     * 长链接
     */
    @ApiModelProperty(value = "长链接", required = true, example = "https://amos.wang/")
    private String fullUrl;
    /**
     * 时间（-1表示永久有效）
     */
    @ApiModelProperty(value = "时间（-1表示永久有效）", required = true, example = "-1")
    private Integer expire;
    /**
     * 时间单位
     */
    @ApiModelProperty(value = "时间单位", example = "HOUR")
    private TimeUnitEnum timeUnit;

}
