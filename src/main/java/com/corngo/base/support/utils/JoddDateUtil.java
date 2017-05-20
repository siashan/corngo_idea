package com.corngo.base.support.utils;

import jodd.datetime.JDateTime;
import jodd.datetime.Period;

import java.text.ParseException;
import java.util.Date;

/**
 * 1、string转换成date
 * 2、date转换成string
 * 3、日期增减
 * 4、判断是否工作日
 * 5、计算两个日期间隔天数
 * 6、比较两个日期大小
 * <p/>
 * Created by kfc on 2016/10/12.
 */
public class JoddDateUtil {
    public static final String DEFAULT_FORMAT = "YYYY-MM-DD hh:mm:ss.mss";

    /**
     * 将String转换成Date
     *
     * @param str    时间串  2016-01-02   2016-02-02 12:09:09
     * @param format 格式化串 YYYY-MM-DD hh:mm:ss.mss
     * @return
     * @throws ParseException
     */
    public static Date string2Date(String str, String format) {
        return new JDateTime(str, format).convertToDate();
    }

    /**
     * 将Date格式化成String
     *
     * @param date   日期
     * @param format 格式化
     * @return
     */
    public static String date2String(Date date, String format) {
        return new JDateTime(date.getTime()).toString(format);
    }

    /**
     * 增加天数
     *
     * @param date 日期
     * @param day  要增加的天数  正数（n） ：增加n天；负数（n）：减少n天；0 ：日期不变
     * @return
     */
    public static Date addDay(Date date, int day) {
        return date2JDateTime(date).addDay(day).convertToDate();
    }

    /**
     * 减少天数
     *
     * @param date 日期
     * @param day  要减少的天数  正数（n） ：减少n天；负数（n）：增加n天；0 ：日期不变
     * @return
     */
    public static Date subDay(Date date, int day) {
        return date2JDateTime(date).subDay(day).convertToDate();
    }

    /**
     * 增加月份
     *
     * @param date
     * @param month
     * @return
     */
    public static Date addMonth(Date date, int month) {
        return date2JDateTime(date).addMonth(month).convertToDate();
    }

    /**
     * 减少月份
     *
     * @param date
     * @param month
     * @return
     */
    public static Date subMonth(Date date, int month) {
        return date2JDateTime(date).subMonth(month).convertToDate();
    }

    /**
     * 增加年份
     *
     * @param date
     * @param year
     * @return
     */
    public static Date addYear(Date date, int year) {
        return date2JDateTime(date).addYear(year).convertToDate();
    }

    /**
     * 减少年份
     *
     * @param date
     * @param year
     * @return
     */
    public static Date subYear(Date date, int year) {
        return date2JDateTime(date).subYear(year).convertToDate();
    }

    /**
     * 增加小时
     *
     * @param date
     * @param hour
     * @return
     */
    public static Date addHour(Date date, int hour) {
        return date2JDateTime(date).addHour(hour).convertToDate();
    }

    /**
     * 减少小时
     *
     * @param date
     * @param hour
     * @return
     */
    public static Date subHour(Date date, int hour) {
        return date2JDateTime(date).subHour(hour).convertToDate();
    }

    /**
     * 增加分钟
     *
     * @param date
     * @param minute
     * @return
     */
    public static Date addMinute(Date date, int minute) {
        return date2JDateTime(date).addMinute(minute).convertToDate();
    }

    /**
     * 减少分钟数
     *
     * @param date
     * @param minute
     * @return
     */
    public static Date subMinute(Date date, int minute) {
        return date2JDateTime(date).subMinute(minute).convertToDate();
    }

    /**
     * 增加秒数
     *
     * @param date
     * @param second
     * @return
     */
    public static Date addMinuteSecond(Date date, int second) {
        return date2JDateTime(date).addMillisecond(second).convertToDate();
    }

    /**
     * 减少秒数
     *
     * @param date
     * @param second
     * @return
     */
    public static Date subMinuteSecond(Date date, int second) {
        return date2JDateTime(date).subMillisecond(second).convertToDate();
    }

    /**
     * 加
     *
     * @param date
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param minuteSecond
     * @param millisecond
     * @return
     */
    public static Date add(Date date, int year, int month, int day, int hour, int minute, int minuteSecond, int millisecond) {
        return date2JDateTime(date).add(year, month, day, hour, minute, minuteSecond, millisecond).convertToDate();
    }

    /**
     * 减
     *
     * @param date
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param minuteSecond
     * @param millisecond
     * @return
     */
    public static Date sub(Date date, int year, int month, int day, int hour, int minute, int minuteSecond, int millisecond) {
        return date2JDateTime(date).sub(year, month, day, hour, minute, minuteSecond, millisecond).convertToDate();
    }

//    /**
//     * 两个日期相差的天数
//     *
//     * @param date1
//     * @param date2
//     * @return
//     */
//    public static long getPeriodOfDay(Date date1,Date date2){
//        return getPeriod(date1, date2).getDays();
//    }
//
//    /**
//     * 两个日期之间相差的小时数
//     *
//     * @param date1
//     * @param date2
//     * @return
//     */
//    public static long getPeriodOfHour(Date date1,Date date2){
//
//        return Math.abs(date1.getTime() - date2.getTime()) / 1000 / 60 / 60;
//
//    }

    public static Period getPeriod(Date date1, Date date2) {
        return new Period(date2JDateTime(date1), date2JDateTime(date2));
    }


    /**
     * java Date 转换成 JDateTime
     *
     * @param date
     * @return
     */
    public static JDateTime date2JDateTime(Date date) {
        return new JDateTime(date.getTime());
    }

    /**
     * 比较要比较的日期是否比被比较的日期大（日期格式不同）
     *
     * @param origDate   要比较的日期字符串
     * @param origFormat 要比较的日期字符串格式
     * @param desDate    被比较的日期字符串
     * @param desFormat  被比较的日期字符串格式
     * @return
     */
    public static boolean isAfter(String origDate, String origFormat, String desDate, String desFormat) {
        JDateTime origDateTime = new JDateTime(origDate, origFormat);
        JDateTime desDateTime = new JDateTime(desDate, desFormat);
        return origDateTime.isAfter(desDateTime);
    }

    /**
     * 比较要比较的日期是否比被比较的日期大（日期格式相同）
     *
     * @param origDate   要比较的日期字符串
     * @param format     日期字符串格式
     * @param desDate    被比较的日期字符串
     * @return
     */
    public static boolean isAfter(String origDate,String desDate, String format) {
        JDateTime origDateTime = new JDateTime(origDate, format);
        JDateTime desDateTime = new JDateTime(desDate, format);
        return origDateTime.isAfter(desDateTime);
    }

    /**
     * 日期比较（日期格式不同）
     *
     * @param origDate   要比较的日期字符串
     * @param origFormat 要比较的日期字符串格式
     * @param desDate    被比较的日期字符串
     * @param desFormat  被比较的日期字符串格式
     * @return 大于返回 1，等于返回 0，小于返回 -1
     */
    public static int compareTo(String origDate, String origFormat, String desDate, String desFormat) {
        JDateTime origDateTime = new JDateTime(origDate, origFormat);
        JDateTime desDateTime = new JDateTime(desDate, desFormat);
        return origDateTime.compareTo(desDateTime);
    }

    /**
     * 日期比较(日期格式相同)
     *
     * @param origDate   要比较的日期字符串
     * @param desDate    被比较的日期字符串
     * @param format     日期字符串格式
     * @return 大于返回 1，等于返回 0，小于返回 -1
     */
    public static int compareTo(String origDate,String desDate, String format) {
        JDateTime origDateTime = new JDateTime(origDate, format);
        JDateTime desDateTime = new JDateTime(desDate, format);
        return origDateTime.compareTo(desDateTime);
    }


    public static void main(String[] args) throws ParseException {
//        Date date1 = new JDateTime("2015-10-11 14:12:12", "YYYY-MM-DD hh:mm:ss").convertToDate();
//        Date date2 = new JDateTime("2015-10-11 01:12:12", "YYYY-MM-DD hh:mm:ss").convertToDate();
//        System.out.println(getPeriodOfHour(date1,date2));
//        System.out.println(isAfter("2015-10-11 01:13:12", "YYYY-MM-DD hh:mm:ss", "2015-10-11 01:12:12", "YYYY-MM-DD hh:mm:ss"));
        System.out.println(compareTo("2015-10-11 01:11:12", "YYYY-MM-DD hh:mm:ss", "2015-10-11 01:12:12", "YYYY-MM-DD hh:mm:ss"));
    }
}
