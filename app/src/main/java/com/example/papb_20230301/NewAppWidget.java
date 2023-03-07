package com.example.papb_20230301;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.RemoteViews;

import java.text.DateFormat;
import java.util.Date;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {
    private static final String appDirectory = "com.example.papb_20230201";
    private static final String COUNT_KEY = "count";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        SharedPreferences prefs = context.getSharedPreferences(appDirectory, 0);
        int count = prefs.getInt(COUNT_KEY + appWidgetId, 0);
        String dateString = DateFormat.getTimeInstance(DateFormat.SHORT).format(new Date());
        count++;

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);

        views.setTextViewText(R.id.tv_appWidget_id, String.valueOf(appWidgetId));
        views.setTextViewText(R.id.tv_appwidget_update_label, String.valueOf(appWidgetId));
        views.setTextViewText(R.id.tv_appwidget_update, dateString);
        views.setTextViewText(R.id.tv_appwidget_count, String.valueOf(count));
        views.setTextViewText(R.id.tv_appwidget_update_label, String.valueOf(count));

        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putInt(COUNT_KEY + appWidgetId, count);
        prefsEditor.apply();

        Intent intent = new Intent(context, NewAppWidget.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        int[] idArray = new int[] {appWidgetId};
        intent.putExtra(appWidgetManager.EXTRA_APPWIDGET_IDS, idArray);

        PendingIntent pendingUpdate = PendingIntent.getBroadcast(context, appWidgetId, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        views.setOnClickPendingIntent(R.id.button_update, pendingUpdate);

        // Instruct the widget manager to update the widget
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