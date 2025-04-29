package com.example.mobile_th5;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    String[] titles = {
            "Orange", "Apple", "Strawberry", "Banana",
            "Lemon", "Kiwi", "Pear", "Cherry"
    };

    String[] calories = {
            "47 Calories", "52 Calories", "33 Calories",
            "89 Calories", "29 Calories", "61 Calories",
            "57 Calories", "50 Calories"
    };

    int[] images = {
            R.drawable.orange, R.drawable.apple, R.drawable.strawberry,
            R.drawable.banana, R.drawable.lemon, R.drawable.kiwi,
            R.drawable.pear, R.drawable.cherry
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_2);

        ListView listView = findViewById(R.id.listView);
        CustomAdapter adapter = new CustomAdapter(this, titles, calories, images);
        listView.setAdapter(adapter);
    }
}
