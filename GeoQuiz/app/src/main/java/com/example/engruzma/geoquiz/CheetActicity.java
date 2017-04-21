package com.example.engruzma.geoquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.engruzma.geoquiz.R.string.True;

public class CheetActicity extends AppCompatActivity {

    TextView TextAser;
    Button chekAnser;
    private boolean mAnser;
    private boolean mIsCheater=false;
    private String Cheter_Index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheet_acticity);

        mAnser=getIntent().getBooleanExtra("Answer",false);
        TextAser=(TextView)findViewById(R.id.textAnswer);
        chekAnser =(Button)findViewById(R.id.trueKey);

        if(savedInstanceState!=null)
        {

            mIsCheater=savedInstanceState.getBoolean(Cheter_Index);
            if(mIsCheater)
            {
                setAnswerShownResult(mIsCheater);
            }
        }

        chekAnser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Cheet Activity","Cheet Display");
                TextAser.setText("Answer is "+mAnser);
                setAnswerShownResult(mIsCheater);


            }
        });

    }
    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra("Cheet", isAnswerShown);
        setResult(RESULT_OK, data);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean(Cheter_Index,mIsCheater);
        Log.e("onCheet","on Save");

    }
}
