package com.mahcks.weatheralarm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AlarmRingActivity extends Activity {
	
	String description;
	int temp;
	
	public void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		WeatherData wd = WeatherGetter.getWeather();
		this.description = wd.weather;
		this.temp = (int)wd.temp_f;
		
		Button stopButton = (Button) findViewById(R.id.stopButton);
		stopButton.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
			
		});
		
		Button snoozeButton = (Button) findViewById(R.id.stopButton);
		snoozeButton.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlarmScheduler.setSnooze(AlarmRingActivity.this.getApplicationContext());
			}
			
		});
		
		if (description == wd.SNOW){
			
		}
		else if (description == wd.RAIN){
			
		}
		else if (description == wd.CLOUDY){
			
		}
		else if (description == wd.TSTORM){
			
		}
		else if (description == wd.CLEAR){
			
		}
	}
	
}
