package com.mahcks.weatheralarm;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.os.StrictMode;

/*
 * Main activity
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
    	StrictMode.ThreadPolicy policy = new StrictMode.
    			ThreadPolicy.Builder().permitAll().build();
    			StrictMode.setThreadPolicy(policy); 
    	
    	WeatherGetter.getWeather();
    	System.out.println("hello world");
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //comment
    	getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
