package com.tech.futureteric.backend.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.tech.futureteric.backend.model.Message;

@Database(entities = {Message.class}, version = 1)
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
