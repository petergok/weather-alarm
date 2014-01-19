package com.mahcks.weatheralarm;

import java.util.ArrayList;

import com.mahcks.weatheralarm.contentprovider.MyAlarmContentProvider;
import com.mahcks.weatheralarm.database.AlarmTable;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class WeatherAlarmOverview extends ListActivity 
	implements LoaderManager.LoaderCallbacks<Cursor> {
	
	public static WeatherAlarmOverview mActivity;
	
	// private Cursor cursor;
	private AlarmAdapter mAdapter;
	private View checkedItem;
	private int checkedItemPos;
	private long checkedItemId;
	private boolean createdAction = false;
	private ActionMode mActionMode;
	
	private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

	    // Called when the action mode is created; startActionMode() was called
	    @Override
	    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
	        // Inflate a menu resource providing context menu items
	        MenuInflater inflater = mode.getMenuInflater();
	        inflater.inflate(R.menu.context_menu_alarms, menu);
	        return true;
	    }

	    // Called each time the action mode is shown. Always called after onCreateActionMode, but
	    // may be called multiple times if the mode is invalidated.
	    @Override
	    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
	        return false; // Return false if nothing is done
	    }

	    // Called when the user selects a contextual menu item
	    @Override
	    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
	        switch (item.getItemId()) {
	            case R.id.delete_alarm:
	            	deleteSelectedItem();
	                mode.finish(); // Action picked, so close the CAB
	                return true;
	            case R.id.edit_alarm:
	                Intent i = new Intent(mActivity, AlarmEditActivity.class);
	        	    Uri alarmUri = Uri.parse(MyAlarmContentProvider.CONTENT_URI + "/" + checkedItemId);
	        	    i.putExtra(MyAlarmContentProvider.CONTENT_ITEM_TYPE, alarmUri);
	        	    
	        	    startActivity(i);
	        	    mode.finish();
	        	    return true;
	            default:
	                return false;
	        }
	    }

	    // Called when the user exits the action mode
	    @SuppressLint("NewApi")
		@Override
	    public void onDestroyActionMode(ActionMode mode) {
	        mActionMode = null;
	        createdAction = false;
	        for (int child = 0; child < mActivity.getListView().getChildCount(); child++) {
				mActivity.getListView().getChildAt(child).setBackground(getResources().getDrawable(R.drawable.alarm_list_background));
			}
	    }
	};
	
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		mActivity = this;
		super.onCreate(savedInstanceState);
		
	    setContentView(R.layout.alarm_list);
	    
	    getActionBar().setTitle("Create alarm");
	    
	    this.getListView().setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (mActionMode != null) {
	                return false;
	            }

	            // Start the CAB using the ActionMode.Callback defined above
	            mActionMode = mActivity.startActionMode(mActionModeCallback);
	            view.setSelected(true);
	            checkedItemId = id;
	            createdAction = true;
	            checkedItem = view;
	            
	            view.setBackground(getResources().getDrawable(R.drawable.alarm_list_background_selected));
	            return true;
			}
	    });
	    
	    this.getListView().setDividerHeight(2);
	    
	    this.getListView().setOnTouchListener(new OnTouchListener() {

			@SuppressLint("NewApi")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (!createdAction && event.getActionMasked() == MotionEvent.ACTION_DOWN && mActivity.getListView().getChildCount() > 0) {
					float x = event.getX();
					float y = event.getY() + 120;
					for (int child = 0; child < mActivity.getListView().getChildCount(); child++) {
						Rect r = new Rect();
						mActivity.getListView().getChildAt(child).getGlobalVisibleRect(r);
						if (r.contains((int)x, (int)y))
								mActivity.getListView().getChildAt(Math.min(child, mActivity.getListView().getChildCount())).setBackground(getResources().getDrawable(R.drawable.alarm_list_background_selected));
					}
				}
				else if (!createdAction) {
					for (int child = 0; child < mActivity.getListView().getChildCount(); child++) {
						mActivity.getListView().getChildAt(child).setBackground(getResources().getDrawable(R.drawable.alarm_list_background));
					}
				}
				return false;
			}
	    	
	    });
	    
	    fillData();
	    registerForContextMenu(getListView());
	    
	    StrictMode.ThreadPolicy policy = new StrictMode.
	    		ThreadPolicy.Builder().permitAll().build();
	    		StrictMode.setThreadPolicy(policy); 
	}
	
	public void deleteSelectedItem() {
		Uri alarmUri = Uri.parse(MyAlarmContentProvider.CONTENT_URI + "/" + checkedItemId);
		getContentResolver().delete(alarmUri, AlarmTable.COLUMN_ID + "=?", new String[]{String.valueOf(checkedItemId)});
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		String[] projection = { AlarmTable.COLUMN_ID, AlarmTable.COLUMN_NAME, 
								AlarmTable.COLUMN_TIME, AlarmTable.COLUMN_DAYS,
								AlarmTable.COLUMN_IS_SMART, AlarmTable.COLUMN_IS_CRES,
								AlarmTable.COLUMN_IS_SNOOZE, AlarmTable.COLUMN_VOLUME,
								AlarmTable.COLUMN_IS_ON, AlarmTable.COLUMN_EARLY_FOG,
								AlarmTable.COLUMN_EARLY_RAIN, AlarmTable.COLUMN_EARLY_SNOW};
	    CursorLoader cursorLoader = new CursorLoader(this,
	        MyAlarmContentProvider.CONTENT_URI, projection, null, null, null);
	    return cursorLoader;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor data) {
		mAdapter.swapCursor(data);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		// data is not available anymore, delete reference
	    mAdapter.swapCursor(null);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.alarm_list_menu, menu);
	    return true;
	}
	
	// Reaction to the menu selection
	  @Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case R.id.create_new_alarm:
	      createAlarm();
	      return true;
	    }
	    return super.onOptionsItemSelected(item);
	  }
	  
	  private void createAlarm() {
		  Intent i = new Intent(this, AlarmEditActivity.class);
		  startActivity(i);
	  }
	  
	// Opens the second activity if an entry is clicked
	  @SuppressLint("NewApi")
	@Override
	  protected void onListItemClick(ListView l, View v, int position, long id) {
		if (mActionMode == null) {
			super.onListItemClick(l, v, position, id);
			v.setBackground(getResources().getDrawable(R.drawable.alarm_list_background));
			Intent i = new Intent(this, AlarmEditActivity.class);
			Uri alarmUri = Uri.parse(MyAlarmContentProvider.CONTENT_URI + "/" + id);
			i.putExtra(MyAlarmContentProvider.CONTENT_ITEM_TYPE, alarmUri);
			createdAction = false;

			startActivity(i);
		} else if (checkedItem.equals(v)){
			checkedItem.setBackground(getResources().getDrawable(R.drawable.alarm_list_background));
			mActionMode.finish();
		}
	  }
	
	private void fillData() {

	    getLoaderManager().initLoader(0, null, this);
	    mAdapter = new AlarmAdapter(this, null, 0);

	    setListAdapter(mAdapter);
	 }

}
