package com.example.engruzma.ui;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


          button=(Button)findViewById(R.id.popButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(MainActivity.this, button);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.popup, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {


                        Toast.makeText(
                                MainActivity.this,
                                "You Clicked : " + item.getTitle(),
                                Toast.LENGTH_SHORT
                        ).show();
                        // Explicit Intent by specifying its class name
                        Intent i = new Intent(MainActivity.this, Drag_And_Drop.class);

// Starts TargetActivity
                        startActivity(i);
                        return true;
                    }
                });
                popup.show();
            }
        });

        TextView text=(TextView)findViewById(R.id.text);
        text.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(MainActivity.this,"long click",Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        LinearLayout relativeLayout=(LinearLayout)findViewById(R.id.view);

        switch(item.getItemId())
        {
            case R.id.blue:
                if(item.isChecked())
                {
                    item.setChecked(false);
                }
                else
                {
                    item.setChecked(true);
                    relativeLayout.setBackgroundColor(Color.BLUE);
                }
            case R.id.red:
                if(item.isChecked())
                {
                    item.setChecked(false);
                }
                else
                {
                    item.setChecked(true);
                    relativeLayout.setBackgroundColor(Color.RED);
                }



        }
        return super.onOptionsItemSelected(item);
    }
}
