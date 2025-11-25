package com.alfa.bank;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alfa.bank.adapter.LoanAdapter;
import com.alfa.bank.viewmodel.LoanViewModel;

public class LoanManagementActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LoanAdapter adapter;
    private LoanViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_management);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Loan Management");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recyclerView = findViewById(R.id.recyclerViewLoans);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new ViewModelProvider(this).get(LoanViewModel.class);

        viewModel.getLoans().observe(this, loans -> {
            if (loans != null && !loans.isEmpty()) {
                adapter = new LoanAdapter(loans, loan -> {
                    viewModel.makePayment(loan.getLoanId(), loan.getMonthlyPayment());
                    Toast.makeText(this, "Payment processed successfully", Toast.LENGTH_SHORT).show();
                });
                recyclerView.setAdapter(adapter);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
