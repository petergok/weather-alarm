package com.mahcks.weatheralarm;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
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
		setContentView(R.layout.alarm_alert);
		self=this;
		Calendar c = Calendar.getInstance();
		this.time=((int)c.get(Calendar.HOUR_OF_DAY))+":"+((int)c.get(Calendar.MINUTE));
		
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
				finish();
				return;
			}
			
		});
		
		
		TextView textView = (TextView) findViewById(R.id.temp);
		textView.setText(this.temp+"°F");
		
		ImageView imgView = (ImageView)findViewById(R.id.weatherIcon);
		textView = (TextView) findViewById(R.id.desc);
		textView.setText(this.description);
		if (description == wd.SNOW){
			imgView.setImageResource(R.drawable.snow);
		}
		else if (description == wd.RAIN){
			imgView.setImageResource(R.drawable.rain);
		}
		else if (description == wd.CLOUDY){
			imgView.setImageResource(R.drawable.cloudy);
		}
		else if (description == wd.TSTORM){
			imgView.setImageResource(R.drawable.storm);
		}
		else if (description == wd.CLEAR){
			imgView.setImageResource(R.drawable.sunny);
		}
		
		textView = (TextView) findViewById(R.id.time);
		textView.setText(this.time);
		

		WeatherSound.playSound(this, wd.type, true);
	}
	
}
