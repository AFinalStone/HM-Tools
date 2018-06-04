package com.hm.iou.tools;

import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author syl
 * @time 2018/5/31 下午6:35
 */
public class TimeUtil {

    private static TimeUtil INSTANCE;
    private SimpleDateFormat mDateFormat;

    public TimeUtil(String dateFormat) {
        this.mDateFormat = new SimpleDateFormat(dateFormat);
    }

    public synchronized static TimeUtil getInstance(SimpleDateFormatEnum dateFormatEnum) {
        if (INSTANCE == null) {
            INSTANCE = new TimeUtil(dateFormatEnum.getType());
        }
        return INSTANCE;
    }

    /**
     * 把timeInMillis转化成DateFormat格式的时间字符串返回
     *
     * @param timeInMillis 毫秒级时间
     * @return
     */
    public String getTimeInString(long timeInMillis) {
        return mDateFormat.format(new Date(timeInMillis));
    }

    /**
     * 把date转化成mDateFormat格式的时间字符串返回
     *
     * @param date
     * @return
     */
    public String getTimeInString(Date date) {
        return mDateFormat.format(date);
    }


    /**
     * 把String类型的时间转换成long类型的时间
     *
     * @param time
     * @return
     * @throws ParseException
     */
    public long getTimeInLong(String time) throws ParseException {
        Date date = mDateFormat.parse(time);
        return date.getTime();
    }

    /**
     * 把date转换成long类型的时间
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public long getTimeInLong(Date date) throws ParseException {
        return date.getTime();
    }

    /**
     * 把字符串time转换成Date返回
     *
     * @param time
     * @return
     * @throws ParseException
     */
    public Date getTimeInDate(String time) throws ParseException {
        Date date = mDateFormat.parse(time);
        return date;
    }

    /**
     * 把timeInMillis转化成Date返回
     *
     * @param timeInMillis
     * @return
     * @throws ParseException
     */
    public Date getTimeInDate(long timeInMillis) {
        return new Date(timeInMillis);
    }

    /**
     * 以字符串的形式返回当前系统时间
     *
     * @return
     */
    public String getCurrentTimeInString() {
        return mDateFormat.format(new Date(System.currentTimeMillis()));
    }

    /**
     * 以毫秒级的长整型的形式返回当前系统时间
     *
     * @return
     */
    public long getCurrentTimeInLong() {
        return System.currentTimeMillis();
    }

    /**
     * 以Date对象的形式返回当前系统时间
     *
     * @return
     */
    public Date getCurrentTimeInDate() {
        return new Date(System.currentTimeMillis());
    }

    public enum SimpleDateFormatEnum {
        DateFormatForApi("yyyy-MM-dd HH:mm:ss"),
        DateFormatForApp("yyyy.MM.dd HH:mm:ss");
        private String type;

        SimpleDateFormatEnum(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }


}





