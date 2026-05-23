package com.example.calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView result, tvExpression;
    String input = "";
    double num1, num2;
    String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
        tvExpression = findViewById(R.id.tvExpression);

        int[] nums = {
                R.id.b0, R.id.b1, R.id.b2, R.id.b3, R.id.b4, R.id.b5, R.id.b6, R.id.b7, R.id.b8, R.id.b9
        };

        for (int id : nums) {
            Button b = findViewById(id);
            b.setOnClickListener(v -> {
                input += b.getText().toString();
                result.setText(input);
                updateExpression();
            });
        }

        findViewById(R.id.bdot).setOnClickListener(v -> {
            if (!input.contains(".")) {
                input += ".";
                result.setText(input);
                updateExpression();
            }
        });

        findViewById(R.id.bc).setOnClickListener(v -> {
            input = "";
            num1 = 0;
            num2 = 0;
            operator = "";
            result.setText("0");
            tvExpression.setText("");
        });

        findViewById(R.id.badd).setOnClickListener(v -> setOperator("+"));
        findViewById(R.id.bsub).setOnClickListener(v -> setOperator("-"));
        findViewById(R.id.bmul).setOnClickListener(v -> setOperator("*"));
        findViewById(R.id.bdiv).setOnClickListener(v -> setOperator("/"));

        findViewById(R.id.bequal).setOnClickListener(v -> calculate());
    }

    private void updateExpression() {
        if (operator.isEmpty()) {
            tvExpression.setText(input);
        } else {
            tvExpression.setText(num1 + " " + operator + " " + input);
        }
    }

    private void setOperator(String op) {
        if (!input.isEmpty()) {
            num1 = Double.parseDouble(input);
            operator = op;
            input = "";
            tvExpression.setText(num1 + " " + operator);
        }
    }

    private void calculate() {
        if (!input.isEmpty() && !operator.isEmpty()) {
            num2 = Double.parseDouble(input);
            tvExpression.setText(num1 + " " + operator + " " + num2 + " =");
            double ans = 0;
            switch (operator) {
                case "+":
                    ans = num1 + num2;
                    break;
                case "-":
                    ans = num1 - num2;
                    break;
                case "*":
                    ans = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        ans = num1 / num2;
                    } else {
                        result.setText("Error");
                        input = "";
                        operator = "";
                        return;
                    }
                    break;
            }
            result.setText(String.valueOf(ans));
            input = String.valueOf(ans);
            operator = "";
        }
    }
}
