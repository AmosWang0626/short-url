package com.amos.shorturl.adapter.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
     * 完整链接
     */
    @NotBlank(message = "链接不能为空")
    @ApiModelProperty(value = "链接", required = true, example = "https://amos.wang/")
    private String fullUrl;
    /**
     * 时间（-1表示永久有效）
     */
    @NotNull(message = "过期时间不能为空")
    @Min(-1)
    @ApiModelProperty(value = "时间（-1表示永久有效）", required = true, example = "-1")
    private Integer expire;
    /**
     * 时间单位
     */
    @ApiModelProperty(value = "时间单位", example = "HOUR")
    private TimeUnitEnum timeUnit;

}
