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
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;
import com.example.android.pets.Data.PetContract;
import com.example.android.pets.Data.PetContract.PetsEntry;
import com.example.android.pets.Data.PetHandler;

import static android.content.ContentUris.parseId;

/**
 * Allows user to create a new pet or edit an existing one.
 */
public class EditorActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    String name,breed,weight;
    int gender;

    private boolean mPetHasChanged = false;

    /** EditText field to enter the pet's name */
    private EditText mNameEditText;

    /** EditText field to enter the pet's breed */
    private EditText mBreedEditText;

    /** EditText field to enter the pet's weight */
    private EditText mWeightEditText;

    /** EditText field to enter the pet's gender */
    private Spinner mGenderSpinner;

    // content View For Update Value

    ContentValues updateValue;

    // pet Uri

    // edit clcik id contain in uri
    Uri uri;

    /**
     * Gender of the pet. The possible values are:
     * 0 for unknown gender, 1 for male, 2 for female.
     */
    private int mGender = 0;
    private String GenderText="Unknown";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        // for  detail view
        Intent intent=getIntent();
        uri=intent.getData();

        if(intent==null || uri==null)
        {
            setTitle(getString(R.string.editor_activity_title_new_pet));
        }
        else
        {

            setTitle("Edit Pet");
            setTitleColor(R.color.colorAccent);
             uri=intent.getData();
            getLoaderManager().initLoader(1,null,this);
            Log.e("Edit","Loader Initialize With Intent "+intent);

        }






        // Find all relevant views that we will need to read user input from
        mNameEditText = (EditText) findViewById(R.id.edit_pet_name);
        mBreedEditText = (EditText) findViewById(R.id.edit_pet_breed);
        mWeightEditText = (EditText) findViewById(R.id.edit_pet_weight);
        mGenderSpinner = (Spinner) findViewById(R.id.spinner_gender);

        mNameEditText.setOnTouchListener(mTouchListener);
        mBreedEditText.setOnTouchListener(mTouchListener);
        mWeightEditText.setOnTouchListener(mTouchListener);
        mGenderSpinner.setOnTouchListener(mTouchListener);

        setupSpinner();
    }

     View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mPetHasChanged = true;
            return false;
        }
    };

    private void savePreviousData() {

        // save previous data of pet

        name=mNameEditText.getText().toString();
        breed=mBreedEditText.getText().toString();
        weight=mWeightEditText.getText().toString();
        gender=mGenderSpinner.getSelectedItemPosition();

    }

    /**
     * Setup the dropdown spinner that allows the user to select the gender of the pet.
     */
    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_gender_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mGenderSpinner.setAdapter(genderSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.gender_male))) {
                        mGender = PetsEntry.MALE; // Male
                        GenderText="Male";
                    } else if (selection.equals(getString(R.string.gender_female))) {
                        mGender = PetsEntry.FEMALE;; // Female
                        GenderText="FeMale";
                    } else {
                        mGender = PetsEntry.UNKNOWN; // Unknown
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGender = 0; // Unknown
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    public void Delete()
    {

        if(uri==null)
        {
            Toast toast=Toast.makeText(this,"You Can not Delete Pet From While Insertion",Toast.LENGTH_LONG);
            toast.show();
        }
        else
        {

            String[] mSelectionArgs= {String.valueOf(ContentUris.parseId(uri))};
            String Selection=PetsEntry._ID+"=?";
            int DeleteRows=getContentResolver().delete(uri,Selection,mSelectionArgs);
            Toast toast=Toast.makeText(this,"Delete Pet",Toast.LENGTH_LONG);
            toast.show();
        }








    }

    private void savePet()
    {
        PetHandler petHandler=new PetHandler(this);
        SQLiteDatabase sqLiteDatabase=petHandler.getWritableDatabase();
        ContentValues values=new ContentValues();




        if(isCompareData())
        {
            values.put(PetsEntry.COLUM_NAME,mNameEditText.getText().toString());
            values.put(PetsEntry.COLUM_GENDER,mGender);
            values.put(PetsEntry.COLUM_BREED,mBreedEditText.getText().toString());
            values.put(PetsEntry.COLUM_WEIGHT,mWeightEditText.getText().toString());


            // insert in database

            //long newRowId = sqLiteDatabase.insert(PetsEntry.TABLE_NAME,null,values);
            long newRowId;
            Uri uri=getContentResolver().insert(PetsEntry.CONTENT_URI,values);

            if(parseId(uri)==1213)
            {
                Toast toast=Toast.makeText(this,"Pet Name Required",Toast.LENGTH_LONG);
                toast.show();
            }
            else if(parseId(uri)==1214)
            {
                Toast toast=Toast.makeText(this,"Bread Required",Toast.LENGTH_LONG);
                toast.show();
            }
            else
            {
                Toast toast=Toast.makeText(this,"Pet Save in database",Toast.LENGTH_LONG);
                toast.show();
            }
            Log.v("Row ID",""+3);

        }
        else
        {

            String mSelectionClause = "_id" + "=?";
            String[] mSelectionArgs= {String.valueOf(ContentUris.parseId(uri))};


            int mRowsUpdated = getContentResolver().update(
                    PetContract.BASE_CONTENT_URI,   // the user dictionary content URI
                    updateValue ,                   // the columns to update
                    mSelectionClause   ,                 // the column to select on
                    mSelectionArgs        // the value to compare to
            );

            if(mRowsUpdated==1)
            {
                Toast toast=Toast.makeText(this,"Pet Update in database",Toast.LENGTH_LONG);
                toast.show();
            }
            else if(mRowsUpdated==1213){

                Toast toast=Toast.makeText(this,"Please Fill Relevent filleds",Toast.LENGTH_LONG);
                toast.show();
                Log.e("Insert","Updated Rows ID"+mRowsUpdated);

            }
            else {

                Toast toast=Toast.makeText(this,"Update Failed",Toast.LENGTH_LONG);
                toast.show();
                Log.e("Insert","Updated Rows ID"+mRowsUpdated);

            }




        }






    }

    private boolean isCompareData() {

        boolean match=false;
        boolean localMatch=true;
        updateValue=new ContentValues();

            if(uri==null)
            {
                localMatch=true;
            }
            else
            {
                if(name.equalsIgnoreCase(mNameEditText.getText().toString()))
                {
                    match=true;

                }
                else
                {
                    updateValue.put(PetsEntry.COLUM_NAME,mNameEditText.getText().toString());
                    match=false; // return true if all matches
                    localMatch=false; // return false if all matches

                }
                if(breed.equalsIgnoreCase(mBreedEditText.getText().toString()))
                {
                    match=true;

                }
                else
                {
                    updateValue.put(PetsEntry.COLUM_BREED,mBreedEditText.getText().toString());
                    match=false;
                    localMatch=false;
                }
                if(weight.equalsIgnoreCase(mWeightEditText.getText().toString()))
                {
                    match=true;
                }
                else
                {
                    updateValue.put(PetsEntry.COLUM_WEIGHT,mWeightEditText.getText().toString());
                    match=false;
                    localMatch=false;
                }
                if(gender==mGenderSpinner.getSelectedItemPosition())
                {
                    match=true;
                }
                else
                {
                    updateValue.put(PetsEntry.COLUM_GENDER,mGender);
                    match=false;
                    localMatch=false;
                }
            }

            return localMatch;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                savePet();
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                // Do nothing for now
                DialogInterface.OnClickListener DeleteButton =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // User clicked "Discard" button, navigate to parent activity.
                                Delete();
                                finish();

                            }
                        };
                DailogSetting(DeleteButton,"want to Delete A Pet","Delete","Cancel");
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // If the pet hasn't changed, continue with navigating up to parent activity
                // which is the {@link CatalogActivity}.
                if (!mPetHasChanged) {
                    NavUtils.navigateUpFromSameTask(EditorActivity.this);
                    return true;
                }

                // Otherwise if there are unsaved changes, setup a dialog to warn the user.
                // Create a click listener to handle the user confirming that
                // changes should be discarded.
                DialogInterface.OnClickListener discardButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // User clicked "Discard" button, navigate to parent activity.
                                NavUtils.navigateUpFromSameTask(EditorActivity.this);
                            }
                        };

                // Show a dialog that notifies the user they have unsaved changes
                DailogSetting(discardButtonClickListener,"Want to discard Changing","Discard Changes","KeepChanges");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        Log.e("Edit","Loader onCreate");
        return new CursorLoader(
                this,   // Parent activity context
                uri,        // Table to query
                null,     // Projection to return
                null,            // No selection clause
                null,            // No selection arguments
                null             // Default sort order
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        Log.e("Edit","Loader finished");

        cursor.moveToNext();

        mNameEditText.setText(cursor.getString(cursor.getColumnIndex(PetsEntry.COLUM_NAME)));
        mBreedEditText.setText(cursor.getString(cursor.getColumnIndex(PetsEntry.COLUM_BREED)));

        // get Gender id
        int genderID=cursor.getInt(cursor.getColumnIndex(PetsEntry.COLUM_GENDER));

        switch (genderID)
        {
            case 0:
                mGenderSpinner.setSelection(genderID);
                break;
            case 1:
                mGenderSpinner.setSelection(genderID);
                break;
            case 2:
                mGenderSpinner.setSelection(genderID);
                break;
            default:
                Log.e("Edit Activity","Invalid Gender");


        }
        mWeightEditText.setText(""+cursor.getInt(cursor.getColumnIndex(PetsEntry.COLUM_WEIGHT)));
        savePreviousData();

        cursor.close();



    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
          //  loader.reset();


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

    @Override
    public void onBackPressed() {
        // If the pet hasn't changed, continue with handling back button press
        if (!mPetHasChanged) {
            super.onBackPressed();
            return;
        }

        // Otherwise if there are unsaved changes, setup a dialog to warn the user.
        // Create a click listener to handle the user confirming that changes should be discarded.
        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // User clicked "Discard" button, close the current activity.
                        finish();
                    }
                };

        // Show dialog that there are unsaved changes
        DailogSetting(discardButtonClickListener,"Want to discard Changing","Discard Changes","KeepChanges");
    }
}