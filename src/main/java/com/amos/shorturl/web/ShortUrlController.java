package com.amos.shorturl.web;

import com.amos.shorturl.adapter.model.ShortUrlForm;
import com.amos.shorturl.adapter.model.ShortUrlVO;
import com.amos.shorturl.common.api.CommonResponse;
import com.amos.shorturl.service.ShortUrlBusiness;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.util.UriEncoder;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

/**
 * DESCRIPTION: 短链接 controller
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/11/22
 */
@Api(tags = "短链接")
@Controller
public class ShortUrlController {

    @Resource
    private ShortUrlBusiness shortUrlBusiness;


    /**
     * 短链接添加页面
     */
    @ApiIgnore
    @GetMapping
    public String index() {
        return "index";
    }


    /**
     * 新增短链接
     */
    @PostMapping
    @ResponseBody
    @ApiOperation("生成短链接")
    public CommonResponse<ShortUrlVO> add(@RequestBody ShortUrlForm form) {
        if (StringUtils.isBlank(form.getFullUrl())) {
            return CommonResponse.fail();
        }

        // 解码-避免前端传来数据已转码
        String fullUrl = UriEncoder.decode(form.getFullUrl());
        form.setFullUrl(fullUrl);

        return shortUrlBusiness.save(form);
    }


    /**
     * 短链接访问
     */
    @GetMapping("{key}")
    @ApiOperation("访问短链接")
    public String get(@PathVariable("key") String key) {
        CommonResponse<String> response = shortUrlBusiness.find(key);

        if (!response.isSuccess()) {
            return "404";
        }

        // URL转码
        return "redirect:" + UriEncoder.encode(response.getData());
    }


    /**
     * 查看所有短链接
     */
    @GetMapping("all")
    @ResponseBody
    @ApiOperation("获取所有短链接")
    public CommonResponse<List<ShortUrlVO>> all() {

        return shortUrlBusiness.findAll();
    }

}
