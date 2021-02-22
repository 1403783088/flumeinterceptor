package com.atguigu.flume.interceptor;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.event.JSONEvent;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogTypeInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        byte[] body = event.getBody();
        String log = new String(body, StandardCharsets.UTF_8);

        Map<String, String> headers = event.getHeaders();

        if(log.contains("start"))
            headers.put("topic", "topic_start");
        else
            headers.put("topic", "topic_event");
        //因为headers是map的索引，使用put会直接在原map里面操作，所以不需要重新set
        return event;
    }

    @Override
    public List<Event> intercept(List<Event> events) {
        ArrayList<Event> events1 = new ArrayList<>();

        for(Event event : events){
            events1.add(intercept(event));
        }
        return events1;
    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder{

        @Override
        public Interceptor build() {
            return new LogTypeInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
