package com.bentie.primerabasedatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class DatabaseHelper{

   private Context context = null;
   private DatabaseHelperInternal dbHelper = null;
   private SQLiteDatabase db = null;
   private static final String DATABASE_NAME = "DBClientes";
   private static final int DATABASE_VERSION = 1;
   private static final String DATABASE_TABLE_CLIENTS = "Clientes";
   public static final String CODE = "codigo";
   public static final String NAME = "nombre";
   public static final String PHONE = "telefono";
   private static final String DATABASE_CREATE_CLIENTS = "create table " + DATABASE_TABLE_CLIENTS +
           " (" + CODE + " integer primary key, " + NAME + " text not null, " + PHONE + " text not null)";

   public DatabaseHelper(Context context){
       this.context = context;
   }

   private static class DatabaseHelperInternal extends SQLiteOpenHelper{

       public DatabaseHelperInternal(@Nullable Context context) {
           super(context, DATABASE_NAME, null, DATABASE_VERSION);
       }

       @Override
       public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE_CLIENTS);
       }

       @Override
       public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_CLIENTS);
       }
   }

   public DatabaseHelper open(){
       dbHelper = new DatabaseHelperInternal(context);
       db = dbHelper.getWritableDatabase();
       return this;
   }

   public void close(){
       dbHelper.close();
   }

   public long insertItem(int cod, String nom, String tel){
       ContentValues initialValues = new ContentValues();
       initialValues.put(CODE, cod);
       initialValues.put(NAME, nom);
       initialValues.put(PHONE, tel);
       return db.insert(DATABASE_TABLE_CLIENTS, null, initialValues);
   }

   public String getRawClients(){
       String[] fields = new String[]{CODE, NAME, PHONE};
       Cursor c = db.query(DATABASE_TABLE_CLIENTS, fields, null, null, null, null, null);
       StringBuilder dataBuilder = new StringBuilder();
       if(c.moveToFirst())
           do{
               dataBuilder.append(String.format(Locale.forLanguageTag("es"),"%d - %s:%s", c.getInt(0), c.getString(1), c.getString(2)));
               dataBuilder.append("\n");
           }while(c.moveToNext());
       c.close();
       return dataBuilder.toString();
   }

    public List<Client> getClients(){
        String[] fields = new String[]{CODE, NAME, PHONE};
        Cursor c = db.query(DATABASE_TABLE_CLIENTS, fields, null, null, null, null, null);
        List<Client> clients = new ArrayList<>();
        if(c.moveToFirst())
            do{
                clients.add(new Client(c.getString(c.getColumnIndex(CODE)),
                        c.getString(c.getColumnIndex(NAME)), c.getString(c.getColumnIndex(PHONE))));
            }while(c.moveToNext());
        c.close();
        return clients;
    }
}
