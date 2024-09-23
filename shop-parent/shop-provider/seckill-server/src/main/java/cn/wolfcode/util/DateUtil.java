package cn.wolfcode.util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by lanxw
 */
public class DateUtil {
    /**
     * 根据日期和场次看是否在秒杀有效时间之内
     * @param date
     * @param time
     * @return
     */
    public static boolean isLegalTime(Date date, int time){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY,time);
        Long start = c.getTime().getTime();
        Long now = new Date().getTime();
        c.add(Calendar.HOUR_OF_DAY,2);
        Long end = c.getTime().getTime();
        return now>=start && now<=end;
    }
    /**
     * 给指定的日期中增加指定时间
     * @param date
     * @param field
     * @param num
     * @return
     */
    public static Date addTime(Date date, int time,Integer field,Integer num){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY,time);
        c.add(field,num);
        return c.getTime();
    }
    public static long calculateRemainingSeconds(Date targetDate) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime targetDateTime = targetDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        Duration duration = Duration.between(now, targetDateTime);
        if (duration.isNegative()) {
            return 0; // 如果目标日期已过，则返回10秒
        }
        long remainingSeconds = duration.getSeconds();
        return remainingSeconds;
    }
}
