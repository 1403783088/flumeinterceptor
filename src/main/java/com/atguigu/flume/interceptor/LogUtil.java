package com.atguigu.flume.interceptor;

import org.apache.commons.lang.math.NumberUtils;

public class LogUtil {
    public static Boolean validateStart(String log){
        String trimedLog = log.trim();

        return trimedLog.startsWith("{") && trimedLog.endsWith("}");
    }

    public static Boolean validateEvent(String log){
        String[] split = log.split("\\|");

        if(split.length != 2) return false;

        if(!(split[0].length() == 13 && NumberUtils.isDigits(split[0]))) return false;

        return split[1].trim().startsWith("}") && split[1].trim().endsWith("}");
    }
}
