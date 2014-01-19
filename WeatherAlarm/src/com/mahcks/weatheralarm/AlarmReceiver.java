package com.mahcks.weatheralarm;

import android.content.*;
import android.os.Vibrator;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

	public static final String ACTION = "com.mahcks.alarmaction";
	
	@Override
	  public void onReceive(Context context, Intent intent) {
		
		
		Intent alarm = new Intent(context,AlarmRingActivity.class);
		alarm.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		alarm.putExtra("isCres", intent.getExtras().getInt("isCres"));
	    context.startActivity(alarm);
	    
	  }

	
}