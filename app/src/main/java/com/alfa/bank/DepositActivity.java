package com.alfa.bank;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DepositActivity extends AppCompatActivity {

    private Spinner spinnerDepositType;
    private EditText editTextDepositAmount, editTextTenureMonths;
    private RadioGroup radioGroupInterestPayout;
    private Button buttonCalculate, buttonOpenDeposit;
    private TextView textViewEstimatedReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Fixed Deposit");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        spinnerDepositType = findViewById(R.id.spinnerDepositType);
        editTextDepositAmount = findViewById(R.id.editTextDepositAmount);
        editTextTenureMonths = findViewById(R.id.editTextTenureMonths);
        radioGroupInterestPayout = findViewById(R.id.radioGroupInterestPayout);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        buttonOpenDeposit = findViewById(R.id.buttonOpenDeposit);
        textViewEstimatedReturn = findViewById(R.id.textViewEstimatedReturn);

        String[] depositTypes = {"Regular FD", "Senior Citizen FD", "Tax Saving FD", "Recurring Deposit"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, depositTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDepositType.setAdapter(adapter);

        buttonCalculate.setOnClickListener(v -> calculateReturn());
        buttonOpenDeposit.setOnClickListener(v -> openDeposit());
    }

    private void calculateReturn() {
        String amountStr = editTextDepositAmount.getText().toString();
        String tenureStr = editTextTenureMonths.getText().toString();

        if (amountStr.isEmpty() || tenureStr.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double amount = Double.parseDouble(amountStr);
        int months = Integer.parseInt(tenureStr);

        double interestRate = 6.5;
        if (spinnerDepositType.getSelectedItemPosition() == 1) {
            interestRate = 7.0;
        }

        double maturityAmount = amount * Math.pow((1 + interestRate / 400), (months / 3.0));

        textViewEstimatedReturn.setText(String.format("Estimated Maturity Amount: $%.2f\nInterest Earned: $%.2f",
                maturityAmount, maturityAmount - amount));
    }

    private void openDeposit() {
        if (textViewEstimatedReturn.getText().toString().contains("Estimated")) {
            Toast.makeText(this, "Fixed Deposit opened successfully", Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(this, "Please calculate returns first", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
