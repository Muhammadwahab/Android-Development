package com.example.abdull.prizebond;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    Button login,signup;
    TextView data;
    String Url="http://savings.gov.pk/draws/01-09-2016,Rs.40000.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=(Button)findViewById(R.id.loginID);
        signup=(Button)findViewById(R.id.signUp);
        data=(TextView) findViewById(R.id.loadData);
        new prizeNumbers().execute();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),sigup.class);
                startActivity(intent);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
            }
        });

    }
    public class prizeNumbers extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] params) {
            StringBuilder bondData=new StringBuilder();
            int count=0;
            try {
                URL url=new URL(Url);
                URLConnection urlConnection=url.openConnection();
                HttpURLConnection httpURLConnection= (HttpURLConnection) urlConnection;
                InputStream inputStream=httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String line;
                while ((line=bufferedReader.readLine())!=null)
                {
                    count++;
                    bondData.append("\n"+line+"\t"+count);
                }
            } catch (MalformedURLException e) {
                Log.d("prizeNumber","Exception is "+e);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bondData;
        }

        @Override
        protected void onPostExecute(Object o) {
            StringBuffer stringBuffer=new StringBuffer(o.toString());


            data.setText(o.toString());

            super.onPostExecute(o);
        }
    }
}
