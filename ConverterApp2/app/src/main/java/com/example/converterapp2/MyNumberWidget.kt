package com.example.converterapp2

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

/**
 * Implementation of App Widget functionality.
 */
class MyNumberWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        var fileWidget = fileSharedPre(context!!)
        if(AddOnClick.equals(intent?.action)) {
            fileWidget.number += 1
        }
        else if (MinOnClick.equals(intent?.action)) {
            fileWidget.number -= 1
        }
        var appWidgetManager = AppWidgetManager.getInstance(context)
        var ids = appWidgetManager.getAppWidgetIds(ComponentName(context!!, MyNumberWidget::class.java))
        for(appWidgetId in ids) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }
    companion object {
        private val AddOnClick = "AddOnClick"
        private val MinOnClick = "MinOnClick"
        internal fun updateAppWidget(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            // Construct the RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.my_number_widget)
            var filePref = fileSharedPre(context)
            views.setTextViewText(R.id.appwidget_text_number, filePref.number.toString())

            views.setOnClickPendingIntent(R.id.appwidget_btn_plus, getPendingSelfIntent(context, AddOnClick))
            views.setOnClickPendingIntent(R.id.appwidget_btn_minus, getPendingSelfIntent(context, MinOnClick))
            views.setOnClickPendingIntent(R.id.layoutWidget, getPendingIntentActivity(context))
            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }

        private fun getPendingSelfIntent(context: Context, StringOnClick: String): PendingIntent? = Intent(
                context, MyNumberWidget::class.java).let {
            it.action = StringOnClick
            PendingIntent.getBroadcast(context, 101, it, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        private fun getPendingIntentActivity (context: Context): PendingIntent {
            var myIntent = Intent(context, WidgetActivity::class.java)
            return PendingIntent.getActivity(context, 0, myIntent, 0)
        }
    }
}