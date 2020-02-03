package com.bentie.listatareas.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bentie.listatareas.database.DatabaseHelper;
import com.bentie.listatareas.model.Task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskDAO {

    private SQLiteDatabase db;
    private Context context;

    public TaskDAO(Context context, SQLiteDatabase db){
        this.context = context;
        this.db = db;
    }

    public List<Task> getAllTasks(){
        String[] fields = new String[]{DatabaseHelper.FIELD_TASK_ID, DatabaseHelper.FIELD_TASK_NAME,
                DatabaseHelper.FIELD_TASK_DESCRIPTION, DatabaseHelper.FIELD_TASK_END_DATE};
        List<Task> tasks = new ArrayList<>();
        if(db != null){
            Cursor c = db.query(DatabaseHelper.DB_TABLE_TASKS, fields,null, null, null, null, null);
            if(c.moveToFirst()){
                do{
                    tasks.add(new Task(c.getInt(c.getColumnIndex(DatabaseHelper.FIELD_TASK_ID)),
                            c.getString(c.getColumnIndex(DatabaseHelper.FIELD_TASK_NAME)),
                            c.getString(c.getColumnIndex(DatabaseHelper.FIELD_TASK_DESCRIPTION)),
                            new Date(c.getLong(c.getColumnIndex(DatabaseHelper.FIELD_TASK_END_DATE))),
                            c.getInt(c.getColumnIndex(DatabaseHelper.FIELD_TASK_URGENCY))));
                }while(c.moveToNext());
            }
            c.close();
        }
        return tasks;
    }

    public void insert(Task task){
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.FIELD_TASK_ID, task.getId());
        cv.put(DatabaseHelper.FIELD_TASK_NAME, task.getName());
        cv.put(DatabaseHelper.FIELD_TASK_DESCRIPTION, task.getDescription());
        cv.put(DatabaseHelper.FIELD_TASK_END_DATE, task.getFinishDate().getTime());
        cv.put(DatabaseHelper.FIELD_TASK_URGENCY, task.getUrgency());
        db.insert(DatabaseHelper.DB_TABLE_TASKS, null, cv);
    }

    public void delete(Task task){
        db.delete(DatabaseHelper.DB_TABLE_TASKS, DatabaseHelper.FIELD_TASK_ID + " = ?",
                new String[]{String.valueOf(task.getId())});
    }

}
