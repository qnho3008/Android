package com.example.lab6;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    String[] student_name = {
            "Nguyen Van A", "Tran Van B"
    };

    String[] mssv = {
            "111111", "222222"
    };

    int[] images = {
            R.drawable.a111111, R.drawable.b222222
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        ListView listView = findViewById(R.id.listView);
        CustomAdapter adapter = new CustomAdapter(this, student_name, mssv, images);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            intent.putExtra("name", student_name[position]);
            intent.putExtra("mssv", mssv[position]);
            intent.putExtra("image", images[position]);
            startActivity(intent);
        });

    }
}
