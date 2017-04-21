/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import static com.example.android.quakereport.QueryUtils.USGS_REQUEST_URL;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<ContentClass>> {

    ListView earthquakeListView;
    EarthQyickArrayAdapter<ContentClass> adapter;


    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private TextView mEmptyStateTextView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.earthquake_activity);

         earthquakeListView = (ListView) findViewById(R.id.list);
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        earthquakeListView.setEmptyView(mEmptyStateTextView);

        Log.v("Oncreate","Activity Create And Start");


        // connectivity check

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null  && networkInfo.isConnected()) {
            // fetch data
            Log.v("Network connect","Connction availale");
            getSupportLoaderManager().initLoader(1, null, this).forceLoad();
        } else {
            // display error
            Log.v("Network Error","Connction Not Avaible yet pleas try again");
            Toast.makeText(this,"Network error",Toast.LENGTH_LONG).show();
        }

        // for loader

        // this comment is for asynk task
//        TsunamiAsyncTask task = new TsunamiAsyncTask();
//        task.execute();


        // this comment is used for default behaviour of app

        // create ContentClass



        // Create a fake list of earthquake locations.
      //  final ArrayList<ContentClass> earthquakes = QueryUtils.extractContentClasss();


//        // Find a reference to the {@link ListView} in the layout
//        ListView earthquakeListView = (ListView) findViewById(R.id.list);
//
//        // Create a new {@link ArrayAdapter} of earthquakes
//        EarthQyickArrayAdapter<ContentClass> adapter = new EarthQyickArrayAdapter<ContentClass>(
//                this, earthquakes);
//
//        // Set the adapter on the {@link ListView}
//        // so the list can be populated in the user interface
//        earthquakeListView.setAdapter(adapter);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public Loader<ArrayList<ContentClass>> onCreateLoader(int id, Bundle args) {

        Log.v("OncreateLoader","Activity Create and loader Start");
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String minMagnitude = sharedPrefs.getString(
                getString(R.string.settings_min_magnitude_key),
                getString(R.string.settings_min_magnitude_default));

        // for order preference
        String orderBy = sharedPrefs.getString(
                getString(R.string.settings_order_by_key),
                getString(R.string.settings_order_by_default)
        );

        Uri baseUri = Uri.parse(QueryUtils.USGS_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("format", "geojson");
        uriBuilder.appendQueryParameter("limit", "50");
        uriBuilder.appendQueryParameter("minmag", minMagnitude);
        uriBuilder.appendQueryParameter("eventtype","earthquake");
        uriBuilder.appendQueryParameter("orderby", orderBy);
        Log.v("OncreateLoader",String.valueOf(uriBuilder.toString()));
        QueryUtils.setUrl(uriBuilder.toString());
        return new EarthQuackAsynkTask(this);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<ContentClass>> loader, final ArrayList<ContentClass> data) {
        // Find a reference to the {@link ListView} in the layout


        ProgressBar progressBar=(ProgressBar)findViewById(R.id.loading_spinner);
        progressBar.setVisibility(View.GONE);
        Log.v("OnLoad Finished","Activity Finish and update UI");
        mEmptyStateTextView.setText("No Earth Quick Find");




        // Create a new {@link ArrayAdapter} of earthquakes
         adapter = new EarthQyickArrayAdapter<ContentClass>(
                EarthquakeActivity.this, data);


        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface





            earthquakeListView.setAdapter(adapter);

            earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    ContentClass contentClass=data.get(position);

                    Uri webpage = Uri.parse(contentClass.getUrl());
                    Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }




                }
            });





    }

    @Override
    public void onLoaderReset(Loader<ArrayList<ContentClass>> loader) {

        Log.v("OnReset","Activity Reset to Default");

    }

    private class TsunamiAsyncTask extends AsyncTask<URL, Void, ArrayList<ContentClass>> {

        @Override
        protected ArrayList<ContentClass> doInBackground(URL... urls) {
            // Create URL object
            URL url = createUrl(USGS_REQUEST_URL);

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
         * Update the screen with the given earthquake (which was the result of the
         * {@link TsunamiAsyncTask}).
         */
        @Override
        protected void onPostExecute(ArrayList<ContentClass> arrayOfDAta) {
            if (arrayOfDAta == null) {
                return;
            }

            updateUi(arrayOfDAta);
        }

        private void updateUi(final ArrayList<ContentClass> arrayOfDAta) {
            // Find a reference to the {@link ListView} in the layout
            ListView earthquakeListView = (ListView) findViewById(R.id.list);

            // Create a new {@link ArrayAdapter} of earthquakes
            EarthQyickArrayAdapter<ContentClass> adapter = new EarthQyickArrayAdapter<ContentClass>(
                    EarthquakeActivity.this, arrayOfDAta);

            // Set the adapter on the {@link ListView}
            // so the list can be populated in the user interface
            earthquakeListView.setAdapter(adapter);

            earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    ContentClass contentClass=arrayOfDAta.get(position);

                    Uri webpage = Uri.parse(contentClass.getUrl());
                    Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }




                }
            });

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
}
