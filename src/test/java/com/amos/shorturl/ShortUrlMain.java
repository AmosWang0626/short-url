package com.amos.shorturl;

import com.amos.shorturl.common.util.DateUtils;
import org.yaml.snakeyaml.util.UriEncoder;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * DESCRIPTION: 短链接测试类
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/11/23
 */
public class ShortUrlMain {

    public static void main(String[] args) {
        String uri = "https://amos.wang/2020/10/29/notes/front/demo/JS监听数组push方法";

        System.out.println(UriEncoder.encode(uri));

        System.out.println(Math.pow(62, 6));

        // java 8 方式生成的时间戳 与 默认时间戳比较
        long expireTime2 = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println("LocalDateTime >>>>> " + expireTime2);
        System.out.println("System.currentTimeMillis() >>>>> " + System.currentTimeMillis());

        System.out.println(DateUtils.toString(expireTime2));
        System.out.println(DateUtils.toTimeMillis(LocalDateTime.now()));

    }

}
