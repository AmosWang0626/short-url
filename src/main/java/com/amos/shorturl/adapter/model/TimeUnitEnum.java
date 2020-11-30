package com.amos.shorturl.adapter.model;

import java.time.LocalDateTime;

/**
 * DESCRIPTION: 时间单位
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/11/26
 */
public enum TimeUnitEnum {

    /**
     * 单位：年、月、日、小时、分钟、秒
     */
    YEAR("年") {
        @Override
        public LocalDateTime setTime(LocalDateTime time, Integer expire) {
            return time.plusYears(expire);
        }
    }, MONTH("月") {
        @Override
        public LocalDateTime setTime(LocalDateTime time, Integer expire) {
            return time.plusMonths(expire);
        }
    }, DAY("日") {
        @Override
        public LocalDateTime setTime(LocalDateTime time, Integer expire) {
            return time.plusDays(expire);
        }
    }, HOUR("小时") {
        @Override
        public LocalDateTime setTime(LocalDateTime time, Integer expire) {
            return time.plusHours(expire);
        }
    }, MINUTE("分钟") {
        @Override
        public LocalDateTime setTime(LocalDateTime time, Integer expire) {
            return time.plusMinutes(expire);
        }
    }, SECOND("秒") {
        @Override
        public LocalDateTime setTime(LocalDateTime time, Integer expire) {
            return time.plusSeconds(expire);
        }
    };

    TimeUnitEnum(String name) {
        this.name = name;
    }

    public abstract LocalDateTime setTime(LocalDateTime time, Integer expire);

    private final String name;

    public String getName() {
        return name;
    }
}
