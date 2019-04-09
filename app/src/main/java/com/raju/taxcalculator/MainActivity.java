package com.raju.taxcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etSalary;
    private TextView tvPlaceholder, tvResult;
    private double taxAmount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSalary = findViewById(R.id.etSalary);
        tvPlaceholder = findViewById(R.id.tvPlaceHolder);
        tvResult = findViewById(R.id.tvResult);
    }

    public void calculateTax(View view) {
        String value = etSalary.getText().toString().trim();
        if (value.isEmpty()) {
            etSalary.setError("Please enter your monthly salary");
            tvPlaceholder.setVisibility(View.GONE);
            tvResult.setVisibility(View.GONE);
        } else {
            User user = new User((Double.parseDouble(value)) * 12);
            double tax = computeTax(user.getSalary());
            tvPlaceholder.setVisibility(View.VISIBLE);
            tvResult.setVisibility(View.VISIBLE);
            tvResult.setText(new DecimalFormat("#.00").format(tax));
        }
    }

    private double computeTax(double salary) {
        if (salary > 0 && salary <= 200000) {
            taxAmount = 0.01 * salary;
        } else if (salary > 200000 && salary <= 350000) {
            taxAmount = (0.01 * 200000) + 0.15 * (salary - 200000);
        } else {
            taxAmount = (0.01 * 200000) + 0.15 * 150000 + 0.25 * (salary - 350000);
        }
        return taxAmount;
    }

}
