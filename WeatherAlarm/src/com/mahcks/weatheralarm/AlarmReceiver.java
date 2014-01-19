package com.mahcks.weatheralarm;

import android.content.*;
import android.os.Vibrator;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	  public void onReceive(Context context, Intent intent) {
		
		
		Intent alarm = new Intent(context, AlarmRingActivity.class);
		alarm.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    context.startActivity(alarm);
	    //Intent service = new Intent(context, WordService.class);
	    //context.startService(service);
//		System.out.println("FUCK YOU");
//		Toast.makeText(context, "ALARM",1500).show();
//		Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
//		// Vibrate for 500 milliseconds
//		v.vibrate(500);
	  }

	
}