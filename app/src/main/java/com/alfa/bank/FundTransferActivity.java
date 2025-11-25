package com.alfa.bank;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.alfa.bank.model.Account;
import com.alfa.bank.model.Transaction;
import com.alfa.bank.viewmodel.AccountViewModel;
import com.alfa.bank.viewmodel.TransactionViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FundTransferActivity extends AppCompatActivity {

    private Spinner spinnerFromAccount, spinnerToAccount;
    private EditText editTextAmount, editTextDescription;
    private Button buttonTransfer;
    private AccountViewModel accountViewModel;
    private TransactionViewModel transactionViewModel;
    private List<Account> accounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fund_transfer);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Fund Transfer");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        spinnerFromAccount = findViewById(R.id.spinnerFromAccount);
        spinnerToAccount = findViewById(R.id.spinnerToAccount);
        editTextAmount = findViewById(R.id.editTextAmount);
        editTextDescription = findViewById(R.id.editTextDescription);
        buttonTransfer = findViewById(R.id.buttonTransfer);

        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        transactionViewModel = new ViewModelProvider(this).get(TransactionViewModel.class);

        accountViewModel.getAccounts().observe(this, accountList -> {
            if (accountList != null && !accountList.isEmpty()) {
                accounts = accountList;
                List<String> accountNumbers = new ArrayList<>();
                for (Account account : accountList) {
                    accountNumbers.add(account.getAccountNumber() + " - " + account.getAccountType());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, accountNumbers);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerFromAccount.setAdapter(adapter);
                spinnerToAccount.setAdapter(adapter);
            }
        });

        buttonTransfer.setOnClickListener(v -> performTransfer());
    }

    private void performTransfer() {
        String amountStr = editTextAmount.getText().toString();
        String description = editTextDescription.getText().toString();

        if (amountStr.isEmpty()) {
            Toast.makeText(this, "Please enter amount", Toast.LENGTH_SHORT).show();
            return;
        }

        double amount = Double.parseDouble(amountStr);
        int fromPosition = spinnerFromAccount.getSelectedItemPosition();
        int toPosition = spinnerToAccount.getSelectedItemPosition();

        if (fromPosition == toPosition) {
            Toast.makeText(this, "Cannot transfer to same account", Toast.LENGTH_SHORT).show();
            return;
        }

        Account fromAccount = accounts.get(fromPosition);
        Account toAccount = accounts.get(toPosition);

        if (fromAccount.getBalance() < amount) {
            Toast.makeText(this, "Insufficient balance", Toast.LENGTH_SHORT).show();
            return;
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        accountViewModel.updateAccount(fromAccount);
        accountViewModel.updateAccount(toAccount);

        Transaction transaction = new Transaction(
                "TXN" + System.currentTimeMillis(),
                fromAccount.getAccountId(),
                "Transfer",
                -amount,
                description.isEmpty() ? "Transfer to " + toAccount.getAccountNumber() : description,
                new Date(),
                "Transfer",
                "Completed",
                toAccount.getAccountHolderName()
        );

        transactionViewModel.addTransaction(transaction);

        Toast.makeText(this, "Transfer successful", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
