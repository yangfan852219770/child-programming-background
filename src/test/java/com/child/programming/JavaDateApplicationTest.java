package com.child.programming;

import com.child.programming.base.util.DateUtil;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * @Description：
 * @Author：yangfan
 **/

public class JavaDateApplicationTest {

    @Test
    public void dateDiffTest(){
       Date date1 = DateUtil.stringToDateByDefaultDayFormat("2019-5-6");
       Date date2 = DateUtil.stringToDateByDefaultDayFormat("2019-5-12");
       int days = DateUtil.computeDiffDays(date1, date2);
       System.out.println(days);
    }

    @Test
    public void dateAddTest(){
        Date date = new Date();
        date = DateUtil.add(date, Calendar.DATE, 1);
        System.out.println(date);
    }

    @Test
    public void dateAddHHmmssTest(){
        Date date = new Date();
        System.out.println(DateUtil.DateToString(date,"YYYY-MM-dd HH:mm:ss"));
        Date date1 = DateUtil.addHHmmss(date,"01:01:10");
        Date date2 = DateUtil.addHHmmss(date,"01:01:10");
        System.out.println(DateUtil.DateToString(date1,"YYYY-MM-dd HH:mm:ss"));
        System.out.println(DateUtil.DateToString(date2,"YYYY-MM-dd HH:mm:ss"));
    }
}
