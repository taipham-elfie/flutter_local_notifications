package com.dexterous.flutterlocalnotifications.room.repository;
import android.content.Context;

import com.dexterous.flutterlocalnotifications.room.ElfieLocalDatabase;
import com.dexterous.flutterlocalnotifications.room.dao.ScheduleNotificationDAO;
import com.dexterous.flutterlocalnotifications.room.entity.ScheduleNotificationEntity;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ScheduleNotificationRepository {
    private static volatile ScheduleNotificationRepository INSTANCE;
    private final ScheduleNotificationDAO dao;
    private final ExecutorService executor;

    private ScheduleNotificationRepository(Context context) {
        ElfieLocalDatabase db = ElfieLocalDatabase.getInstance(context);
        this.dao = db.scheduleNotificationDAO();
        this.executor = Executors.newSingleThreadExecutor();
    }

    public static ScheduleNotificationRepository getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (ScheduleNotificationRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ScheduleNotificationRepository(context.getApplicationContext());
                }
            }
        }
        return INSTANCE;
    }

    public List<ScheduleNotificationEntity> getAllNotificationEntity() {
        Future<List<ScheduleNotificationEntity>> future =
                executor.submit(dao::getAllScheduleNotifications);
        try {
            return future.get(3, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            future.cancel(true);
            return Collections.emptyList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public ScheduleNotificationEntity getByNotificationId(int id) {
        Future<ScheduleNotificationEntity> future =
                executor.submit(() -> dao.getByNotificationId(id));
        try {
            return future.get(3, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            future.cancel(true);
            return null;
        } catch (Exception e) {
            return null;
        }
    }


    public void addNotificationsAsync(List<ScheduleNotificationEntity> entity) {
        executor.execute(() -> dao.addScheduleNotification(entity));
    }

    public void deleteByIdAsync(int id) {
        executor.execute(() -> dao.deleteByNotificationId(id));
    }

    public void deleteAllNotification() {
        executor.execute(dao::deleteAllNotification);
    }
}
