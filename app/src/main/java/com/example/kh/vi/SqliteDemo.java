package com.example.kh.vi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kh on 4/23/2017.
 */

public class SqliteDemo extends SQLiteOpenHelper{
    private static String DATABASE_NAME = "demo";

    private static int DATABASE_VERSON = 1;
    private static String DATABASE_TABLE = "person";
    private static String CREATE_TALBE = "create table person (_id integer primary key);";
    public static String ID ="_id";
    private static String table ="person";
    private  SQLiteDatabase demo;
    public SqliteDemo(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSON);
    }
    public long insertdb(int id){
        ContentValues contentValues = new ContentValues();
        if(id!=-1)
        contentValues.put(ID,id);
        return demo.insert(table,null,contentValues);
       // return -1;
    }
    public long updatedb(int id, int value){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, value);
        String where;
        if(id>0)
         where="_id = "+id;
        else
            where  = "_id = "+-1;
     return   demo.update(table,contentValues,where,null);
    }
    public long deletedb(int id){
        String where;
        if(id>0)
            where="_id= "+id;
        else
            where  = "_id = "+-1;
        return   demo.delete(table,where,null);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TALBE);
    }
    public void openDB(){
        demo  = getWritableDatabase();

    }
    public void closeDB(){
        if(demo!=null && demo.isOpen())
            demo.close();
    }
    public Cursor loaddata(){
      return demo.rawQuery("select * from "+table,null);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
