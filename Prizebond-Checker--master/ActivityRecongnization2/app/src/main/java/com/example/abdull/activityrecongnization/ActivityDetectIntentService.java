package com.example.abdull.activityrecongnization;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import java.util.ArrayList;

/**
 * Created by abdull on 7/16/17.
 */

public class ActivityDetectIntentService extends IntentService {
    String TAG="Activity Detect IS";
    public ActivityDetectIntentService() {
        super("Activity Detect IS");
    }




    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        ActivityRecognitionResult result=ActivityRecognitionResult.extractResult(intent);
        Intent localIntent=new Intent(CONSTANTS.Brodcast_Action);

        ArrayList<DetectedActivity> detectedActivities= (ArrayList<DetectedActivity>) result.getProbableActivities();
        localIntent.putExtra(CONSTANTS.Activity_Extra,detectedActivities);
        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);

    }
}
