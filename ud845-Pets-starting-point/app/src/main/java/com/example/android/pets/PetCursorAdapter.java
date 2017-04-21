package com.example.android.pets;

import android.app.LauncherActivity;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import com.example.android.pets.Data.PetContract;

/**
 * Created by Engr.Uzma on 24/09/2016.
 */
public class PetCursorAdapter extends CursorAdapter {
    public PetCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.items,viewGroup,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

       try {
           TextView pet=(TextView)view.findViewById(R.id.pet);
           TextView breed=(TextView)view.findViewById(R.id.breed);

           pet.setText(cursor.getString(cursor.getColumnIndexOrThrow(PetContract.PetsEntry.COLUM_NAME)));
           breed.setText(cursor.getString(cursor.getColumnIndexOrThrow(PetContract.PetsEntry.COLUM_BREED)));

       }catch (Exception e)
       {
           Log.e("Catalog",""+e);
       }
       finally {

       }

    }
}
