package com.example.abdull.database_asignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObservable;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by abdull on 4/10/17.
 */

public class helper extends SQLiteOpenHelper {

    public static final String dataBaseName="Famous";
    public static final String table="People";
    public static final int Version=1;
    public static final String UNIQUE_ID = BaseColumns._ID;
    public static final String NAME = "NAME";
    public static final String Inspiration = "INSPIRATION";

    // table Query

    String PeopleTable = "CREATE TABLE " + table + "("
            + UNIQUE_ID + " INTEGER PRIMARY KEY,"
            + NAME + " TEXT,"
            + Inspiration + " TEXT"
            + ")";
    Context context;

    public helper(Context context) {
        super(context, dataBaseName, null, Version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PeopleTable);
        Log.d("Database Table", "Database tables created");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public long addInspiration(String name,String inspiration)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(NAME,name);
        contentValues.put(Inspiration,inspiration);
        long id=database.insert(table,null,contentValues);
        database.close();
        return id;
    }
    public ArrayList showRecord()
    {
        ArrayList arrayList=new ArrayList();
        String Select="Select *from "+table;
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.rawQuery(Select,null);
        while(cursor.moveToNext())
        {
            int namePosition=cursor.getColumnIndex(NAME);
            int inspirationPostion=cursor.getColumnIndex(Inspiration);
            String data="Name is "+cursor.getString(namePosition)+"\n Inspiration is "+cursor.getString(inspirationPostion);
            arrayList.add(data);
        }
        database.close();
        return arrayList;
    }
    public void updateRecord(long Position,String name,String inspiration)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, name);
        values.put(Inspiration, inspiration);
        String[] args = new String[]{++Position+""};
        long id=db.update(table, values, "_id=?", args);
        Toast.makeText(context,"id is "+id,Toast.LENGTH_LONG).show();
        db.close();

    }

    public void deleteSpecificRecord(long id)
    {
        SQLiteDatabase database=this.getReadableDatabase();
        String[] args = new String[]{++id+""};
        database.delete(table,"_id=?",args);
        database.close();
    }
    public void deleteAll()
    {
        SQLiteDatabase database=this.getReadableDatabase();
        database.delete(table,null,null);
        database.close();
    }

    public ArrayList searchRecoid(String searchData)
    {
        ArrayList arrayList=new ArrayList();
        String Select="Select *from "+table+" where "+NAME+"='"+searchData+"'"+" OR "+Inspiration+"='"+searchData+"'";
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.rawQuery(Select,null);


        while(cursor.moveToNext())
        {
            int namePosition=cursor.getColumnIndex(NAME);
            int inspirationPostion=cursor.getColumnIndex(Inspiration);
            String data="Name is "+cursor.getString(namePosition)+"\n Inspiration is "+cursor.getString(inspirationPostion);
            arrayList.add(data);
        }
        database.close();
        return arrayList;
    }
}


