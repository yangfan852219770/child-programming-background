package com.child.programming.base.util;

import lombok.extern.log4j.Log4j2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description：
 * @Author：yangfan
 **/
@Log4j2
public class DateUtil {

    private final static String defaultDayFormat = "YYYY-MM-DD";

    /**
     * 将日期转换为YYYY-MM-DD格式字符串
     * @param date
     * @return
     */
    public static String dateToStringByDefaultDayFormat(Date date){
        return DateToString(date,defaultDayFormat);
    }

    /**
     * 将字符串转为YYYY-MM-DD格式日期
     * @param dateString
     * @return
     */
    public static Date stringToDateByDefaultDayFormat(String dateString){
        return StringToDate(dateString, defaultDayFormat);
    }
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
    public static Date StringToDate(String dateString,String Dateformat) {
        SimpleDateFormat formatter = new SimpleDateFormat(Dateformat);
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            log.error("日期转换错误");
            return null;
        }
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
