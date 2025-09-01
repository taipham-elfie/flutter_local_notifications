package com.dexterous.flutterlocalnotifications.room.entity;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import com.dexterous.flutterlocalnotifications.models.NotificationDetails;
import com.dexterous.flutterlocalnotifications.models.RepeatInterval;
import com.dexterous.flutterlocalnotifications.models.ScheduleMode;
import com.dexterous.flutterlocalnotifications.models.Time;
import com.dexterous.flutterlocalnotifications.room.Constant;
import com.google.gson.Gson;

import java.util.Map;

@Entity(tableName = Constant.TABLE_SCHEDULE_NOTIFICATION,
        indices = {@Index(value = {"notificationId"})})
public class ScheduleNotificationEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public Integer notificationId;
    public String title;
    public String body;
    public String payload;
    public String repeatInterval; // RepeatInterval
    public Integer repeatIntervalMilliseconds;
    public String timeZoneName;
    public String repeatTime;
    public Long calledAt;
    public Integer day;
    public String scheduleMode; // ScheduleMode
    public String scheduledDateTime;
    public Long millisecondsSinceEpoch;

    public static ScheduleNotificationEntity fromNotificationDetails(NotificationDetails notificationDetails) {
        ScheduleNotificationEntity entity = new ScheduleNotificationEntity();
        entity.notificationId = notificationDetails.id;
        entity.title = notificationDetails.title;
        entity.body = notificationDetails.body;
        entity.payload = notificationDetails.payload;
        entity.repeatInterval = notificationDetails.repeatInterval != null ? notificationDetails.repeatInterval.name() : null;
        entity.repeatIntervalMilliseconds = notificationDetails.repeatIntervalMilliseconds;
        entity.timeZoneName = notificationDetails.timeZoneName;
        entity.repeatTime = notificationDetails.repeatTime != null ? Converters.timeToString(notificationDetails.repeatTime) : null;
        entity.calledAt = notificationDetails.calledAt;
        entity.day = notificationDetails.day;
        entity.scheduleMode = notificationDetails.scheduleMode != null ? notificationDetails.scheduleMode.name() : null;
        entity.scheduledDateTime = notificationDetails.scheduledDateTime;
        entity.millisecondsSinceEpoch = notificationDetails.millisecondsSinceEpoch;
        return entity;
    }

    public NotificationDetails toNotificationDetails() {
        NotificationDetails notificationDetails = new NotificationDetails();
        notificationDetails.id = this.notificationId;
        notificationDetails.title = this.title;
        notificationDetails.body = this.body;
        notificationDetails.payload = this.payload;
        notificationDetails.repeatInterval = this.repeatInterval != null ? RepeatInterval.valueOf(this.repeatInterval) : null;
        notificationDetails.repeatIntervalMilliseconds = this.repeatIntervalMilliseconds;
        notificationDetails.timeZoneName = this.timeZoneName;
        notificationDetails.repeatTime = this.repeatTime != null ? Converters.fromString(this.repeatTime) : null;
        notificationDetails.calledAt = this.calledAt;
        notificationDetails.day = this.day;
        notificationDetails.scheduleMode = this.scheduleMode != null ? ScheduleMode.valueOf(this.scheduleMode) : ScheduleMode.exactAllowWhileIdle ;
        notificationDetails.scheduledDateTime = this.scheduledDateTime;
        notificationDetails.millisecondsSinceEpoch = this.millisecondsSinceEpoch;
        return notificationDetails;
    }
}

