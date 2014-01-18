package com.mahcks.weatheralarm.contentprovider;
import java.util.Arrays;
import java.util.HashSet;

import com.mahcks.weatheralarm.database.AlarmDatabaseHelper;
import com.mahcks.weatheralarm.database.AlarmTable;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class MyAlarmContentProvider extends ContentProvider {

  // database
  private AlarmDatabaseHelper database;

  // used for the UriMacher
  private static final int ALARMS = 10;
  private static final int ALARM_ID = 20;

  private static final String AUTHORITY = "com.mahcks.weatheralarm.contentprovider";

  private static final String BASE_PATH = "alarms";
  public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
      + "/" + BASE_PATH);

  public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
      + "/todos";
  public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
      + "/todo";

  private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
  static {
    sURIMatcher.addURI(AUTHORITY, BASE_PATH, ALARMS);
    sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", ALARM_ID);
  }

  @Override
  public boolean onCreate() {
    database = new AlarmDatabaseHelper(getContext());
    return false;
  }

  @Override
  public Cursor query(Uri uri, String[] projection, String selection,
      String[] selectionArgs, String sortOrder) {

    // Uisng SQLiteQueryBuilder instead of query() method
    SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

    // check if the caller has requested a column which does not exists
    checkColumns(projection);

    // Set the table
    queryBuilder.setTables(AlarmTable.TABLE_ALARMS);

    int uriType = sURIMatcher.match(uri);
    switch (uriType) {
    case ALARMS:
      break;
    case ALARM_ID:
      // adding the ID to the original query
      queryBuilder.appendWhere(AlarmTable.COLUMN_ID + "="
          + uri.getLastPathSegment());
      break;
    default:
      throw new IllegalArgumentException("Unknown URI: " + uri);
    }

    SQLiteDatabase db = database.getWritableDatabase();
    Cursor cursor = queryBuilder.query(db, projection, selection,
        selectionArgs, null, null, sortOrder);
    // make sure that potential listeners are getting notified
    cursor.setNotificationUri(getContext().getContentResolver(), uri);

    return cursor;
  }

  @Override
  public String getType(Uri uri) {
    return null;
  }

  @Override
  public Uri insert(Uri uri, ContentValues values) {
    int uriType = sURIMatcher.match(uri);
    SQLiteDatabase sqlDB = database.getWritableDatabase();
    int rowsDeleted = 0;
    long id = 0;
    switch (uriType) {
    case ALARMS:
      id = sqlDB.insert(AlarmTable.TABLE_ALARMS, null, values);
      break;
    default:
      throw new IllegalArgumentException("Unknown URI: " + uri);
    }
    getContext().getContentResolver().notifyChange(uri, null);
    return Uri.parse(BASE_PATH + "/" + id);
  }

  @Override
  public int delete(Uri uri, String selection, String[] selectionArgs) {
    int uriType = sURIMatcher.match(uri);
    SQLiteDatabase sqlDB = database.getWritableDatabase();
    int rowsDeleted = 0;
    switch (uriType) {
    case ALARMS:
      rowsDeleted = sqlDB.delete(AlarmTable.TABLE_ALARMS, selection,
          selectionArgs);
      break;
    case ALARM_ID:
      String id = uri.getLastPathSegment();
      if (TextUtils.isEmpty(selection)) {
        rowsDeleted = sqlDB.delete(AlarmTable.TABLE_ALARMS,
            AlarmTable.COLUMN_ID + "=" + id, 
            null);
      } else {
        rowsDeleted = sqlDB.delete(AlarmTable.TABLE_ALARMS,
            AlarmTable.COLUMN_ID + "=" + id 
            + " and " + selection,
            selectionArgs);
      }
      break;
    default:
      throw new IllegalArgumentException("Unknown URI: " + uri);
    }
    getContext().getContentResolver().notifyChange(uri, null);
    return rowsDeleted;
  }

  @Override
  public int update(Uri uri, ContentValues values, String selection,
      String[] selectionArgs) {

    int uriType = sURIMatcher.match(uri);
    SQLiteDatabase sqlDB = database.getWritableDatabase();
    int rowsUpdated = 0;
    switch (uriType) {
    case ALARMS:
      rowsUpdated = sqlDB.update(AlarmTable.TABLE_ALARMS, 
          values, 
          selection,
          selectionArgs);
      break;
    case ALARM_ID:
      String id = uri.getLastPathSegment();
      if (TextUtils.isEmpty(selection)) {
        rowsUpdated = sqlDB.update(AlarmTable.TABLE_ALARMS, 
            values,
            AlarmTable.COLUMN_ID + "=" + id, 
            null);
      } else {
        rowsUpdated = sqlDB.update(AlarmTable.TABLE_ALARMS, 
            values,
            AlarmTable.COLUMN_ID + "=" + id 
            + " and " 
            + selection,
            selectionArgs);
      }
      break;
    default:
      throw new IllegalArgumentException("Unknown URI: " + uri);
    }
    getContext().getContentResolver().notifyChange(uri, null);
    return rowsUpdated;
  }

  private void checkColumns(String[] projection) {
    String[] available = { AlarmTable.COLUMN_NAME, AlarmTable.COLUMN_TIME,
        AlarmTable.COLUMN_DAYS, AlarmTable.COLUMN_IS_SMART, AlarmTable.COLUMN_IS_CRES,
        AlarmTable.COLUMN_IS_SNOOZE, AlarmTable.COLUMN_VOLUME, AlarmTable.COLUMN_IS_ON,
        AlarmTable.COLUMN_ID };
    if (projection != null) {
      HashSet<String> requestedColumns = new HashSet<String>(Arrays.asList(projection));
      HashSet<String> availableColumns = new HashSet<String>(Arrays.asList(available));
      // check if all columns which are requested are available
      if (!availableColumns.containsAll(requestedColumns)) {
        throw new IllegalArgumentException("Unknown columns in projection");
      }
    }
  }

}
