package com.amos.shorturl.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * DESCRIPTION: 短链接 controller
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/11/22
 */
@Controller
@RequestMapping("short/url")
public class ShortUrlController {

    private static final Map<String, String> MAP = new ConcurrentHashMap<>();

    private static final String BASE_URL = "http://localhost:8080/short/url/";

    static {
        MAP.put("0", "https://amos.wang");
    }


    /**
     * 短链接添加页面
     */
    @GetMapping
    public String index() {
        return "index";
    }


    /**
     * 新增短链接
     */
    @PostMapping
    @ResponseBody
    public String add(String url) {
        for (Map.Entry<String, String> entry : MAP.entrySet()) {
            if (entry.getValue().equals(url)) {
                return BASE_URL + entry.getKey();
            }
        }

        String key = String.valueOf(MAP.size());
        MAP.put(key, url);

        return BASE_URL + key;
    }


    /**
     * 短链接访问
     */
    @GetMapping("{key}")
    public String get(@PathVariable("key") String key) {
        String url = MAP.get(key);
        if (url == null) {
            return "404";
        }

        // FIXME 此处需要兼容 URL 中含有中文的情况
        return "redirect:" + url;
    }


    /**
     * 查看所有短链接
     */
    @GetMapping("all")
    @ResponseBody
    public Map<String, String> all() {

        return MAP;
    }

}
