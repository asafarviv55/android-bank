package com.alfa.bank;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.alfa.bank.model.Transaction;
import com.alfa.bank.viewmodel.TransactionViewModel;

import java.util.Date;

public class BillPaymentActivity extends AppCompatActivity {

    private Spinner spinnerBillType;
    private EditText editTextBillerId, editTextAmount;
    private Button buttonPayBill;
    private TransactionViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_payment);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Bill Payment");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        spinnerBillType = findViewById(R.id.spinnerBillType);
        editTextBillerId = findViewById(R.id.editTextBillerId);
        editTextAmount = findViewById(R.id.editTextBillAmount);
        buttonPayBill = findViewById(R.id.buttonPayBill);

        viewModel = new ViewModelProvider(this).get(TransactionViewModel.class);

        String[] billTypes = {"Electricity", "Water", "Gas", "Internet", "Phone", "Cable TV", "Insurance"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, billTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBillType.setAdapter(adapter);

        buttonPayBill.setOnClickListener(v -> processBillPayment());
    }

    private void processBillPayment() {
        String billType = spinnerBillType.getSelectedItem().toString();
        String billerId = editTextBillerId.getText().toString();
        String amountStr = editTextAmount.getText().toString();

        if (billerId.isEmpty() || amountStr.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double amount = Double.parseDouble(amountStr);

        Transaction transaction = new Transaction(
                "TXN" + System.currentTimeMillis(),
                "ACC001",
                "Debit",
                -amount,
                billType + " Bill Payment - " + billerId,
                new Date(),
                "Bills",
                "Completed",
                billType + " Provider"
        );

        viewModel.addTransaction(transaction);

        Toast.makeText(this, "Bill payment successful", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
