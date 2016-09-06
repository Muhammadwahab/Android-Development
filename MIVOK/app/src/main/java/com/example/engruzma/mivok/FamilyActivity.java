package com.example.engruzma.mivok;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        //getActionBar().setDisplayHomeAsUpEnabled(true);


        ArrayList<word> numbers=new ArrayList();
        numbers.add(new word("father","әpә"));
        numbers.add(new word("mother","әṭa"));
        numbers.add(new word("son","angsi"));
        numbers.add(new word("daughter","tune"));
        numbers.add(new word("older brother","massokka"));
        numbers.add(new word("six","temmokka"));
        numbers.add(new word("seven","kenekaku"));
        numbers.add(new word("eight","kawinta"));
        numbers.add(new word("nine","w0'e"));
        numbers.add(new word("ten","na'aacha"));



        // display txt view

        LinearLayout linearLayout=(LinearLayout)findViewById(R.id.root);

        wordAdapter<word> stringArrayAdapter=new wordAdapter(this,numbers,R.color.category_family);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(stringArrayAdapter);
    }
}
