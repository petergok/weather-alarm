package com.mahcks.weatheralarm;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AlarmRingActivity extends Activity {
	
	String description;
	int temp;
	String time;

	AlarmRingActivity self;
	public void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
		        + WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD|
		        + WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED|
		        + WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
		
		setContentView(R.layout.alarm_alert);
		self=this;
		Calendar c = Calendar.getInstance();
		
		String hours = ""+(int)c.get(Calendar.HOUR_OF_DAY);
		String minutes = ""+(int)c.get(Calendar.MINUTE);
		
		if((int)c.get(Calendar.HOUR_OF_DAY)<=9){
			hours = '0'+hours;
		}
		
		if((int)c.get(Calendar.MINUTE)<=9){
			minutes = '0'+minutes;
		}
		
		this.time=hours+":"+minutes;
		
		WeatherData wd = WeatherGetter.getWeather();
		this.description = wd.weather;
		this.temp = (int)wd.temp_f;
		
		Button stopButton = (Button) findViewById(R.id.stopButton);
		stopButton.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				WeatherSound.mp.stop();
				finish();
				return;
			}
			
		});
		
		Button snoozeButton = (Button) findViewById(R.id.snoozeButton);
		snoozeButton.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				AlarmScheduler.setSnooze(self);
				WeatherSound.mp.stop();
				///moveTaskToBack(true);
				finish();
				return;
			}
			
		});
		
		
		TextView textView = (TextView) findViewById(R.id.temp);
		textView.setText(this.temp+"F");
		
		ImageView imgView = (ImageView)findViewById(R.id.weatherIcon);
		textView = (TextView) findViewById(R.id.desc);
		textView.setText(this.description);
		if (wd.type.equals(wd.SNOW)){
			imgView.setImageResource(R.drawable.snow);
		}
		else if (wd.type.equals(wd.RAIN)){
			imgView.setImageResource(R.drawable.rain);
		}
		else if (wd.type.equals(wd.CLOUDY)){
			imgView.setImageResource(R.drawable.cloudy);
		}
		else if (wd.type.equals(wd.TSTORM)){
			imgView.setImageResource(R.drawable.storm);
		}
		else if (wd.type.equals(wd.CLEAR)){
			imgView.setImageResource(R.drawable.sunny);
		}
		
		textView = (TextView) findViewById(R.id.time);
		textView.setText(this.time);
		

		WeatherSound.playSound(this, wd.type, true);
	}
	
}
