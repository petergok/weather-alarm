package com.mahcks.weatheralarm;

import android.app.*;
import android.content.*;
import android.os.IBinder;

import java.util.Calendar;


public class AlarmScheduler{

	public static void setAlarm(Context context,Alarm alarm){
		
		AlarmManager alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, AlarmReceiver.class);
		PendingIntent alarmIntent = PendingIntent.getBroadcast(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

		int hour = Integer.valueOf(alarm.time.substring(0,2));
		int min = Integer.valueOf(alarm.time.substring(3,5));
		
		for(int i=0;i<7;i++){
			if(alarm.days.charAt(i)=='t'){
				// Set the alarm to start at 8:30 a.m.
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(System.currentTimeMillis());
				calendar.set(Calendar.DAY_OF_WEEK, i);
				calendar.set(Calendar.HOUR_OF_DAY, hour%12);
				calendar.set(Calendar.MINUTE, min);
				if(hour<12)calendar.set(Calendar.AM_PM, Calendar.AM);
				else calendar.set(Calendar.AM_PM,Calendar.PM);
		
				// setRepeating() lets you specify a precise custom interval--in this case,
				// 20 minutes.
				alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
				        1000 * 60 * 60 * 24 * 7, alarmIntent);
			}
		}
		
	}
	
	public static void removeAlarm(Context context,Alarm alarm){
		AlarmManager alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, AlarmReceiver.class);
		PendingIntent alarmIntent = PendingIntent.getBroadcast(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
		alarmMgr.cancel(alarmIntent);
	}
	
	
}
