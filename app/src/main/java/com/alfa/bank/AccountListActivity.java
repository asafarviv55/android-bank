package com.alfa.bank;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alfa.bank.adapter.AccountAdapter;
import com.alfa.bank.viewmodel.AccountViewModel;

public class AccountListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AccountAdapter adapter;
    private AccountViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_list);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("My Accounts");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recyclerView = findViewById(R.id.recyclerViewAccounts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new ViewModelProvider(this).get(AccountViewModel.class);

        viewModel.getAccounts().observe(this, accounts -> {
            if (accounts != null && !accounts.isEmpty()) {
                adapter = new AccountAdapter(accounts, account -> {
                    Toast.makeText(this, "Account: " + account.getAccountNumber(), Toast.LENGTH_SHORT).show();
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
