package com.bentie.listatareas.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper {

    private Context context;
    private DatabaseHelperInternal dbHelper = null;
    private SQLiteDatabase db = null;
    protected static final String DB_NAME = "tasksDb";
    protected static final int DB_VERSION = 1;

    //tasks database creation query
    public static final String DB_TABLE_TASKS = "tasks";
    public static final String FIELD_TASK_ID = "id";
    public static final String FIELD_TASK_NAME = "name";
    public static final String FIELD_TASK_DESCRIPTION = "description";
    public static final String FIELD_TASK_END_DATE = "finishdate";
    public static final String FIELD_TASK_URGENCY = "urgency";

    private static final String CREATE_TABLE_TASKS = "create table " + DB_TABLE_TASKS + "(" +
            FIELD_TASK_ID + " integer primary key, " + FIELD_TASK_NAME + " text not null, " +
            FIELD_TASK_DESCRIPTION + " text, " + FIELD_TASK_END_DATE + " integer, " +
            FIELD_TASK_URGENCY + " integer not null)";

    public DatabaseHelper(Context context){
        this.context = context;
    }

    public DatabaseHelper open(){
        dbHelper = new DatabaseHelperInternal(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public SQLiteDatabase getDb(){
        return db;
    }

    public void close(){dbHelper.close();}

    private static class DatabaseHelperInternal extends SQLiteOpenHelper {

        DatabaseHelperInternal(@Nullable Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_TASKS);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_TASKS);
        }
    }

}
