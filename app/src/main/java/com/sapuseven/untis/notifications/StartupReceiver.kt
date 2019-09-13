package com.sapuseven.untis.notifications

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.sapuseven.untis.helpers.config.PreferenceManager
import com.sapuseven.untis.helpers.config.PreferenceUtils
import com.sapuseven.untis.notifications.NotificationSetup.Companion.EXTRA_LONG_PROFILE_ID
import java.util.*

class StartupReceiver : BroadcastReceiver() {
	// TODO: This gets called twice on starting the app. While this doesn't impair functionality, it is still unwanted behaviour.
	@SuppressLint("UnsafeProtectedBroadcastReceiver")
	override fun onReceive(context: Context, intent: Intent) {
		Log.d("StartupReceiver", "StartupReceiver received")
		val preferenceManager = PreferenceManager(context)
		if (!PreferenceUtils.getPrefBool(preferenceManager, "preference_notifications_enable"))
			return

		val calendar = Calendar.getInstance()
		calendar.set(Calendar.HOUR_OF_DAY, 2)
		calendar.set(Calendar.MINUTE, 0)
		calendar.set(Calendar.SECOND, 0)
		val newIntent = Intent(context, NotificationSetup::class.java)
		newIntent.putExtra(EXTRA_LONG_PROFILE_ID, preferenceManager.profileId)
		val pendingIntent = PendingIntent.getBroadcast(context, 0, newIntent, 0)
		val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
		am.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent)

		context.sendBroadcast(newIntent)
	}
}
