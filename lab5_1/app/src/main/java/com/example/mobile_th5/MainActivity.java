package com.example.mobile_th5;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    String[] titles = {
            "Android Cupcake", "Android Donut", "Android Eclair",
            "Android Froyo", "Android Gingerbread", "Android Honeycomb"
    };

    String[] versions = {
            "Version 1.5", "Version 1.6", "Version 2.0",
            "Version 2.2", "Version 2.3", "Version 3.0"
    };

    int[] images = {
            R.drawable.cupcake, R.drawable.donut, R.drawable.eclair,
            R.drawable.frozen, R.drawable.gingerbread, R.drawable.honeycomb
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_1);

        ListView listView = findViewById(R.id.listView);
        CustomAdapter adapter = new CustomAdapter(this, titles, versions, images);
        listView.setAdapter(adapter);
    }
}
