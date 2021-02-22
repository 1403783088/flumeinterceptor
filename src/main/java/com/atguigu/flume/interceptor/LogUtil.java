package com.atguigu.flume.interceptor;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.flume.Event;
import org.apache.flume.event.JSONEvent;

import java.util.HashMap;

public class LogUtil {
    public static Boolean validateStart(String log){
        String trimedLog = log.trim();

        return trimedLog.startsWith("{") && trimedLog.endsWith("}");
    }

    public static Boolean validateEvent(String log){
        String[] split = log.split("\\|");

        if(split.length != 2) return false;

        if(!(split[0].length() == 13 && NumberUtils.isDigits(split[0]))) return false;


        return (split[1].trim().startsWith("{") && split[1].trim().endsWith("}"));
    }

    public static void main(String[] args) {
        JSONEvent jsonEvent = new JSONEvent();
        jsonEvent.setBody("1613954217436|{\"cm\":{\"ln\":\"-70.8\",\"sv\":\"V2.2.5\",\"os\":\"8.1.0\",\"g\":\"H0B15D50@gmail.com\",\"mid\":\"0\",\"nw\":\"3G\",\"l\":\"pt\",\"vc\":\"2\",\"hw\":\"640*1136\",\"ar\":\"MX\",\"uid\":\"0\",\"t\":\"1613920435349\",\"la\":\"25.6\",\"md\":\"sumsung-15\",\"vn\":\"1.3.3\",\"ba\":\"Sumsung\",\"sr\":\"K\"},\"ap\":\"app\",\"et\":[{\"ett\":\"1613926026723\",\"en\":\"display\",\"kv\":{\"goodsid\":\"0\",\"action\":\"1\",\"extend1\":\"2\",\"place\":\"5\",\"category\":\"91\"}},{\"ett\":\"1613859626710\",\"en\":\"notification\",\"kv\":{\"ap_time\":\"1613953904544\",\"action\":\"2\",\"type\":\"4\",\"content\":\"\"}},{\"ett\":\"1613897132898\",\"en\":\"active_background\",\"kv\":{\"active_source\":\"3\"}},{\"ett\":\"1613937927273\",\"en\":\"comment\",\"kv\":{\"p_comment_id\":0,\"addtime\":\"1613865561737\",\"praise_count\":216,\"other_id\":7,\"comment_id\":2,\"reply_count\":66,\"userid\":2,\"content\":\"祟页慌噎扇慎>氟偶相厕凰辜隅\"}},{\"ett\":\"1613947600960\",\"en\":\"favorites\",\"kv\":{\"course_id\":6,\"id\":0,\"add_time\":\"1613920711694\",\"userid\":4}}]}".getBytes());
        HashMap<String, String> headers = new HashMap<>();
        headers.put("time", "2021");
        jsonEvent.setHeaders(headers);
        LogETLInterceptor logETLInterceptor = new LogETLInterceptor();
        LogTypeInterceptor logTypeInterceptor = new LogTypeInterceptor();
        Event intercept1 = logETLInterceptor.intercept(jsonEvent);
        System.out.println(intercept1.getHeaders());
    }
}
