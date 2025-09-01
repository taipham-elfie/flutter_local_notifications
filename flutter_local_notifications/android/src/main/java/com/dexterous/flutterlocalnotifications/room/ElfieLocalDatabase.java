package com.dexterous.flutterlocalnotifications.room;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.dexterous.flutterlocalnotifications.room.dao.ScheduleNotificationDAO;
import com.dexterous.flutterlocalnotifications.room.entity.ScheduleNotificationEntity;

@androidx.room.Database(entities = {ScheduleNotificationEntity.class}, version = 1, exportSchema = false)
public abstract class ElfieLocalDatabase extends RoomDatabase {
    public abstract ScheduleNotificationDAO scheduleNotificationDAO();
    private static volatile ElfieLocalDatabase INSTANCE;

    public static ElfieLocalDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (ElfieLocalDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    ElfieLocalDatabase.class,
                                    Constant.DATABASE_NAME
                            )
                            .setJournalMode(JournalMode.WRITE_AHEAD_LOGGING)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
