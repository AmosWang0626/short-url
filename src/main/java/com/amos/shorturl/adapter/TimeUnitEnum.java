package com.amos.shorturl.adapter;

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
    YEAR {
        @Override
        public LocalDateTime setTime(LocalDateTime time, Integer expire) {
            return time.plusYears(expire);
        }
    }, MONTH {
        @Override
        public LocalDateTime setTime(LocalDateTime time, Integer expire) {
            return time.plusMonths(expire);
        }
    }, DAY {
        @Override
        public LocalDateTime setTime(LocalDateTime time, Integer expire) {
            return time.plusDays(expire);
        }
    }, HOURS {
        @Override
        public LocalDateTime setTime(LocalDateTime time, Integer expire) {
            return time.plusHours(expire);
        }
    }, MINUTES {
        @Override
        public LocalDateTime setTime(LocalDateTime time, Integer expire) {
            return time.plusMinutes(expire);
        }
    }, SECONDS {
        @Override
        public LocalDateTime setTime(LocalDateTime time, Integer expire) {
            return time.plusSeconds(expire);
        }
    };

    public abstract LocalDateTime setTime(LocalDateTime time, Integer expire);

}
