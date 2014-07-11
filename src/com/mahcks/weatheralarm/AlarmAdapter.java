package com.mahcks.weatheralarm;

import com.mahcks.weatheralarm.contentprovider.MyAlarmContentProvider;
import com.mahcks.weatheralarm.database.AlarmTable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Rect;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class AlarmAdapter extends CursorAdapter {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    
    public AlarmAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View v = mLayoutInflater.inflate(R.layout.alarm_row, parent, false);
        return v;
    }

    /**
     * 
     * @param   v
     *          The view in which the elements we set up here will be displayed.
     * 
     * @param   context
     *          The running context where this ListView adapter will be active.
     * 
     * @param   c
     *          The Cursor containing the query results we will display.
     */

    @Override
    public void bindView(View v, Context context, Cursor c) {
        String name = c.getString(c.getColumnIndexOrThrow(AlarmTable.COLUMN_NAME));
        String time = c.getString(c.getColumnIndexOrThrow(AlarmTable.COLUMN_TIME));
        String days = c.getString(c.getColumnIndexOrThrow(AlarmTable.COLUMN_DAYS));

        /**
         * Next set the title of the entry.
         */

        TextView nameField = (TextView) v.findViewById(R.id.name);
        if (nameField != null)
            nameField.setText(name);
        
        TextView timeField = (TextView) v.findViewById(R.id.time);
        if (timeField != null)
        	timeField.setText(time);
        
        /*DAYS*/
        TextView day = (TextView) v.findViewById(R.id.daysS);
        if (days.charAt(0) == 't')
        	day.setTextColor(v.getResources().getColor(R.color.green));
        else
        	day.setTextColor(v.getResources().getColor(R.color.grayDarker));
        
        day = (TextView) v.findViewById(R.id.daysM);
        if (days.charAt(1) == 't')
        	day.setTextColor(v.getResources().getColor(R.color.green));
        else
        	day.setTextColor(v.getResources().getColor(R.color.grayDarker));
        
        day = (TextView) v.findViewById(R.id.daysT);
        if (days.charAt(2) == 't')
        	day.setTextColor(v.getResources().getColor(R.color.green));
        else
        	day.setTextColor(v.getResources().getColor(R.color.grayDarker));
        
        day = (TextView) v.findViewById(R.id.daysW);
        if (days.charAt(3) == 't')
        	day.setTextColor(v.getResources().getColor(R.color.green));
        else
        	day.setTextColor(v.getResources().getColor(R.color.grayDarker));
        
        day = (TextView) v.findViewById(R.id.daysTh);
        if (days.charAt(4) == 't')
        	day.setTextColor(v.getResources().getColor(R.color.green));
        else
        	day.setTextColor(v.getResources().getColor(R.color.grayDarker));
        
        day = (TextView) v.findViewById(R.id.daysF);
        if (days.charAt(5) == 't')
        	day.setTextColor(v.getResources().getColor(R.color.green));
        else
        	day.setTextColor(v.getResources().getColor(R.color.grayDarker));
        
        day = (TextView) v.findViewById(R.id.daysSa);
        if (days.charAt(6) == 't')
        	day.setTextColor(v.getResources().getColor(R.color.green));
        else
        	day.setTextColor(v.getResources().getColor(R.color.grayDarker));
    }
}
