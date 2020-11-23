package com.amos.shorturl;

import org.yaml.snakeyaml.util.UriEncoder;

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
    }

}
