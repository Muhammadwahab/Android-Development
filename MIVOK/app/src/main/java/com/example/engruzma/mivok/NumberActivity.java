package com.example.engruzma.mivok;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class NumberActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.word_list);
       // getActionBar().setDisplayHomeAsUpEnabled(true);

        // to do <code></code>

        ArrayList<word> numbers=new ArrayList();
        numbers.add(new word("One","Lutti",R.drawable.number_one));
        numbers.add(new word("two","otiiko",R.drawable.number_two));
        numbers.add(new word("three","tolookosu",R.drawable.number_three));
        numbers.add(new word("four","oyyisa",R.drawable.number_four));
        numbers.add(new word("five","massokka",R.drawable.number_five));
        numbers.add(new word("six","temmokka",R.drawable.number_six));
        numbers.add(new word("seven","kenekaku",R.drawable.number_seven));
        numbers.add(new word("eight","kawinta",R.drawable.number_eight));
        numbers.add(new word("nine","w0'e",R.drawable.number_nine));
        numbers.add(new word("ten","na'aacha",R.drawable.number_ten));



        // display txt view

        LinearLayout linearLayout=(LinearLayout)findViewById(R.id.root);

        wordAdapter<word> stringArrayAdapter=new wordAdapter<>(this,numbers,R.color.category_numbers);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(stringArrayAdapter);
//        ArrayAdapter<String> stringArrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,numbers);
//        GridView gridView=(GridView)findViewById(R.id.gridview);
//        gridView.setAdapter(stringArrayAdapter);









    }
}
