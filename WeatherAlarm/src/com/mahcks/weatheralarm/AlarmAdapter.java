package com.mahcks.weatheralarm;

import com.mahcks.weatheralarm.database.AlarmTable;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

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
        int isSmart = c.getInt(c.getColumnIndexOrThrow(AlarmTable.COLUMN_IS_SMART));
        int isCres = c.getInt(c.getColumnIndexOrThrow(AlarmTable.COLUMN_IS_CRES));
        int isSnooze = c.getInt(c.getColumnIndexOrThrow(AlarmTable.COLUMN_IS_SNOOZE));
        int volume = c.getInt(c.getColumnIndexOrThrow(AlarmTable.COLUMN_VOLUME));
        int isOn = c.getInt(c.getColumnIndexOrThrow(AlarmTable.COLUMN_IS_ON));

        /**
         * Next set the title of the entry.
         */

        TextView nameField = (TextView) v.findViewById(R.id.label);
        if (nameField != null) {
            nameField.setText(name);
        }
    }
}
