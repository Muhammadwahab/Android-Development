package com.example.engruzma.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public int teamAScore=0;
    public int teamBScore=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void incrementThreeTeamA(View view)
    {
        int three=3;
        teamAScore=teamAScore+three;
        display(teamAScore);
    }
    public void incrementTwoTeamA(View view)
    {
        int two=2;
        teamAScore=teamAScore+two;
        display(teamAScore);
    }
    public void incrementOneTeamA(View view)
    {
        int one=1;
        teamAScore=teamAScore+one;
        display(teamAScore);
    }
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.aCount);
        quantityTextView.setText("" + number);
    }
    public void incrementThreeTeamB(View view)
    {
        int three=3;
        teamBScore=teamBScore+three;
        displayB(teamBScore);

    }
    public void incrementTwoTeamB(View view)
    {
        int two=2;
        teamBScore=teamBScore+two;
        displayB(teamBScore);
    }
    public void incrementOneTeamB(View view)
    {
        int one=1;
        teamBScore=teamBScore+one;
        displayB(teamBScore);


    }
    private void displayB(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.bCount);
        quantityTextView.setText("" + number);
    }
    public void Reset(View view)
    {
        teamAScore=0;
        teamBScore=0;

        display(teamAScore);
        displayB(teamBScore);

    }

}
