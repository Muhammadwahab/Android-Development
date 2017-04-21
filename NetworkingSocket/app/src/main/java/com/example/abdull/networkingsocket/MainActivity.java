package com.example.abdull.networkingsocket;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abdull.networkingsocket.Socket.socketAsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {


    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final socketAsyncTask socketAsyncTask=new socketAsyncTask(getApplicationContext());

        button=(Button)findViewById(R.id.LoadData);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                socketAsyncTask.execute("www.fb.com");

            }
        });

    }

//    private class socketAsyncTask extends AsyncTask {
//        Socket socket=null;
//        TextView loadData;
//
//        private static final String HOST = "api.geonames.org";
//        Context context;
//
//        // Get your own user name at http://www.geonames.org/login
//        private static final String USER_NAME = "taharushain";
//        private static final String HTTP_GET_COMMAND = "GET /earthquakesJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&username="
//                + USER_NAME
//                + " HTTP/1.1"
//                + "Connection: close" + "\n\n";
//
//        private static final String TAG = "HttpGet";
//
//
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected Object doInBackground(Object[] params) {
//            String Address=params[0].toString();
//            String Data = null;
//
//            try {
//                socket = new Socket(HOST, 80);
//                PrintWriter pw = new PrintWriter(new OutputStreamWriter(
//                        socket.getOutputStream()), true);
//                pw.println(HTTP_GET_COMMAND);
//                Data= readStream(socket.getInputStream());
//                return Data;
//
//            } catch (IOException e) {
//                Log.d("ON BackGround", String.valueOf(e));
//            }
//
//            return Data;
//        }
//
//        @Override
//        protected void onPostExecute(Object o) {
//
//            String Data= (String) o;
//            Toast.makeText(getApplicationContext(),"Data"+Data,Toast.LENGTH_LONG).show();
//
////        loadData=(TextView) ((Activity)context).findViewById(R.id.NetworkData);
////        loadData.setText(Data);
//        }
//
//        @Override
//        protected void onProgressUpdate(Object[] values) {
//            super.onProgressUpdate(values);
//        }
//
//        String readStream(InputStream in) throws IOException {
//            String Data="";
//            InputStreamReader inputStreamReader=new InputStreamReader(in);
//            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
//
//            Data+=bufferedReader.readLine();
//
//            return Data;
//        }
//    }


}
