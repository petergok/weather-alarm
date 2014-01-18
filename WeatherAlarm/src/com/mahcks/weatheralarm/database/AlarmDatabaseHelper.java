package com.mahcks.weatheralarm.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AlarmDatabaseHelper extends SQLiteOpenHelper {

		  private static final String DATABASE_NAME = "alarmstable.db";
		  private static final int DATABASE_VERSION = 5;

		  public AlarmDatabaseHelper(Context context) {
		    super(context, DATABASE_NAME, null, DATABASE_VERSION);
		  }

		  // Method is called during creation of the database
		  @Override
		  public void onCreate(SQLiteDatabase database) {
		    AlarmTable.onCreate(database);
		  }

		  // Method is called during an upgrade of the database,
		  // e.g. if you increase the database version
		  @Override
		  public void onUpgrade(SQLiteDatabase database, int oldVersion,
		      int newVersion) {
		    AlarmTable.onUpgrade(database, oldVersion, newVersion);
		  }
}
