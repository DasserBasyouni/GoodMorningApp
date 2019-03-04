package com.tech.futureteric.backend.database;

import android.content.Context;

import com.tech.futureteric.backend.model.Message;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Message.class}, version = 1, exportSchema = false)
public abstract class MessagesDatabase extends RoomDatabase {

    private static MessagesDatabase INSTANCE, WIDGET_INSTANCE;

    public abstract MessageDao userDao();

    public synchronized static MessagesDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), MessagesDatabase.class,
                            "messages-db")
                            .build();
        }
        return INSTANCE;
    }

    public synchronized static MessagesDatabase getWidgetAppDatabase(Context context) {
        if (WIDGET_INSTANCE == null) {
            WIDGET_INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), MessagesDatabase.class,
                            "messages-db")
                            .allowMainThreadQueries()
                            .build();
        }
        return WIDGET_INSTANCE;
    }

    public static void destroyInstances() {
        INSTANCE = null;
        WIDGET_INSTANCE = null;
    }
}
