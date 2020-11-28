package com.amos.shorturl.common.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * DESCRIPTION: Context
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/11/28
 */
@Configuration
public class SpringBeanUtils {

    private static ApplicationContext applicationContext;

    public SpringBeanUtils(ApplicationContext applicationContext) {
        SpringBeanUtils.applicationContext = applicationContext;
    }

    public static <T> T getBean(String name, Class<T> requiredType) {
        return applicationContext.getBean(name, requiredType);
    }

    public static <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

}
