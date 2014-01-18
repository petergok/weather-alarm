package com.mahcks.weatheralarm;

import com.mahcks.weatheralarm.contentprovider.MyAlarmContentProvider;
import com.mahcks.weatheralarm.database.AlarmTable;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.view.Menu;
import android.view.MenuItem;
import android.os.StrictMode;
import android.view.MenuInflater;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.ToggleButton;
/*
 * Main activity
 */
public class AlarmEditActivity extends Activity {
	
	public Alarm alarm;
	public boolean saveAlarm;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
    	StrictMode.ThreadPolicy policy = new StrictMode.
    			ThreadPolicy.Builder().permitAll().build();
    			StrictMode.setThreadPolicy(policy); 
    	
    	WeatherGetter.getWeather();
    	System.out.println("hello world");
    	
        super.onCreate(savedInstanceState);
        saveAlarm = true;
        setContentView(R.layout.activity_main);
        alarm = new Alarm();
        getActionBar().setDisplayShowTitleEnabled(false);
        
        /*DATE BUTTONS*/
        ToggleButton toggleDayS = (ToggleButton) findViewById(R.id.toggleButton1);
        toggleDayS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    alarm.days="t"+alarm.days.substring(1);
                } else {
                	alarm.days="f"+alarm.days.substring(1);
                }
            }
        });
        
        ToggleButton toggleDayM = (ToggleButton) findViewById(R.id.toggleButton2);
        toggleDayM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                	alarm.days=alarm.days.substring(0,1)+"t"+alarm.days.substring(2);
                } else {
                	alarm.days=alarm.days.substring(0,1)+"f"+alarm.days.substring(2);
                }
            }
        });
        
        ToggleButton toggleDayT = (ToggleButton) findViewById(R.id.toggleButton3);
        toggleDayT.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                	alarm.days=alarm.days.substring(0,2)+"t"+alarm.days.substring(3);
                } else {
                	alarm.days=alarm.days.substring(0,2)+"f"+alarm.days.substring(3);
                }
            }
        });
        
        ToggleButton toggleDayW = (ToggleButton) findViewById(R.id.toggleButton4);
        toggleDayW.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                	alarm.days=alarm.days.substring(0,3)+"t"+alarm.days.substring(4);
                } else {
                	alarm.days=alarm.days.substring(0,3)+"f"+alarm.days.substring(4);
                }
            }
        });
        
        ToggleButton toggleDayTh = (ToggleButton) findViewById(R.id.toggleButton5);
        toggleDayTh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                	alarm.days=alarm.days.substring(0,4)+"t"+alarm.days.substring(5);
                } else {
                	alarm.days=alarm.days.substring(0,4)+"f"+alarm.days.substring(5);
                }
            }
        });
        
        ToggleButton toggleDayF = (ToggleButton) findViewById(R.id.toggleButton6);
        toggleDayF.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                	alarm.days=alarm.days.substring(0,5)+"t"+alarm.days.substring(6);
                } else {
                	alarm.days=alarm.days.substring(0,5)+"f"+alarm.days.substring(6);
                }
            }
        });
        
        ToggleButton toggleDaySa = (ToggleButton) findViewById(R.id.toggleButton7);
        toggleDaySa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                	alarm.days=alarm.days.substring(0,6)+"t";
                } else {
                	alarm.days=alarm.days.substring(0,6)+"f";
                }
            }
        });
        
        /*ALARM FEATURES TOGGLE*/
        
        Switch toggleSnooze = (Switch) findViewById(R.id.switchSnooze);
        toggleSnooze.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                	alarm.isSnooze=true;
                } else {
                	alarm.isSnooze=false;
                }
            }
        });
        
        Switch toggleCres = (Switch) findViewById(R.id.switchCrescendo);
        toggleCres.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                	alarm.isCres=true;
                } else {
                	alarm.isCres=false;
                }
            }
        });
        
        Switch toggleSmart = (Switch) findViewById(R.id.switchSmart);
        toggleSmart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                	alarm.isSmart=true;
                } else {
                	alarm.isSmart=false;
                }
            }
        });
        
        /*VOLUME*/
        SeekBar volumeControl = (SeekBar) findViewById(R.id.volumeBar);
        
        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				alarm.volume = seekBar.getProgress();
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				
			}
		});
    }
    
    @Override
    public void onPause() {
    	if (saveAlarm)
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
    
    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
		switch (item.getItemId()) {
    	case R.id.action_save:
    		alarm.isOn=true;
    		return true;
    	case R.id.action_cancel:
	    	saveAlarm=false;
	    	return true;
    	default:
    		return super.onOptionsItemSelected(item);
    	}
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
