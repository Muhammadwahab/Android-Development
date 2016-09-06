package com.example.engruzma.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private int Count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_layout);
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.count);
        quantityTextView.setText("" + number);
    }

    public void submit(View view) {

        displayPrice(Count*10);
    }

    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.prize);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    public void increment(View view) {
        this.Count++;
        display(this.Count);
    }
    public void Decrement(View view) {
        this.Count--;
        display(this.Count);
    }

}
