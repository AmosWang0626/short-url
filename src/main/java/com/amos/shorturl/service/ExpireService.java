package com.amos.shorturl.service;

/**
 * DESCRIPTION: 过期逻辑
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/12/1
 */
public interface ExpireService {

    /**
     * 初始化过期数据
     */
    void initExpireJob();

}
