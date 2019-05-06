package com.child.programming.base.util;

import lombok.extern.log4j.Log4j2;

import javax.validation.constraints.NotBlank;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description：
 * @Author：yangfan
 **/
@Log4j2
public class DateUtil {

    private final static String defaultDayFormat = "yyyy-MM-dd";

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

    /**
     * 转化为calendar
     * @param date
     * @return
     */
    public static Calendar convertToCalendar(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 计算两个日期间，相差的天数
     * @param minDate
     * @param maxDate
     * @return
     */
    public static int computeDiffDays(Date minDate, Date maxDate){
        int days = (int)(maxDate.getTime() - minDate.getTime())/(24*3600*1000);
        days +=1;
        return days;
    }

    /**
     * 日期，加运算
     * @param date
     * @param field
     * @param number
     * @return
     */
    public static Date add(Date date, int field, int number){
        Calendar calendar = convertToCalendar(date);
        calendar.set(field, calendar.get(field) + number);
        return calendar.getTime();
    }

    /**
     * 时分秒，相加
     * @param date
     * @param timeHHmmss
     * @return
     */
    public static Date addHHmmss(Date date, @NotBlank String timeHHmmss){
        Calendar calendar = convertToCalendar(date);
        String[] timeArray = timeHHmmss.split(":");
        if (!EmptyUtils.arrayIsEmpty(timeArray) && timeArray.length == 3){
            calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + Integer.parseInt(timeArray[0]));
            calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + Integer.parseInt(timeArray[1]));
            calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) + Integer.parseInt(timeArray[2]));
        }
        return calendar.getTime();
    }
}
