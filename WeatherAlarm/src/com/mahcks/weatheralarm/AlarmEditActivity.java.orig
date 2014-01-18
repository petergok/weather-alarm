package com.mahcks.weatheralarm;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
<<<<<<< HEAD:WeatherAlarm/src/com/mahcks/weatheralarm/MainActivity.java
import android.os.StrictMode;
=======
import android.view.MenuInflater;
>>>>>>> master -added all the shits:WeatherAlarm/src/com/mahcks/weatheralarm/AlarmEditActivity.java

/*
 * Main activity
 */
public class AlarmEditActivity extends Activity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
    	StrictMode.ThreadPolicy policy = new StrictMode.
    			ThreadPolicy.Builder().permitAll().build();
    			StrictMode.setThreadPolicy(policy); 
    	
    	WeatherGetter.getWeather();
    	System.out.println("hello world");
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Alarm alarm = new Alarm();
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
<<<<<<< HEAD:WeatherAlarm/src/com/mahcks/weatheralarm/MainActivity.java
        //comment
    	getMenuInflater().inflate(R.menu.main, menu);
        return true;
=======
    	MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
>>>>>>> master -added all the shits:WeatherAlarm/src/com/mahcks/weatheralarm/AlarmEditActivity.java
    }
    
}
