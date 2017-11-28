package com.zy.creditindex.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by huaqin on 2017/10/30.
 */

public class DateTimeUtil {
  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    /*获取一年前的日期*/
    public  Date startTime() throws Exception {

        Calendar calendar = Calendar.getInstance();//日历对象
        calendar.setTime(new Date());//设置当前日期
        calendar.add(Calendar.YEAR, -1);//份减一
        calendar.add(Calendar.DATE, -1);//日期减一
        Date  dayTime= calendar.getTime();
        String date = format.format(dayTime);

        return format.parse(date);
    }
    //获取当前系统前一天日期
    public  Date endTime() throws Exception {

        Calendar calendar = Calendar.getInstance();//日历对象
        calendar.setTime(new Date());//设置当前日期
        calendar.add(Calendar.DATE, -1);//日期减一
        Date  dayTime= calendar.getTime();
        String date = format.format(dayTime);

        return format.parse(date);
    }
}