/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.pets;



import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.*;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.example.android.pets.Data.PetContract;
import com.example.android.pets.Data.PetHandler;
import com.example.android.pets.Data.PetContract.PetsEntry;

import static android.widget.Toast.makeText;


/**
 * Displays list of pets that were entered and stored in the app.
 */
public class CatalogActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    PetCursorAdapter petCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
        ListView listView=(ListView)findViewById(R.id.list);
        View emptyView = findViewById(R.id.empty_view);
        listView.setEmptyView(emptyView);
         petCursorAdapter=new PetCursorAdapter(CatalogActivity.this,null);
        listView.setAdapter(petCursorAdapter);


        // on clcik list item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Intent intent=new Intent(CatalogActivity.this,EditorActivity.class);
                Uri uri=PetsEntry.CONTENT_URI;
                uri= ContentUris.withAppendedId(uri,id);
                intent .setData(uri);
                startActivity(intent);

            }
        });

        getLoaderManager().initLoader(0,null,this);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        displayDatabaseInfo();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:

                PetHandler petHandler=new PetHandler(this);
                SQLiteDatabase sqLiteDatabase=petHandler.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put(PetsEntry.COLUM_NAME,"Dummy");
                values.put(PetsEntry.COLUM_GENDER,0);
                values.put(PetsEntry.COLUM_BREED,"Algoritham");
                values.put(PetsEntry.COLUM_WEIGHT,22);
                Uri uri=getContentResolver().insert(PetsEntry.CONTENT_URI,values);

                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for nowDialogInterface.OnClickListener DeleteButton =
                DialogInterface.OnClickListener DeleteButton =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // User clicked "Discard" button, navigate to parent activity.
                                Delete();


                            }
                        };
                DailogSetting(DeleteButton,"want to Delete A Pet","Delete","Cancel");
        }
        return super.onOptionsItemSelected(item);
    }
    private void DailogSetting(
            DialogInterface.OnClickListener discardButtonClickListener,String ask,String TakingAction,String Ignore) {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the positive and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(ask);
        builder.setPositiveButton(TakingAction, discardButtonClickListener);
        builder.setNegativeButton(Ignore, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Keep editing" button, so dismiss the dialog
                // and continue editing the pet.
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the pets database.
     */
    private void displayDatabaseInfo() {
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        PetHandler mDbHelper = new PetHandler(this);

        // Create and/or open a database to read from it


        // Perform this raw SQL query "SELECT * FROM pets"
        // to get a Cursor that contains all rows from the pets table.
       // Cursor cursor = db.rawQuery("SELECT * FROM " + PetsEntry.TABLE_NAME, null);

        //String Projection[]={}
//        Cursor cursor=db.query(
//                PetsEntry.TABLE_NAME,
//                null,
//                null,
//                null,
//                null,
//                null,
//                null
//
//
//
//        );



        Cursor cursor=getContentResolver().query(PetsEntry.CONTENT_URI,null,null,null,null);
        Log.e("Catalog","Cursor"+cursor.toString());
        try {
            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // pets table in the database).

           // Log.e("Catalog","List view"+listView.toString());
            // Find the ListView which will be populated with the pet data


            // Find and set empty view on the ListView, so that it only shows when the list has 0 items.


//            displayView.setText("Number of Pet in the Table : " + cursor.getCount()+"\n\n");


//            displayView.append("\n"+PetsEntry.COLUM_NAME+"-"+PetsEntry.COLUM_BREED+"-"+PetsEntry.COLUM_GENDER+"-"+PetsEntry.COLUM_WEIGHT+"-");

//            while (cursor.moveToNext())
//            {
//                displayView.append("\n");
//                displayView.append(cursor.getInt(0)+" "+cursor.getString(1)+" "+cursor.getString(2)+" "+
//
//                        cursor.getInt(3)+" "+cursor.getInt(4)+" "
//                );
//            }


        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
          //  cursor.close();
        }
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(
                this,   // Parent activity context
                PetsEntry.CONTENT_URI,        // Table to query
                null,     // Projection to return
                null,            // No selection clause
                null,            // No selection arguments
                null             // Default sort order
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

     petCursorAdapter.swapCursor(cursor);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        petCursorAdapter.swapCursor(null);
    }



    public void Delete()
    {





            int DeleteRows=getContentResolver().delete(PetsEntry.CONTENT_URI,null,null);

        if(DeleteRows>0)
        {
            Toast toast=Toast.makeText(this,"Delete Pet",Toast.LENGTH_LONG);
            toast.show();
        }
        else
        {
            Toast toast=Toast.makeText(this,"All Pets Delete",Toast.LENGTH_LONG);
            toast.show();
        }










    }


}
