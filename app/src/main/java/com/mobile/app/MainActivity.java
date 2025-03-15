package com.mobile.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mobile.app.activities.ResultActivity;

import java.util.Optional;


public class MainActivity extends AppCompatActivity {

    EditText numA, numB, numC;
    Button solveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //setContentView(R.layout.sum_constrain_layout);
        setContentView(R.layout.main_layout);

        numA = findViewById(R.id.numA);
        numB = findViewById(R.id.numB);
        numC = findViewById(R.id.numC);

        solveBtn = findViewById(R.id.solveBtn);
        solveBtn.setOnClickListener(v -> solveBtnOnClick());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            return insets;
        });
    }

    private void solveBtnOnClick()
    {

        String strA = numA.getText().toString();
        String strB = numB.getText().toString();
        String strC = numC.getText().toString();

        if(strA.isEmpty() || strB.isEmpty() || strC.isEmpty())
        {
            return;
        }

        String result = solve(strA,strB, strC);

        Bundle bundle = new Bundle();
        bundle.putString("result", result);
        switchIntent(ResultActivity.class, bundle);
    }

    private String solve(String strA, String strB, String strC)
    {
        double a = Double.parseDouble(strA);
        double b = Double.parseDouble(strB);
        double c = Double.parseDouble(strC);
        double delta = b * b - 4 * a * c;

        String result;
        if(delta < 0)
        {
            result = "Không tồn tại nghiệm";
        } else if (delta ==0 ) {
            result = "Nghiệm kép: " + (-b / (2 * a));
        }else {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);
            result = "Phương trình có 2 nghiệm: " + x1 + " và " + x2;
        }
        return result;
    }

    private void switchIntent(Class<?> T, Bundle bundle)
    {
        Intent intent = new Intent(this, T);
        if(bundle != null)
        {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
}