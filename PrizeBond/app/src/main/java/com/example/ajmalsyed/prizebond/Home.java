package com.example.ajmalsyed.prizebond;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Home extends AppCompatActivity {

    Button loaddata,userrecord,adduser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        loaddata= (Button) findViewById(R.id.dataload_button);
        adduser= (Button) findViewById(R.id.adduser_button);
        userrecord= (Button) findViewById(R.id.userrecord_button);


        userrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, userlist.class);
                startActivity(intent);
            }
        });

        loaddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Networking  networking=new Networking();
                networking.execute();
            }
        });


        adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }


    public class Networking extends AsyncTask {

        Context context;
        public void setContext(Context context){
            this.context=context;
        }

        public Context getContext()
        { return context;
        }





        String url="https://prizebondapi.herokuapp.com/search?num=2512151&draw=200";
        StringBuffer stringBuffer;

        @Override
        protected Object doInBackground(Object[] params) {
            try {
                URL u = new URL(url);
                stringBuffer=new StringBuffer();
                HttpURLConnection httpURLConnection=(HttpURLConnection) u.openConnection();
                InputStream inputStream=httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);


                String line;

                while ((line=bufferedReader.readLine())!=null){

                    stringBuffer.append(line);

                    Log.d("","string value is "+ stringBuffer);

                }

            } catch (MalformedURLException e) {
                Log.d("ASynTAskNetwork","Error Log is "+e);}
            catch (IOException e) {
                e.printStackTrace();
            }


            return stringBuffer;

        }

        @Override
        protected void onPostExecute(Object o) {

            Cursor cursor;
            String datavalue=o.toString();

            JSONTokener jsonTokener=new JSONTokener(datavalue);
            try {
                JSONObject jsonObject= (JSONObject) jsonTokener.nextValue();

                String getdrawnumber=jsonObject.getString("drawNumber");

                Log.d("Networking","retrieve value is "+getdrawnumber);



            userDB userDB=new userDB(getApplicationContext());
             cursor=userDB.Search(getdrawnumber);
            if (cursor.moveToNext()){
                String name=cursor.getString(1);
                 String cellnumber=cursor.getString(2);
                  Log.d("","cell number is "+cellnumber);
                  String num=cursor.getString(3);
                   Log.d(""," drawnumber is "+num);

                String message="Congratulation "+name+" You win Prize Bond and your draw number is "+getdrawnumber;

                sendSms(cellnumber,message,true);
               // Toast.makeText(getApplicationContext(),"msg send to "+ cellnumber+" draw number is"+getdrawnumber,Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
                builder.setTitle("CONGRATULATION");
                builder.setIcon(R.drawable.congratulationicon);
                builder.setMessage("Winner of Prize Bond is "+ name +" and Draw Number is "+getdrawnumber+" \tTHANK YOU");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog option = builder.create();
                option.show();
            }
                else{

                AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
                builder.setTitle("SORRY");
                builder.setIcon(R.drawable.sorryicon);
                builder.setMessage("Winner is not found from UserList please try next time. \nTHANK YOU");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog option = builder.create();
                option.show();

               // Toast.makeText(getApplicationContext(),"draw number is"+getdrawnumber,Toast.LENGTH_SHORT).show();


            }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            super.onPostExecute(o);
        }

        private void sendSms(String phonenumber, String message, boolean isBinary) {
            SmsManager manager = SmsManager.getDefault();

            SmsManager.getDefault().sendTextMessage(phonenumber, null,
                    message, null, null);
           // Toast.makeText(getApplicationContext(),"msg send ",Toast.LENGTH_SHORT).show();
        }

    }

}

