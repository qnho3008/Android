package com.mobile.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mobile.app.MainActivity;
import com.mobile.app.R;


public class ResultActivity  extends AppCompatActivity {

    TextView resultView;
    Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //setContentView(R.layout.sum_constrain_layout);
        setContentView(R.layout.second_layout);
        resultView = findViewById(R.id.result);
        backBtn = findViewById(R.id.backBtn);

        backBtn.setOnClickListener(v -> backBtnOnClick());
        showResult(getIntent().getExtras());


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.second), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            return insets;
        });
    }

    private void showResult(Bundle bundle)
    {
        if(bundle == null)
        {
            return;
        }
        String result = bundle.getString("result");
        resultView.setText(result);
    }

    private void backBtnOnClick()
    {
        switchIntent(MainActivity.class);
    }

    private void switchIntent(Class<?> T)
    {
        Intent intent = new Intent(this, T);
        startActivity(intent);
    }

}
