package com.atguigu.flume.interceptor;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class LogETLInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        String log = new String(event.getBody(), StandardCharsets.UTF_8);

        if(log.contains("start")){
            if(LogUtil.validateStart(log)){
                return event;
            }
        }else{
            if(LogUtil.validateEvent(log)){
                return event;
            }
        }

        return null;
    }

    @Override
    public List<Event> intercept(List<Event> events) {
        ArrayList<Event> eventArrayList = new ArrayList<>();
        for (Event event : events) {
            Event intercept = intercept(event);
            if(intercept != null) eventArrayList.add(event);
        }

        return eventArrayList;
    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder{
        @Override
        public Interceptor build() {
            return new LogETLInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
