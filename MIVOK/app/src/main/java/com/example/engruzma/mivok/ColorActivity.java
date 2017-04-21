package com.example.engruzma.mivok;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
      //  getActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<word> numbers=new ArrayList();
        numbers.add(new word("One","Lutti"));
        numbers.add(new word("two","otiiko"));
        numbers.add(new word("three","tolookosu"));
        numbers.add(new word("four","oyyisa"));
        numbers.add(new word("five","massokka"));
        numbers.add(new word("six","temmokka"));
        numbers.add(new word("seven","kenekaku"));
        numbers.add(new word("eight","kawinta"));
        numbers.add(new word("nine","w0'e"));
        numbers.add(new word("ten","na'aacha"));



        // display txt view

        LinearLayout linearLayout=(LinearLayout)findViewById(R.id.root);

        wordAdapter<word> stringArrayAdapter=new wordAdapter<>(this,numbers,R.color.category_colors);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(stringArrayAdapter);
    }
}
