package com.child.programming.base.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description：
 * @Author：yangfan
 **/
public class DateUtil {
    /**
     * 将日期转换为指定格式的字符串 yyyy-MM-dd ||yyyy-MM-dd HH:mm:ss
     * @param date
     * @param dateString
     * @return
     */
    public static String DateToString(Date date, String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateString);
        return formatter.format(date);
    }

    /**
     * 将字符串转换为指定格式的日期
     * @param dateString
     * @param Dateformat
     * @return
     * @throws Exception
     */
    public static Date StringToDate(String dateString,String Dateformat) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat(Dateformat);
        return formatter.parse(dateString);
    }


    /**
     *比较日期大小
     */
    public static int compareDate(Date date1, Date date2) {
        if (date1.before(date2)) {
//            date1 + "在" + date2 + "前面"
            return 1;
        } else if (date1.after(date2)) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     *比较日期大小
     */
    public static int compareDateByGetTime(Date date1, Date date2) {
        if (date1.getTime() < date2.getTime()) {
//            date1 + "在" + date2 + "前面"
            return 1;
        } else if (date1.getTime() > date2.getTime()) {
            return -1;
        } else {
            return 0;
        }
    }

}
