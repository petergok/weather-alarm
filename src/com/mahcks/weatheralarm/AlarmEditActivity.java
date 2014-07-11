package com.mahcks.weatheralarm;

import com.mahcks.weatheralarm.contentprovider.MyAlarmContentProvider;
import com.mahcks.weatheralarm.database.AlarmTable;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.os.StrictMode;
import android.view.MenuInflater;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.TimePicker;
import android.widget.ToggleButton;

/*
 * Main activity
 */
public class AlarmEditActivity extends Activity {
	
	public Alarm alarm;
	public boolean saveAlarm;
	public Uri alarmUri;
	public AlarmEditActivity mActivity;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
    	super.onCreate(savedInstanceState);
    	
    	StrictMode.ThreadPolicy policy = new StrictMode.
    			ThreadPolicy.Builder().permitAll().build();
    			StrictMode.setThreadPolicy(policy); 
    	
    	//WeatherGetter.getWeather();
    	
        super.onCreate(savedInstanceState);
        
        getActionBar().setTitle("Save alarm"); 
        
        saveAlarm = true;
    	alarm = new Alarm();
    	mActivity = this;
    	
    	Bundle extras = getIntent().getExtras();
    	
    	// check from the saved Instance
        alarmUri = (savedInstanceState == null) ? null : (Uri) savedInstanceState
            .getParcelable(MyAlarmContentProvider.CONTENT_ITEM_TYPE);

        // Or passed from the other activity
        if (extras != null) {
          alarmUri = extras
              .getParcelable(MyAlarmContentProvider.CONTENT_ITEM_TYPE);

          fillData(alarmUri);
        }
    	
        setContentView(R.layout.activity_main);
        
        EditText editText = ((EditText) findViewById(R.id.autoCompleteTextView1));
        editText.setText(alarm.name);
        editText.setOnEditorActionListener(new OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                	findViewById(R.id.everything).requestFocus();
                    handled = true;
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                return handled;
            }

			
        });
        ((TimePicker) findViewById(R.id.timePicker)).setCurrentHour(Integer.valueOf(alarm.time.substring(0, 2)));
        ((TimePicker) findViewById(R.id.timePicker)).setCurrentMinute(Integer.valueOf(alarm.time.substring(3)));
        
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
        toggleDayS.setChecked(alarm.days.charAt(0) == 't');
        
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
        toggleDayM.setChecked(alarm.days.charAt(1) == 't');
        
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
        toggleDayT.setChecked(alarm.days.charAt(2) == 't');
        
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
        toggleDayW.setChecked(alarm.days.charAt(3) == 't');
        
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
        toggleDayTh.setChecked(alarm.days.charAt(4) == 't');
        
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
        toggleDayF.setChecked(alarm.days.charAt(5) == 't');
        
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
        toggleDaySa.setChecked(alarm.days.charAt(6) == 't');
        
        Switch toggleSnooze = (Switch) findViewById(R.id.switchSnooze);
        toggleSnooze.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                	alarm.isSnooze=1;
                } else {
                	alarm.isSnooze=0;
                }
            }
        });
        toggleSnooze.setChecked(alarm.isSnooze == 1);
        
        Switch toggleCres = (Switch) findViewById(R.id.switchCrescendo);
        toggleCres.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                	alarm.isCres=1;
                } else {
                	alarm.isCres=0;
                }
            }
        });
        toggleCres.setChecked(alarm.isCres == 1);
        
        Switch toggleSmart = (Switch) findViewById(R.id.switchSmart);
        toggleSmart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                	alarm.isSmart=1;
                } else {
                	alarm.isSmart=0;
                }
                mActivity.turnOnSmart(isChecked);
            }
        });
        toggleSmart.setChecked(alarm.isSmart == 1);
        
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
        volumeControl.setProgress(alarm.volume);
        
        turnOnSmart(alarm.isSmart == 1);
        
        ((EditText) findViewById(R.id.early_fog_text)).setText(String.valueOf(alarm.earlyFog));
        ((EditText) findViewById(R.id.early_rain_text)).setText(String.valueOf(alarm.earlyRain));
        ((EditText) findViewById(R.id.early_snow_text)).setText(String.valueOf(alarm.earlySnow));
        	
    }
    
    
    
    public void turnOnSmart (boolean isSmart) {
    	if (isSmart) {
        	((TableRow) findViewById(R.id.early_snow)).setVisibility(View.VISIBLE);
        	((TableRow) findViewById(R.id.early_fog)).setVisibility(View.VISIBLE);
        	((TableRow) findViewById(R.id.early_rain)).setVisibility(View.VISIBLE);
        } else {
        	((TableRow) findViewById(R.id.early_snow)).setVisibility(View.GONE);
        	((TableRow) findViewById(R.id.early_fog)).setVisibility(View.GONE);
        	((TableRow) findViewById(R.id.early_rain)).setVisibility(View.GONE);
        }
    }
    
    private void fillData(Uri uri) {
    	String[] projection = { AlarmTable.COLUMN_ID, AlarmTable.COLUMN_NAME, 
				AlarmTable.COLUMN_TIME, AlarmTable.COLUMN_DAYS,
				AlarmTable.COLUMN_IS_SMART, AlarmTable.COLUMN_IS_CRES,
				AlarmTable.COLUMN_IS_SNOOZE, AlarmTable.COLUMN_VOLUME,
				AlarmTable.COLUMN_EARLY_FOG, AlarmTable.COLUMN_EARLY_RAIN,
				AlarmTable.COLUMN_EARLY_SNOW, AlarmTable.COLUMN_IS_ON};
        Cursor c = getContentResolver().query(uri, projection, null, null,
            null);
        if (c != null) {
          c.moveToFirst();
          alarm.name = c.getString(c.getColumnIndexOrThrow(AlarmTable.COLUMN_NAME));
          alarm.time = c.getString(c.getColumnIndexOrThrow(AlarmTable.COLUMN_TIME));
          alarm.days = c.getString(c.getColumnIndexOrThrow(AlarmTable.COLUMN_DAYS));
          alarm.isSmart = c.getInt(c.getColumnIndexOrThrow(AlarmTable.COLUMN_IS_SMART));
          alarm.isCres = c.getInt(c.getColumnIndexOrThrow(AlarmTable.COLUMN_IS_CRES));
          alarm.isSnooze = c.getInt(c.getColumnIndexOrThrow(AlarmTable.COLUMN_IS_SNOOZE));
          alarm.volume = c.getInt(c.getColumnIndexOrThrow(AlarmTable.COLUMN_VOLUME));
          alarm.isOn = c.getInt(c.getColumnIndexOrThrow(AlarmTable.COLUMN_IS_ON));
          alarm.earlyFog = c.getInt(c.getColumnIndexOrThrow(AlarmTable.COLUMN_EARLY_FOG));
          alarm.earlyRain = c.getInt(c.getColumnIndexOrThrow(AlarmTable.COLUMN_EARLY_RAIN));
          alarm.earlySnow = c.getInt(c.getColumnIndexOrThrow(AlarmTable.COLUMN_EARLY_SNOW));
          alarm.id = c.getInt(c.getColumnIndexOrThrow(AlarmTable.COLUMN_ID));

          // always close the cursor
          c.close();
        }
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
    		alarm.isOn=1;
    		saveAlarm = true;
    		this.finish();
    		return false;
    	case R.id.action_cancel:
	    	saveAlarm=false;
	    	this.finish();
	    	return false;
    	default:
    		return super.onOptionsItemSelected(item);
    	}
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveAlarm();
        outState.putParcelable(MyAlarmContentProvider.CONTENT_ITEM_TYPE, alarmUri);
      }
    
    @Override
    public void onBackPressed(){
    	saveAlarm = false;
    	super.onBackPressed();
    }
    
    private void saveAlarm() {
    	alarm.name = ((EditText) findViewById(R.id.autoCompleteTextView1)).getText().toString();
    	if (alarm.name.equals(""))
    		alarm.name = "Alarm";
    	alarm.time = ((TimePicker) findViewById(R.id.timePicker)).getCurrentHour().toString() + ":"
    				+ ((TimePicker) findViewById(R.id.timePicker)).getCurrentMinute().toString();
    	
    	if (alarm.time.indexOf(":") != 2)
    		alarm.time = "0" + alarm.time;
    	if (alarm.time.length() < 5)
    		alarm.time = alarm.time.substring(0, 3) + "0" + alarm.time.substring(3);
    	
    	String editText = ((EditText) findViewById(R.id.early_fog_text)).getText().toString();
    	if (!editText.equals(""))
    		alarm.earlyFog = Integer.valueOf(editText);
    	
    	editText = ((EditText) findViewById(R.id.early_rain_text)).getText().toString();
    	if (!editText.equals(""))
    		alarm.earlyRain = Integer.valueOf(editText);
    	
    	editText = ((EditText) findViewById(R.id.early_snow_text)).getText().toString();
    	if (!editText.equals(""))
    		alarm.earlySnow = Integer.valueOf(editText);

	    ContentValues values = new ContentValues();
	    values.put(AlarmTable.COLUMN_NAME, alarm.name);
	    values.put(AlarmTable.COLUMN_TIME, alarm.time);
	    values.put(AlarmTable.COLUMN_DAYS, alarm.days);
	    values.put(AlarmTable.COLUMN_IS_SMART, alarm.isSmart);
	    values.put(AlarmTable.COLUMN_IS_CRES, alarm.isCres);
	    values.put(AlarmTable.COLUMN_IS_SNOOZE, alarm.isSnooze);
	    values.put(AlarmTable.COLUMN_VOLUME, alarm.volume);
	    values.put(AlarmTable.COLUMN_EARLY_FOG, alarm.earlyFog);
	    values.put(AlarmTable.COLUMN_EARLY_RAIN, alarm.earlyRain);
	    values.put(AlarmTable.COLUMN_EARLY_SNOW, alarm.earlySnow);
	    values.put(AlarmTable.COLUMN_IS_ON, alarm.isOn);
	    
	    if (alarmUri == null) {
	        // New todo
	        alarmUri = getContentResolver().insert(MyAlarmContentProvider.CONTENT_URI, values);
	        String[] projection = { AlarmTable.COLUMN_ID, AlarmTable.COLUMN_NAME, 
					AlarmTable.COLUMN_TIME, AlarmTable.COLUMN_DAYS,
					AlarmTable.COLUMN_IS_SMART, AlarmTable.COLUMN_IS_CRES,
					AlarmTable.COLUMN_IS_SNOOZE, AlarmTable.COLUMN_VOLUME,
					AlarmTable.COLUMN_EARLY_FOG, AlarmTable.COLUMN_EARLY_RAIN,
					AlarmTable.COLUMN_EARLY_SNOW, AlarmTable.COLUMN_IS_ON};
	        Cursor c = getContentResolver().query(alarmUri, projection, null, null,
	            null);
	        if (c != null) {
	        	alarm.id = c.getInt(c.getColumnIndexOrThrow(AlarmTable.COLUMN_ID));
	        	c.close();
	        }
	    } else {
	        // Update todo
	        getContentResolver().update(alarmUri, values, null, null);
	    }
	    
	    AlarmScheduler.setAlarm(this, alarm);
	}
    
}
