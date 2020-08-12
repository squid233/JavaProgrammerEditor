package io.github.squid233.javaprogrammereditor.util;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static java.time.DayOfWeek.*;

/**
 * @author squid233
 */
public class DateUtil {

    private static final DayOfWeek DAY_OF_WEEK = LocalDate.now().getDayOfWeek();

    public static String getDayOfWeek() {
        return DAY_OF_WEEK == MONDAY
                ? "星期一"
                : DAY_OF_WEEK == TUESDAY
                ? "星期二"
                : DAY_OF_WEEK == WEDNESDAY
                ? "星期三"
                : DAY_OF_WEEK == THURSDAY
                ? "星期四"
                : DAY_OF_WEEK == FRIDAY
                ? "星期五"
                : DAY_OF_WEEK == SATURDAY
                ? "星期六"
                : "星期日";
    }
}
