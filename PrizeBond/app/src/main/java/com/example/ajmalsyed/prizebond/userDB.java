package com.example.ajmalsyed.prizebond;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Ajmal Syed on 6/8/2017.
 */
public class userDB extends SQLiteOpenHelper {

    static final String DATABASE_NAME="User.db";
    public final String TABLE_NAME="User";
    public final String ID_COLUMN = BaseColumns._ID;
    public final String NAME_COLUMN = "Name";
    public final String PHONE_NUMBERS ="Cell_Number" ;
    public final String DRAW_NUMBER_COLUMN = "Drawnumber";
Context context;


    final String Create_DB="CREATE TABLE "+TABLE_NAME +"( "+
            ID_COLUMN +" INTEGER PRIMARY KEY," +
            NAME_COLUMN+" TEXT NOT NULL,"+
            PHONE_NUMBERS+" TEXT NOT NULL,"+
            DRAW_NUMBER_COLUMN+" TEXT NOT NULL );";

    public userDB(Context context) {
        super(context, DATABASE_NAME,null,1);
        this.context=context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_DB);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXIST "+TABLE_NAME);
        onCreate(db);
    }



    public boolean Insertion(String name,String number,String drawnumber){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues value=new ContentValues();
        value.put(NAME_COLUMN,name);
        value.put(PHONE_NUMBERS,number);
        value.put(DRAW_NUMBER_COLUMN,drawnumber);
        long datainsert=db.insert(TABLE_NAME,null,value);

        if(datainsert==-1){
            return false;
        }
        else{
            return true;
        }
    }


    public ArrayList DataDisplay() {


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME, null);
        int name = cursor.getColumnIndex(NAME_COLUMN);
        int number = cursor.getColumnIndex(PHONE_NUMBERS);
        int drawnumber = cursor.getColumnIndex(DRAW_NUMBER_COLUMN);


        ArrayList<UserInstance> instanceArrayList = new ArrayList<UserInstance>();

            while (cursor.moveToNext()) {
                String mName = cursor.getString(name);
                  String mNumber = cursor.getString(number);
                    String mdrawnumber = cursor.getString(drawnumber);
                       instanceArrayList.add(new UserInstance(mName, mNumber, mdrawnumber));
            }
                           db.close();
                              return instanceArrayList;
    }




    public void DeleteSelectedlist(long id){

        SQLiteDatabase db=this.getWritableDatabase();
        String [] args=new String[]{++id +""};

         long deleted=db.delete(TABLE_NAME," _id=?",args);
        db.close();



          //  Toast.makeText(context," selected item deleted id is = "+deleted ,Toast.LENGTH_SHORT).show();



    }

    public void deleteAlldata(){
        SQLiteDatabase db=this.getReadableDatabase();
          db.delete(TABLE_NAME,null,null);
            Toast.makeText(context," delete All items",Toast.LENGTH_SHORT).show();

    }

//"+DRAW_NUMBER_COLUMN+","+PHONE_NUMBERS+"
   public Cursor Search(String number){

       SQLiteDatabase db=this.getReadableDatabase();
       String searchquery="Select * from "+TABLE_NAME+" where "+DRAW_NUMBER_COLUMN+"= '"+number+"'";
       Cursor cursor=db.rawQuery(searchquery,null);

       return cursor;



//
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor cursor = db.rawQuery("Select "+DRAW_NUMBER_COLUMN +
//                " from " + TABLE_NAME +
//                " where "+DRAW_NUMBER_COLUMN+" ='" + number +"'" , null);
//
//        String draw;
//        if(cursor.moveToFirst()) {
//
//            do {
//
//                draw = cursor.getString(3);
//
//                Toast.makeText(context, "Selected number is " + draw, Toast.LENGTH_LONG).show();
//
//
//            } while (cursor.moveToNext());
//        }
//            else
//            {
//                Toast.makeText(context, "Selected number is not found", Toast.LENGTH_LONG).show();
//            }
//
//    }

}}
