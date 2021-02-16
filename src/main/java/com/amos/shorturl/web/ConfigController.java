package com.amos.shorturl.web;

import com.amos.common.dto.response.MultiResponse;
import com.amos.shorturl.adapter.model.SimpleEntry;
import com.amos.shorturl.adapter.model.TimeUnitEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * DESCRIPTION: 配置相关
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/11/30
 */
@RestController
@RequestMapping("config")
public class ConfigController {

    @GetMapping("timeUnit")
    public MultiResponse<SimpleEntry<String, String>> timeUnit() {
        List<SimpleEntry<String, String>> list = new ArrayList<>();
        Arrays.stream(TimeUnitEnum.values()).forEach(timeUnitEnum -> {
            list.add(new SimpleEntry<>(timeUnitEnum.name(), timeUnitEnum.getName()));
        });

        return MultiResponse.ofSuccess(list);
    }

}