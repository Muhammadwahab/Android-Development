package com.example.engruzma.fetchrecord;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         textView= (TextView) findViewById(R.id.textss);
        new fetchData().execute("http://savings.gov.pk/draws/01-12-2016,Rs.40000.txt");
    }

    public class fetchData extends AsyncTask<String,Integer,String>
    {


        @Override
        protected String doInBackground(String... urls) {
            try {
                return loadFromNetwork(urls[0]);
            } catch (IOException e) {
                return ("error");
            }        }
        protected void onProgressUpdate(Integer... progress) {

        }

        protected void onPostExecute(String result) {

            textView.setText(result);

        }
        /** Initiates the fetch operation. */
        private String loadFromNetwork(String urlString) throws IOException {
            InputStream stream = null;
            String str ="";

            try {
                stream = downloadUrl(urlString);
                str = readIt(stream, 900000);
            } finally {
                if (stream != null) {
                    stream.close();
                }
            }
            return str;
        }
        private InputStream downloadUrl(String urlString) throws IOException {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Start the query
            conn.connect();
            InputStream stream = conn.getInputStream();
            return stream;
        }
        private String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
            Reader reader = null;
            reader = new InputStreamReader(stream, "UTF-8");
            char[] buffer = new char[len];
            reader.read(buffer);
            return new String(buffer); // //
        }
    }
}
