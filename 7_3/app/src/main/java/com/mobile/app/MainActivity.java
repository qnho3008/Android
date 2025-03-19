package com.mobile.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mobile.app.activities.LinearEquation;
import com.mobile.app.activities.Sum_ConstrainLayout;
import com.mobile.app.activities.Sum_LinearLayout;


public class MainActivity extends AppCompatActivity {

    Button constrainSum,linearSum,linearEqua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //setContentView(R.layout.sum_constrain_layout);
        setContentView(R.layout.main_layout);

        linearEqua = findViewById(R.id.linearEqua);
        linearSum = findViewById(R.id.solve);
        constrainSum = findViewById(R.id.constrainSum);

        linearEqua.setOnClickListener(v -> linearEquation());
        linearSum.setOnClickListener(v -> linearSumLayout());
        constrainSum.setOnClickListener(v -> constrainSumLayout());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            return insets;
        });
    }

    public void linearSumLayout()
    {
        switchIntent(Sum_LinearLayout.class);
    }

    public void constrainSumLayout()
    {
        switchIntent(Sum_ConstrainLayout.class);
    }

    public void linearEquation()
    {
        switchIntent(LinearEquation.class);
    }

    private void switchIntent(Class<?> T)
    {
        Intent intent = new Intent(this, T);
        startActivity(intent);
    }


}