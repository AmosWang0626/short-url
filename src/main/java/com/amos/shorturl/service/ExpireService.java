package com.amos.shorturl.service;

/**
 * DESCRIPTION: 过期逻辑
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/12/1
 */
public interface ExpireService {

    /**
     * 添加过期时间信息
     *
     * @param shortUrlId 短链接ID
     * @param expireTime 过期时间
     */
    void addExpireInfo(String shortUrlId, Long expireTime);

    /**
     * 初始化过期数据
     */
    void initExpireInfoJob();

}
