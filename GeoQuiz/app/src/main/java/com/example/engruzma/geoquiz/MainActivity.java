package com.example.engruzma.geoquiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageButton next,prev;
    TextView textView;
    Questions questions[]={
            new Questions(R.string.one,false),
            new Questions(R.string.two,false),
            new Questions(R.string.three,true),
            new Questions(R.string.four,true)

    };
    private  Button True,False,cheet;
    private String Tag="MainACtivity";
    private int index=0;
    private String Key_Index;
    private String Cheter_Index;
    private int REQUESTCODE=0;
    private boolean mIsCheater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(Tag,"On Create Call");
        textView=(TextView) findViewById(R.id.textQuest) ;

        if(savedInstanceState!=null)
        {
            index=savedInstanceState.getInt(Key_Index);
            mIsCheater=savedInstanceState.getBoolean(Cheter_Index);
        }
        updateQuestion();

        True =(Button)findViewById(R.id.trueKey);
        False =(Button)findViewById(R.id.FalseKey);
        next =(ImageButton)findViewById(R.id.next);
        prev =(ImageButton)findViewById(R.id.prev);
        cheet =(Button)findViewById(R.id.cheet);

        True.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(Tag,"True Succeed");
                checkAnser(true);
            }
        });
        False.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(Tag,"false succed");
                checkAnser(false);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(Tag,"next Apear");
                updateQuestion();
            }
        });
        cheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(Tag,"Cheet button");
                Intent intent=getIntent(MainActivity.this,questions[index].isAns());

                startActivityForResult(intent,REQUESTCODE);

            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(Tag,"Prev Apear");
                if(index==0)
                {
                    index=questions.length-1;
                    textView.setText(questions[index].getTextID());
                }
                else
                {
                    textView.setText(questions[--index].getTextID());

                    Log.e(Tag,"Prev Apear else"+index);
                }

            }
        });

    }
    public void updateQuestion()
    {
        index=index%questions.length;
        textView.setText(questions[index++].getTextID());
    }
    public void checkAnser(boolean check)
    {
        int id=0;

       if(mIsCheater)
       {
           Toast.makeText(MainActivity.this,"Chor ka bacha he to",Toast.LENGTH_LONG).show();
       }
       else {
           if(check==questions[index].isAns())
           {
               Toast.makeText(MainActivity.this,R.string.Correct,Toast.LENGTH_LONG).show();
           }
           else
           {
               Toast.makeText(MainActivity.this,R.string.Wrong,Toast.LENGTH_LONG).show();

           }
       }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Key_Index,index);
        outState.putBoolean(Cheter_Index,mIsCheater);
        Log.e(Tag,"save states");

    }
    private Intent getIntent(Context conetext, boolean anser)
    {
        Intent intent=new Intent(conetext,CheetActicity.class);
        intent.putExtra("Answer",anser);
        return intent;

    }
    @Override    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;        }
        if (requestCode == REQUESTCODE) {
            Log.e(Tag,"Request code"+REQUESTCODE);
            if (data == null) {
                return;
            }
            mIsCheater = wasAnswerShown(data);
        }
    }
    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra("Cheet", false);
    }
}
