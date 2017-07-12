package com.example.ajmalsyed.prizebond;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.ajmalsyed.prizebond.R.id.savebutton;

public class MainActivity extends AppCompatActivity {


EditText name,number, edrawnumber;

    Button userrecord, saveuser,dataload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        name = (EditText) findViewById(R.id.etname);
        number = (EditText) findViewById(R.id.etnumber);
        edrawnumber = (EditText) findViewById(R.id.etdrawnumber);

        saveuser = (Button) findViewById(R.id.savebutton);

        saveuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddData();
                finish();
            }
        });
    }



    public void AddData() {
        userDB userDB = new  userDB(this);
        if (name == null) {
            Toast.makeText(MainActivity.this, "fill all fields", Toast.LENGTH_SHORT).show();
        } else {
            boolean data = userDB.Insertion(name.getText().toString(),
                    number.getText().toString(),
                    edrawnumber.getText().toString());
            if (data) {
                Toast.makeText(MainActivity.this, "data insert successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "data not insert ", Toast.LENGTH_SHORT).show();
            }
        }
    }}
























//        searchdata = (Button) findViewById(R.id.Searchdata);
//        numbertext = (TextView) findViewById(R.id.numbertext);
//        winORlose = (TextView) findViewById(R.id.winORlose);
//        searchtext = (EditText) findViewById(R.id.searchfield);


//        searchdata.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Networking networking = new Networking();
//                networking.execute();
//            }
//        });


//    class Networking extends AsyncTask{
//
//        String url="https://prizebondapi.herokuapp.com/search?num=2512151&draw=200";
//        StringBuffer stringBuffer;
//
//        @Override
//        protected Object doInBackground(Object[] params) {
//    try {
//        URL u = new URL(url);
//        stringBuffer=new StringBuffer();
//        HttpURLConnection httpURLConnection=(HttpURLConnection) u.openConnection();
//        InputStream inputStream=httpURLConnection.getInputStream();
//        InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
//        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
//
//
//        String line;
//
//        while ((line=bufferedReader.readLine())!=null){
//
//            stringBuffer.append(line);
//
//        }
//
//    } catch (MalformedURLException e) {
//            Log.d("ASynTAskNetwork","Error Log is "+e);}
//    catch (IOException e) {
//        e.printStackTrace();
//    }
//
//
//            return stringBuffer;
//        }
//
//        @Override
//        protected void onPostExecute(Object o) {
//
//            String datavalue=o.toString();
//
//            JSONTokener jsonTokener=new JSONTokener(datavalue);
//
//            try {
//                JSONObject jsonObject= (JSONObject) jsonTokener.nextValue();
//
//
//
//          String number=jsonObject.getString("drawNumber");
//
//                String gettext=searchtext.getText().toString().trim();
//
//               if(number.equals(gettext)) {
//                numbertext.setText(number);
//               // String result=jsonObject.getString("win");
//                    winORlose.setText("You Win");
//
//               }
//
//                else{
//                    numbertext.setText("Number not found");
//                    winORlose.setText(" You Lose DUDE");
//                }
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            super.onPostExecute(o);
//        }
//    }

