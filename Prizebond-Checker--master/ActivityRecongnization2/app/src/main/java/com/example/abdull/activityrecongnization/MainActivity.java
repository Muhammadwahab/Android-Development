package com.example.abdull.activityrecongnization;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.location.Location;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.ActivityRecognition;
import com.google.android.gms.location.DetectedActivity;
import com.google.android.gms.location.LocationListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener,LocationListener,ResultCallback<Status> {

    GoogleApiClient googleApiClient;
    TextView mstatus;
    Button requestUpdateButton,RemoveUpdateButton;
    ActivityReconginationBrodcastReciever activityReconginationBrodcastReciever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestUpdateButton= (Button) findViewById(R.id.request_activity_updates_button);
        RemoveUpdateButton= (Button) findViewById(R.id.remove_activity_updates_button);

        mstatus= (TextView) findViewById(R.id.detectedActivities);
        activityReconginationBrodcastReciever=new ActivityReconginationBrodcastReciever();

        buildGoogleApiClient();

    }

    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        googleApiClient.disconnect();
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(activityReconginationBrodcastReciever);
        super.onPause();
    }

    @Override
    protected void onResume() {
        LocalBroadcastManager.getInstance(this).registerReceiver(activityReconginationBrodcastReciever,new IntentFilter(CONSTANTS.Brodcast_Action));
        super.onResume();
    }

    @Override
    public void onResult(Status status) {
        if (status.isSuccess()) {
            Log.e( "MAin Activity", "Successfully added activity detection.");

        } else {
            Log.e("MAin Activity", "Error adding or removing activity detection: " + status.getStatusMessage());
        }

    }

    public class ActivityReconginationBrodcastReciever extends BroadcastReceiver
    {
        private String BrodcastTag="BrodcastReceriver";

        @Override
        public void onReceive(Context context, Intent intent) {

            ArrayList<DetectedActivity> activities=intent.getParcelableArrayListExtra(CONSTANTS.Activity_Extra);

            String StatusText="";

            for (DetectedActivity thisActivity:activities) {

                StatusText+=getActivityString(thisActivity.getType())+thisActivity.getConfidence()+"%\n";
            }
            mstatus.setText(StatusText);

        }
        public  String getActivityString(int ActivityCode)
    {
        Resources resources=getResources();
        switch(ActivityCode) {
            case DetectedActivity.IN_VEHICLE:
                return resources.getString(R.string.in_vehicle);
            case DetectedActivity.ON_BICYCLE:
                return resources.getString(R.string.on_bicycle);
            case DetectedActivity.ON_FOOT:
                return resources.getString(R.string.on_foot);
            case DetectedActivity.RUNNING:
                return resources.getString(R.string.running);
            case DetectedActivity.STILL:
                return resources.getString(R.string.still);
            case DetectedActivity.TILTING:
                return resources.getString(R.string.tilting);
            case DetectedActivity.UNKNOWN:
                return resources.getString(R.string.unknown);
            case DetectedActivity.WALKING:
                return resources.getString(R.string.walking);
            default:
                return resources.getString(R.string.unidentifiable_activity, ActivityCode);
        }
    }
    }

    private void buildGoogleApiClient() {

        googleApiClient=new GoogleApiClient.Builder(this)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(ActivityRecognition.API)
                .build();

    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {


    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }
    public void requestActivityUpdatesButtonHandler(View view) {
        if (!googleApiClient.isConnected()) {
            Toast.makeText(this, getString(R.string.not_connected),
                    Toast.LENGTH_SHORT).show();
            return;
        }
        ActivityRecognition.ActivityRecognitionApi.requestActivityUpdates(
                googleApiClient,
                CONSTANTS.DETECTION_INTERVAL_IN_MILLISECONDS,
                getActivityDetectionPendingIntent()
        ).setResultCallback(this);
        requestUpdateButton.setEnabled(false);
        RemoveUpdateButton.setEnabled(true);

    }

    public void removeActivityUpdatesButtonHandler(View view) {
        if (!googleApiClient.isConnected()) {
            Toast.makeText(this, getString(R.string.not_connected), Toast.LENGTH_SHORT).show();
            return;
        }
        // Remove all activity updates for the PendingIntent that was used to request activity
        // updates.
        ActivityRecognition.ActivityRecognitionApi.removeActivityUpdates(
                googleApiClient,
                getActivityDetectionPendingIntent()
        ).setResultCallback(this);
        requestUpdateButton.setEnabled(true);
        RemoveUpdateButton.setEnabled(false);
    }
    private PendingIntent getActivityDetectionPendingIntent() {
        Intent intent = new Intent(this, ActivityDetectIntentService.class);

        // We use FLAG_UPDATE_CURRENT so that we get the same pending intent back when calling
        // requestActivityUpdates() and removeActivityUpdates().
        return PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

}
