package com.mobile.app.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mobile.app.R;

public class LinearEquation extends AppCompatActivity {
    EditText numAInput,numBInput,numCInput;
    TextView result;
    Button solve;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.linear_equations_layout);

        numAInput = findViewById(R.id.numA_input);
        numBInput = findViewById(R.id.numB_input);
        numCInput = findViewById(R.id.numC_input);
        result = findViewById(R.id.result);
        solve = findViewById(R.id.solve);

        solve.setOnClickListener(v -> solveBtnOnClick());


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @SuppressLint("SetTextI18n")
    public void solveBtnOnClick()
    {
        try{
            double a = Double.parseDouble(numAInput.getText().toString());
            double b = Double.parseDouble(numBInput.getText().toString());
            double c = Double.parseDouble(numCInput.getText().toString());

            double x = (c - b) / a;
            result.setText(Double.toString(x));

        } catch (Exception ignore) {
            result.setText("Invalid input");
        }
    }
}