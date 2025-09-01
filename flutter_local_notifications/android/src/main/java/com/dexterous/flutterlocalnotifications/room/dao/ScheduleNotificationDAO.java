package com.dexterous.flutterlocalnotifications.room.dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.dexterous.flutterlocalnotifications.room.entity.ScheduleNotificationEntity;

import java.util.List;

@Dao
public interface ScheduleNotificationDAO {
    
    @Query("SELECT * FROM elfie_schedule_notifications")
    List<ScheduleNotificationEntity> getAllScheduleNotifications();

    @Query("SELECT * FROM elfie_schedule_notifications WHERE notificationId = :id LIMIT 1")
    ScheduleNotificationEntity getByNotificationId(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addScheduleNotification(List<ScheduleNotificationEntity> notification);

    @Query("DELETE FROM elfie_schedule_notifications WHERE notificationId = :id")
    void deleteByNotificationId(int id);

    @Query("DELETE FROM elfie_schedule_notifications")
    void deleteAllNotification();
}
