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

public class Sum_ConstrainLayout extends AppCompatActivity {

    EditText numAInput,numBInput;
    Button solve;
    TextView result;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.sum_constrain_layout);

        numAInput = findViewById(R.id.numA_input);
        numBInput = findViewById(R.id.numB_input);
        solve = findViewById(R.id.solve);
        result = findViewById(R.id.result);

        solve.setOnClickListener(v -> sumBtnOnClick());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.constraint), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @SuppressLint("SetTextI18n")
    public void sumBtnOnClick()
    {
            try{
                double a = Double.parseDouble(numAInput.getText().toString());
                double b = Double.parseDouble(numBInput.getText().toString());
                double sum = a + b;
                result.setText(Double.toString(sum));

            } catch (Exception ignore) {
                result.setText("Invalid input");
            }
    }
}