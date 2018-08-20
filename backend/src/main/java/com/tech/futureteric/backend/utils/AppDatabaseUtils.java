package com.tech.futureteric.backend.utils;

import android.content.Context;

import com.tech.futureteric.backend.model.Message;

import java.util.List;

import static com.tech.futureteric.backend.database.MessagesDatabase.getAppDatabase;
import static com.tech.futureteric.backend.database.MessagesDatabase.getWidgetAppDatabase;

public class AppDatabaseUtils {

    public static void addMessage(Context context, Message message) {
        getAppDatabase(context).userDao().insertMessage(message);
    }

    public static List<Message> getAllMessages(Context context) {
        return getAppDatabase(context).userDao().getAll();
    }

    public static Message getUpComingMessage(Context context) {
        return getWidgetAppDatabase(context).userDao().getUpComingMessage();
    }
}