package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.R.attr.name;

/**
 * Created by Engr.Uzma on 23/08/2016.
 */
public class EarthQyickArrayAdapter<T> extends ArrayAdapter {

    public EarthQyickArrayAdapter(Activity Context, ArrayList<ContentClass> Content) {
        super(Context, 0, Content);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        ContentClass contentClass = (ContentClass) getItem(position);

        TextView text;


        text = (TextView) listItemView.findViewById(R.id.magnitude);

        // set colour
        GradientDrawable magnitudeCircle = (GradientDrawable) text.getBackground();
        int colour = getMagnitudeColor(contentClass.getMagnitude());
        magnitudeCircle.setColor(colour);

        text.setText("" + formatMagnitude(contentClass.getMagnitude()));
        // placed text one
        text = (TextView) listItemView.findViewById(R.id.placedone);

        String offset = contentClass.getPlaced();

        int last = offset.lastIndexOf("of");
        String offsetLocation, PrimaryLocation;
        if (last == -1) {
            offsetLocation = "NearOF";
            last = 0;
        } else {
            last = last + 3;
            offsetLocation = offset.substring(0, last);

        }
        PrimaryLocation = offset.substring(last, offset.length());

        text.setText("" + offsetLocation);
        // placed text two
        text = (TextView) listItemView.findViewById(R.id.placedtwo);

        offset = contentClass.getPlaced();

        text.setText("" + PrimaryLocation);

        // date of earth quick

        Date dateObject = new Date(contentClass.gettimeInMilliSecond());

        text = (TextView) listItemView.findViewById(R.id.date);
        text.setText("" + formatDate(dateObject));

        // time set

        text = (TextView) listItemView.findViewById(R.id.time);
        text.setText("" + formatTime(dateObject));


        return listItemView;
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);

    }
}
