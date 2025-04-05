package com.mobile.app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btnDiv,btnMul,btnMinus,btnPlus,btnDot,btnPercent,btnDel,btnSolve;
    private List<String> expression;
    private String currentNumber ="0";
    private TextView txtDisplay;
    private boolean isResult = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        init();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    private void init() {
        expression = new ArrayList<>();
        txtDisplay = findViewById(R.id.txtDisplay);

        // Digit buttons
        int[] digitIds = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};
        for (int i = 0; i < digitIds.length; i++) {
            int finalI = i;
            findViewById(digitIds[i]).setOnClickListener(v -> {
                if (isResult) {
                    currentNumber = "";
                    isResult = false;
                }
                currentNumber += finalI;
                updateDisplay();
            });
        }

        findViewById(R.id.btnDot).setOnClickListener(v -> {
            if (!currentNumber.contains(".")) {
                if (currentNumber.isEmpty()) currentNumber = "0";
                currentNumber += ".";
                updateDisplay();
            }
        });

        //Operator buttons
        findViewById(R.id.btnPlus).setOnClickListener(v -> addOperator("+"));
        findViewById(R.id.btnMinus).setOnClickListener(v -> addOperator("-"));
        findViewById(R.id.btnMul).setOnClickListener(v -> addOperator("*"));
        findViewById(R.id.btnDiv).setOnClickListener(v -> addOperator("/"));
        findViewById(R.id.btnPercent).setOnClickListener(v -> {
            if (!currentNumber.isEmpty()) {
                expression.add(currentNumber);
                expression.add("/");
                expression.add("100");
                currentNumber = "";
                updateDisplay();
            }
            isResult = false;
        });

        //Delete button
        findViewById(R.id.btnDel).setOnClickListener(v -> {
            currentNumber = "0";
            expression.clear();
            updateDisplay();
            isResult = true;
        });

        //Solve button
        findViewById(R.id.btnSolve).setOnClickListener(v -> {
            if (!currentNumber.isEmpty()) expression.add(currentNumber);
            double result = solveExpression(new ArrayList<>(expression));
            txtDisplay.setText(String.valueOf(result));
            expression.clear();
            currentNumber = String.valueOf(result);
        });
    }

    private void addOperator(String op) {

        if (!currentNumber.isEmpty()) {
            expression.add(currentNumber);
            currentNumber = "";
        }
        expression.add(op);
        isResult = false;
        updateDisplay();

    }

    private void updateDisplay() {
        StringBuilder sb = new StringBuilder();
        for (String s : expression) sb.append(s);
        sb.append(currentNumber);
        txtDisplay.setText(sb.toString());
    }

    private double solveExpression(List<String> expr) {
        try {
            if (expr.size() < 3)
            {
                isResult = true;
                return Double.parseDouble(expr.get(0));
            }
            // First pass: *, /
            for (int i = 0; i < expr.size(); i++) {
                if (expr.get(i).equals("*") || expr.get(i).equals("/")) {
                    double left = Double.parseDouble(expr.get(i - 1));
                    double right = Double.parseDouble(expr.get(i + 1));
                    double result = expr.get(i).equals("*") ? left * right : left / right;
                    expr.set(i - 1, String.valueOf(result));
                    expr.remove(i); // remove operator
                    expr.remove(i); // remove right operand
                    i -= 1; // move back one step
                }
            }

            // Second pass: +, -
            for (int i = 0; i < expr.size(); i++) {
                if (expr.get(i).equals("+") || expr.get(i).equals("-")) {
                    double left = Double.parseDouble(expr.get(i - 1));
                    double right = Double.parseDouble(expr.get(i + 1));
                    double result = expr.get(i).equals("+") ? left + right : left - right;
                    expr.set(i - 1, String.valueOf(result));
                    expr.remove(i); // remove operator
                    expr.remove(i); // remove right operand
                    i -= 1;
                }
            }
            isResult =true;
            return Double.parseDouble(expr.get(0));
        } catch (Exception e) {
            currentNumber = "0";
            updateDisplay();
            isResult = true;
            return 0.0;
        }

    }

}