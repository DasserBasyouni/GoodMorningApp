package com.tech.futureteric.goodmorning.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import com.tech.futureteric.goodmorning.R;

import static com.tech.futureteric.backend.utils.AppDatabaseUtils.getUpComingMessage;


public class MorningMessagesWidget extends AppWidgetProvider {


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.morning_messages_widget);

        views.setTextViewText(R.id.textView_msg, getUpComingMessage(context).getMsg());

        String name = getUpComingMessage(context).getSenderName();
        if (name.isEmpty())
            name = "Unknown Contact";

        views.setTextViewText(R.id.textView_name, name);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

