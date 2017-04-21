package com.example.android.pets.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.pets.Data.PetContract.PetsEntry;

/**
 * Created by Engr.Uzma on 09/09/2016.
 */
public class PetHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "pets.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INEG_TYPE = " integer";
    private static final String NOT_NULL = " not null";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES ="create table " +PetsEntry.TABLE_NAME+
            "(_id integer primary key autoincrement" +COMMA_SEP+
            PetsEntry.COLUM_NAME+TEXT_TYPE+NOT_NULL+COMMA_SEP+PetsEntry.COLUM_BREED+TEXT_TYPE+COMMA_SEP+PetsEntry.COLUM_GENDER
            +INEG_TYPE+NOT_NULL+COMMA_SEP+PetsEntry.COLUM_WEIGHT+INEG_TYPE+" default 0)";


     public PetHandler(Context context)
    {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
