package com.yimei.cron.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by hongpf on 16/7/28.
 */
public class DateFormatter {
    public static String getCurrentDate(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
