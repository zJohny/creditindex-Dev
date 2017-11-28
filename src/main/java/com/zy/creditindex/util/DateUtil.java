package com.zy.creditindex.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ${ZhaoYing}on 2017/10/26 0026
 * 设置默认查询的时间
 */
public class DateUtil {
    //获取当天对应的上个月的日期
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    public static Date starttime()throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();//日历对象
        calendar.setTime(new java.util.Date());//设置当前日期
        calendar.add(Calendar.MONTH, -1);//月份减一
        Date starttime = calendar.getTime();
        String date = format.format(starttime);
        return format.parse(date);
    }
    //获取当天系统日期的前一天日期（即昨天的交易，【业务要求】当天的交易还未开始）
    public static Date endtime()throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();//日历对象
        calendar.setTime(new Date());//设置当前日期
        calendar.add(Calendar.DATE, -1);//当天减一 ：
            Date endtime =calendar.getTime();
            String date = format.format(endtime);
            return format.parse(date);
    }
    //获取前天的日期
    public static Date theDayBeforeYesterday()throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();//日历对象
        calendar.setTime(new Date());//设置当前日期
            calendar.add(Calendar.DATE, -2);//当天减二 ：前天//-2
            Date endtime =calendar.getTime();
            String date = format.format(endtime);
            return format.parse(date);
    }
    //获取最近交易日的前一天
    public static Date thedayBeforeTheLatestTradingDay(Date trd_day)throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();//日历对象
        calendar.setTime(trd_day);//设置最近交易日
        calendar.add(Calendar.DATE, -1);//设置最近交易日的前一天
        Date endtime =calendar.getTime();
        String date = format.format(endtime);
        return format.parse(date);
    }
    //获取当天日期对应的上一周的日期
    public static Date  lastWeek()throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();//日历对象
        calendar.setTime(new Date());//设置当前日期
        calendar.add(Calendar.DATE, -7);//当天减二 ：前天
        Date endtime =calendar.getTime();
        String date = format.format(endtime);
        return format.parse(date);
    }

    // 一年前的数据
    public static Date oneYer()throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();//日历对象
        calendar.setTime(new Date());//设置当前日期
        calendar.add(Calendar.YEAR, -1);//YEAR-1
        Date starttimeyer = calendar.getTime();
        String date = format.format(starttimeyer);
        return format.parse(date);
    }

    /**
     * 获取今天是星期几
     * @return
     */
    public static int getWeekend(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(System.currentTimeMillis()));
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek;
    }

    /**
     * 获取今天的固定日期
     * @return
     * @throws Exception
     */
    public static Date taday()throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();//日历对象
        calendar.setTime(new Date());//设置当前日期
        Date endtime =calendar.getTime();
        String date = format.format(endtime);
        return  format.parse(date) ;
    }

    /**
     * 为解决周一查询时重复数据得零的问题
     * @return
     * @throws ParseException
     */
    public static Date threeDaysAgo() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();//日历对象
        calendar.setTime(new Date());//设置当前日期
            calendar.add(Calendar.DATE, -4);//当天减三：大前天
            Date endtime =calendar.getTime();
            String date = format.format(endtime);
            return format.parse(date);
    }
}
