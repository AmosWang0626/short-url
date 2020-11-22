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

    private static final String BASE_URL = "localhost:8080/short/url/";

    static {
        MAP.put("0", "https://amos.wang");
    }

    @PostMapping
    @ResponseBody
    public String add(String url) {
        String key = String.valueOf(MAP.size());
        MAP.put(key, url);

        return BASE_URL + key;
    }

    @GetMapping("{key}")
    public String get(@PathVariable("key") String key) {
        String url = MAP.get(key);
        if (url == null) {
            return "404";
        }

        return "redirect:" + url;
    }

}
