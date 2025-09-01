package com.dexterous.flutterlocalnotifications.models;

import androidx.annotation.Keep;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Keep
public class Time implements Serializable {
  private static final String HOUR = "hour";
  private static final String MINUTE = "minute";
  private static final String SECOND = "second";

  public Integer hour = 0;
  public Integer minute = 0;
  public Integer second = 0;

  public static Time from(Map<String, Object> map) {
    Time time = new Time();
    time.hour = (Integer) map.get(HOUR);
    time.minute = (Integer) map.get(MINUTE);
    time.second = (Integer) map.get(SECOND);
    return time;
  }

  public static  Map<String, Object> toMap(Time time){
    Map<String, Object> map = new HashMap<>();
    map.putIfAbsent(HOUR, time.hour);
    map.putIfAbsent(MINUTE, time.minute);
    map.putIfAbsent(SECOND, time.second);
    return map;
  }
}
