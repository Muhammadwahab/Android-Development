package com.example.riaz.rate_batao;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.zip.Inflater;

/**
 * Created by riaz on 4/30/17.
 */

public class Dialogclass extends DialogFragment {
    LayoutInflater inflater;
    View dailogview;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
       inflater=getActivity().getLayoutInflater();
        dailogview=inflater.inflate(R.layout.dailog,null);
       // ImageView dailog_img=(ImageView) dailogview.findViewById(R.id.profile_picture);
        //TextView dailog_name=(TextView) dailogview.findViewById(R.id.profile_name);
        AlertDialog.Builder postdialog=new AlertDialog.Builder(getContext());
        postdialog.setTitle("Update Record");
        postdialog.setView(dailogview);
        postdialog.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return super.onCreateDialog(savedInstanceState);


    }
}
