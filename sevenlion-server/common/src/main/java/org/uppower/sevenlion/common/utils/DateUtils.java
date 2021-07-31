package org.uppower.sevenlion.common.utils;

import cn.hutool.core.date.DateUtil;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateUtils {

    /**
     * 得到两个时间相差的秒数
     * @param start
     * @param end
     * @return
     */
    public static long dateBetweenWithSecond(LocalDateTime start, LocalDateTime end) {
        Duration duration = Duration.between(start,end);
        return duration.toMillis()/1000;
    }

    /**
     * 得到当前时间与今日结束时间相差的秒数
     * @param start
     * @return
     */
    public static long betweenEndTimeWithSecond(LocalDateTime start) {
        return dateBetweenWithSecond(start,LocalDateTime.MAX);
    }

    /**
     * 比较两个时间是否是同一天
     * @param time
     * @param now
     * @return
     */
    public static boolean isSameDate(LocalDateTime time, LocalDateTime now) {
        return time.toLocalDate().isEqual(now.toLocalDate());
    }

    /**
     * 判断时间是否与今日日期相同
     * @param time
     * @return
     */
    public static boolean isSameToday(LocalDateTime time) {
        return isSameDate(time, LocalDateTime.now());
    }
}
