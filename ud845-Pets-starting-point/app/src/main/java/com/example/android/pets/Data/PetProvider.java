package com.example.android.pets.Data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.example.android.pets.CatalogActivity;
import com.example.android.pets.Data.PetContract.PetsEntry;

import static android.R.attr.id;

/**
 * Created by Engr.Uzma on 22/09/2016.
 */
public class PetProvider extends ContentProvider {

    /** URI matcher code for the content URI for the pets table */
    private static final int PETS = 100;
    PetHandler mDbHelper;

    /** URI matcher code for the content URI for a single pet in the pets table */
    private static final int PET_ID = 101;

    /**
     * UriMatcher object to match a content URI to a corresponding code.
     * The input passed into the constructor represents the code to return for the root URI.
     * It's common to use NO_MATCH as the input for this case.
     */
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    // Static initializer. This is run the first time anything is called from this class.
    static {
        // The calls to addURI() go here, for all of the content URI patterns that the provider
        // should recognize. All paths added to the UriMatcher have a corresponding code to return
        // when a match is found.

        // TODO: Add 2 content URIs to URI matcher

        sUriMatcher.addURI(PetContract.CONTENT_AUTHORITY, PetContract.PetsEntry.TABLE_NAME,PETS);
        sUriMatcher.addURI(PetContract.CONTENT_AUTHORITY, PetContract.PetsEntry.TABLE_NAME+"/#",PET_ID);
    }
    @Override
    public boolean onCreate() {
         mDbHelper = new PetHandler(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        // Get readable database
        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        // This cursor will hold the result of the query
        Cursor cursor=null;

        // Figure out if the URI matcher can match the URI to a specific code
        int match = sUriMatcher.match(uri);
        switch (match) {
            case PETS:
                // For the PETS code, query the pets table directly with the given
                // projection, selection, selection arguments, and sort order. The cursor
                // could contain multiple rows of the pets table.
                // TODO: Perform database query on pets table
                cursor=database.query(
                        PetsEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder



                );
                break;
            case PET_ID:
                // For the PET_ID code, extract out the ID from the URI.
                // For an example URI such as "content://com.example.android.pets/pets/3",
                // the selection will be "_id=?" and the selection argument will be a
                // String array containing the actual ID of 3 in this case.
                //
                // For every "?" in the selection, we need to have an element in the selection
                // arguments that will fill in the "?". Since we have 1 question mark in the
                // selection, we have 1 String in the selection arguments' String array.
                selection = "_id" + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };

                // This will perform a query on the pets table where the _id equals 3 to return a
                // Cursor containing that row of the table.
                cursor = database.query(PetsEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PETS:
                return insertPet(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }
    private Uri insertPet(Uri uri, ContentValues values) {

        // TODO: Insert a new pet into the pets database table with the given ContentValues

        // Once we know the ID of the new row in the table,
        // return the new URI with the ID appended to the end of it

        // Check that the name is not null
        String name = values.getAsString(PetsEntry.COLUM_NAME);
        Log.e("Insert","NAME"+name);
        String Bread = values.getAsString(PetsEntry.COLUM_BREED);
        Log.e("Insert","BREAD"+Bread);
        if (name.equalsIgnoreCase("")) {
           // throw new IllegalArgumentException("Pet requires a name");
            return ContentUris.withAppendedId(uri,1213);

        }
        else if(Bread.equalsIgnoreCase(""))
        {
            return ContentUris.withAppendedId(uri,1213);
        }
        else
        {
            SQLiteDatabase database = mDbHelper.getWritableDatabase();
            long ww= database.insert(PetsEntry.TABLE_NAME,null,values);
            if(ww==-1)
            {

                Log.e("Insert","Error"+id);

            }
            else
            {
                Log.e("Insert","Error"+id);
            }

        }
        getContext().getContentResolver().notifyChange(uri,null);

        return ContentUris.withAppendedId(uri, id);

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Get writeable database
        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PETS:
                // Delete all rows that match the selection and selection args
                getContext().getContentResolver().notifyChange(uri,null);
                return database.delete(PetsEntry.TABLE_NAME, selection, selectionArgs);
            case PET_ID:
                // Delete a single row given by the ID in the URI
                selection = PetsEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                getContext().getContentResolver().notifyChange(uri,null);
                return database.delete(PetsEntry.TABLE_NAME, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection,
                      String[] selectionArgs) {
       // final int match = sUriMatcher.match(uri);
//        switch (match) {
//            case PETS:
//                return updatePet(uri, contentValues, selection, selectionArgs);
//            case PET_ID:
//                // For the PET_ID code, extract out the ID from the URI,
//                // so we know which row to update. Selection will be "_id=?" and selection
//                // arguments will be a String array containing the actual ID.
//
//            default:
//                throw new IllegalArgumentException("Update is not supported for " + uri);
//        }
        selection = PetsEntry._ID + "=?";

        return updatePet(uri, contentValues, selection, selectionArgs);
    }
    private int updatePet(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        // TODO: Update the selected pets in the pets database table with the given ContentValues

        long ww=0;
        String name = values.getAsString(PetsEntry.COLUM_NAME);
        Log.e("Insert","NAME"+name);
        String Bread = values.getAsString(PetsEntry.COLUM_BREED);
        Log.e("Insert","BREAD"+Bread);
        if (TextUtils.isEmpty(name) && name!=null) {
            // throw new IllegalArgumentException("Pet requires a name");
            return 1213;
        }
        else if(TextUtils.isEmpty(Bread) &&Bread!=null)
        {
            return 1213;
        }
        else
        {
            SQLiteDatabase database = mDbHelper.getWritableDatabase();
             ww= database.update(PetsEntry.TABLE_NAME,values,selection,selectionArgs);
            if(ww==1)
            {

                Log.e("Update","Update Successfully"+id);

            }
            else
            {
                Log.e("Update","Error in updatation"+ww);
            }


        }



        // TODO: Return the number of rows that were affected
        getContext().getContentResolver().notifyChange(uri,null);
        return (int)ww;
    }
}
