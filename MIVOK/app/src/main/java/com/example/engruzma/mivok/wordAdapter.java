package com.example.engruzma.mivok;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Engr.Uzma on 09/08/2016.
 */
public class wordAdapter<Name> extends ArrayAdapter {

    private int Colour;




    public wordAdapter(Activity Context, ArrayList<word> Words, int color)
    {
        super(Context,0,Words);
        Colour=color;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        word currentAndroidFlavor = (word) getItem(position);

        // cange color in linear Layout

        View linearLayout=(LinearLayout)listItemView.findViewById(R.id.textcolour);
        int colour= ContextCompat.getColor(getContext(),Colour);
        linearLayout.setBackgroundColor(colour);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.listOne);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        nameTextView.setText(currentAndroidFlavor.getDefualtTranslation());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView numberTextView = (TextView) listItemView.findViewById(R.id.listTwo);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        numberTextView.setText(currentAndroidFlavor.getMivokTranslation());

        if(currentAndroidFlavor.hasImage())
        {
            ImageView imageView=(ImageView) listItemView.findViewById(R.id.ImageView);
            imageView.setImageResource(currentAndroidFlavor.getImageID());
        }
        else
        {
            ImageView imageView=(ImageView) listItemView.findViewById(R.id.ImageView);
            imageView.setVisibility(View.GONE);
        }






        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
