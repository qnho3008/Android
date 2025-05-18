package com.example.lab6;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    ImageView profileImage;
    TextView profileName, profileMssv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        profileImage = findViewById(R.id.profile_image);
        profileName = findViewById(R.id.profile_name);
        profileMssv = findViewById(R.id.profile_mssv);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String mssv = intent.getStringExtra("mssv");
        int imageResId = intent.getIntExtra("image", R.drawable.ic_launcher_foreground);

        profileName.setText(name);
        profileMssv.setText(mssv);
        profileImage.setImageResource(imageResId);
    }
}
