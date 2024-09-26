package com.example.calculatorhwandroid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Double first, second;
    private Boolean isOperationClick;
    private String currentOperation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView = findViewById(R.id.textView);

        Intent intent = new Intent(MainActivity.this, MainActivitySecond.class);
        Intent intent2 = new Intent(MainActivity.this, MainActivitySecond.class);
        String text = textView.getText().toString();
        findViewById(R.id.secondWindowButton).setOnClickListener( view -> {
            startActivity(intent);
             intent2.putExtra("text", text);


        });
    }



    public void onNumberClick(View view) {
        String text = ((MaterialButton) view).getText().toString();
        if (text.equals("AC")) {
            textView.setText("0");
            first = 0.0;
            second = 0.0;
        } else if (text.equals(".")) {
            textView.append(".");
        } else if (textView.getText().toString().equals("0") || isOperationClick){
            textView.setText(text);
        } else {
            textView.append(text);
        }
        isOperationClick = false;
    }

    @SuppressLint("SetTextI18n")
    public void onOperationClick(View view) {
        int id = view.getId();

        if (id == R.id.plusButton) {
            currentOperation = "plus";
            first = Double.valueOf(textView.getText().toString());
            isOperationClick = true;
        } else if (id == R.id.minusButton) {
            currentOperation = "minus";
            first = Double.valueOf(textView.getText().toString());
            isOperationClick = true;
        } else if (id == R.id.divideButton) {
            currentOperation = "divide";
            first = Double.valueOf(textView.getText().toString());
            isOperationClick = true;
        } else if (id == R.id.percentButton) {
            currentOperation = "percent";
            first = Double.valueOf(textView.getText().toString());
            isOperationClick = true;
        } else if (id == R.id.multiplyButton) {
            currentOperation = "multiply";
            first = Double.valueOf(textView.getText().toString());
            isOperationClick = true;
        } else if (id == R.id.equalButton) {
            findViewById(R.id.secondWindowButton).setVisibility(View.VISIBLE);
            second = Double.valueOf(textView.getText().toString());
            double result = 0.0;

            if (currentOperation.equals("plus")) {
                result = first + second;
            } else if (currentOperation.equals("minus")) {
                result = first - second;
            } else if (currentOperation.equals("multiply")) {
                result = first * second;
            } else if (currentOperation.equals("percent")) {
                if (second == 0) {
                    result = first / 100;
                } else {
                    result = (first / 100) * second;
                }
            } else if (currentOperation.equals("divide")) {
                if (second != 0) {
                    result = first / second;
                } else {
                    textView.setText("Error");
                    return;
                }
            }

            textView.setText(Double.toString(result));
            isOperationClick = true;
        }
    }
}