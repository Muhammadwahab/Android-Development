package com.example.abdull.fetchscore;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.telephony.SmsManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.provider.Browser;


public class MainActivity extends AppCompatActivity {

    EditText sms;
    EditText number;


    Button button,BookmarkActivity,startDen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permition);

        BookmarkActivity = (Button) findViewById(R.id.book);
        startDen = (Button) findViewById(R.id.startDen);

        View.OnClickListener onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,permisionActivity.class);
                startActivity(intent);

            }
        };



        startDen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,permisionActivity.class);
                startActivity(intent);
            }
        });
//


    }

//    private BroadcastReceiver receiver = new BroadcastReceiver() {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String message = null;
//
//            switch (getResultCode()) {
//                case Activity.RESULT_OK:
//                    message = "Message sent!";
//                    break;
//                case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
//                    message = "Error. Message not sent.";
//                    break;
//                case SmsManager.RESULT_ERROR_NO_SERVICE:
//                    message = "Error: No service.";
//                    break;
//                case SmsManager.RESULT_ERROR_NULL_PDU:
//                    message = "Error: Null PDU.";
//                    break;
//                case SmsManager.RESULT_ERROR_RADIO_OFF:
//                    message = "Error: Radio off.";
//                    break;
//            }
//
//            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
//        }
//    };

    private void sendSms(String phonenumber, String message, boolean isBinary) {
        SmsManager manager = SmsManager.getDefault();

        SmsManager.getDefault().sendTextMessage("03471218967", null,
                "Hello SMS!", null, null);
    }


//        sms = (EditText) findViewById(R.id.sms);
//        number = (EditText) findViewById(R.id.number);
//
//        button = (Button) findViewById(R.id.send);
//
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
//
//                smsIntent.setData(Uri.parse("sms:" + number.getText().toString()));
//                smsIntent.putExtra("sms_body", sms.getText().toString());
//                startActivity(smsIntent);
//
//
//                try {
//
//                    PendingIntent pendingIntent=PendingIntent.getBroadcast(getApplicationContext(),0,new Intent("SMS_SENT"),0);
//
//
//                    SmsManager.getDefault().sendTextMessage("034718967", null,
//                            "Hello SMS!", pendingIntent, null);
//                } catch (Exception e) {
//                    AlertDialog.Builder alertDialogBuilder = new
//                            AlertDialog.Builder(getApplicationContext());
//
//                }
//            }
//        });
//
//        registerReceiver(receiver, new IntentFilter("SMS_SENT"));  // SMS_SENT is a constant
}
