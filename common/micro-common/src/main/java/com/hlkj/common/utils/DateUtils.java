package com.hlkj.common.utils;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * @program: mall-classic
 * @description:
 * @author: 李向平
 * @create: 2021-03-17 15:31
 */
public class DateUtils {

    /**
     * 是否在时间范围类
     * @param date
     * @param start
     * @param end
     * @return
     */
    public static Boolean isInTimeLine(Date date, Date start, Date end){
        long time = date.getTime();
        long startTime = start.getTime();
        long endTime = end.getTime();
        if (time>startTime && time<endTime){
            return true;
        }
        return false;
    }

    /**
     * 获取当前时间戳
     * @return
     */
    public static Timestamp getCurrTimeStamp(){
        long time = Calendar.getInstance().getTime().getTime();
        return new Timestamp(time);
    }

}
