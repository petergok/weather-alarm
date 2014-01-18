package com.mahcks.weatheralarm;

import com.mahcks.weatheralarm.contentprovider.MyAlarmContentProvider;
import com.mahcks.weatheralarm.database.AlarmTable;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.view.Menu;
import android.os.StrictMode;
import android.view.MenuInflater;
/*
 * Main activity
 */
public class AlarmEditActivity extends Activity {
	
	public Alarm alarm;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
    	StrictMode.ThreadPolicy policy = new StrictMode.
    			ThreadPolicy.Builder().permitAll().build();
    			StrictMode.setThreadPolicy(policy); 
    	
    	WeatherGetter.getWeather();
    	System.out.println("hello world");
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarm = new Alarm();
    }
    
    @Override
    public void onPause() {
    	saveAlarm();
    	super.onPause();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //comment
    	getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    private void saveAlarm() {

	    ContentValues values = new ContentValues();
	    values.put(AlarmTable.COLUMN_NAME, alarm.name);
	    values.put(AlarmTable.COLUMN_TIME, alarm.time);
	    values.put(AlarmTable.COLUMN_DAYS, alarm.days);
	    values.put(AlarmTable.COLUMN_IS_SMART, alarm.isSmart);
	    values.put(AlarmTable.COLUMN_IS_CRES, alarm.isCres);
	    values.put(AlarmTable.COLUMN_IS_SNOOZE, alarm.isSnooze);
	    values.put(AlarmTable.COLUMN_VOLUME, alarm.volume);

	    getContentResolver().insert(MyAlarmContentProvider.CONTENT_URI, values);
	}
    
}
