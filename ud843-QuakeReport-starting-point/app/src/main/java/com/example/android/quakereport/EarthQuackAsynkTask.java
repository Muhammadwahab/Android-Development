package com.example.android.quakereport;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import static com.example.android.quakereport.EarthquakeActivity.LOG_TAG;
import static com.example.android.quakereport.QueryUtils.USGS_REQUEST_URL;

/**
 * Created by Engr.Uzma on 04/09/2016.
 */
public class EarthQuackAsynkTask extends AsyncTaskLoader<ArrayList<ContentClass>> {
    public EarthQuackAsynkTask(Context context) {
        super(context);
    }

    @Override
    public ArrayList<ContentClass> loadInBackground() {

        Log.v("OnLoading","Activity in BackGround Thread");
        URL url = createUrl(QueryUtils.USGS_REQUEST_URL);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = "";
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            // TODO Handle the IOException
        }

        // Extract relevant fields from the JSON response and create an {@link ContentClass} object
        ArrayList<ContentClass> contentClass = QueryUtils.extractContentClasss(jsonResponse);

        // Return the {@link ContentClass} object as the result fo the {@link TsunamiAsyncTask}
        return contentClass;
    }
    /**
     * Returns new URL object from the given string URL.
     */
    private URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            Log.e(LOG_TAG, "Error with creating URL", exception);
            return null;
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();

            Thread.sleep(3000);



            if(urlConnection.getResponseCode()==200)
            {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
            else
            {
                Log.e("404 error","invalid response");

            }


        } catch (IOException e) {
            // TODO: Handle the exception
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // function must handle java.io.IOException here
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Return an {@link ContentClass} object by parsing out information
     * about the first earthquake from the input earthquakeJSON string.
     */

}
