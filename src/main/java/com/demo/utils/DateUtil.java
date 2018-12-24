package com.demo.utils;

import java.util.Date;

/**
 * @Author: demo
 * @Date: 2018/12/21
 * @Description: com.demo.utils
 * @Version: 1.0
 */
public class DateUtil {

    /**
     * 设置从此刻开始的过期时间
     * @param nowTime 此刻时间
     * @param timeOut  过期时间 单位为(秒)
     * @return 过期时间
     */
    public static  Date addTime(Date nowTime, Integer timeOut) {
        Long time = nowTime.getTime();
        time += 1000 * 60 * timeOut;
        return  new Date(time);
    }
}
