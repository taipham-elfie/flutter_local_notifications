package com.dexterous.flutterlocalnotifications.room.entity;

import android.util.Log;

import androidx.room.TypeConverter;

import com.dexterous.flutterlocalnotifications.models.Time;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public class Converters {
    private static final String TAG = "Converters";

    @TypeConverter
    public static Time fromString(String value) {
        try {
            if (value == null) return null;
            Type type = new TypeToken<Map<String, Object>>() {}.getType();
            Map<String, Object> mapValue = new Gson().fromJson(value, type);
            if (mapValue == null) return null;
            return Time.from(mapValue);
        } catch (Exception e) {
            Log.e(TAG, "fromString: Error parsing time", e);
        }
        return null;
    }

    @TypeConverter
    public static String timeToString(Time time) {
        if (time == null) return null;
        Map<String, Object> mapValue = Time.toMap(time);
        return new Gson().toJson(mapValue);
    }
}
