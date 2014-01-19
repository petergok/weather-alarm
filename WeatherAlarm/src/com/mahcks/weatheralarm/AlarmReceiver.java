package com.mahcks.weatheralarm;

import android.content.*;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	  public void onReceive(Context context, Intent intent) {
		
	    //Intent service = new Intent(context, WordService.class);
	    //context.startService(service);
		Toast.makeText(context, "ALARM",500).show();
	  }

	
}