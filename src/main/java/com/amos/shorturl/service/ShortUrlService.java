package com.amos.shorturl.service;

import com.amos.shorturl.adapter.model.ShortUrlForm;
import com.amos.shorturl.adapter.model.ShortUrlVO;
import com.amos.shorturl.common.api.CommonResponse;

import java.util.List;

/**
 * DESCRIPTION: 短链接
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/11/26
 */
public interface ShortUrlService {

    /**
     * 保存短链接
     *
     * @param form 短链接表单
     * @return 短链接信息
     */
    CommonResponse<ShortUrlVO> save(ShortUrlForm form);

    /**
     * 查询短链接
     *
     * @param key 短链接key
     * @return 短链接信息
     */
    CommonResponse<String> find(String key);

    /**
     * 查看所有短链接
     *
     * @return List<短链接信息>
     */
    CommonResponse<List<ShortUrlVO>> findAll();

}
