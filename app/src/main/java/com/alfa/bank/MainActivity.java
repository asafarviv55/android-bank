package com.alfa.bank;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.alfa.bank.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FundTransferActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void openAccountList(View view) {
        startActivity(new Intent(this, AccountListActivity.class));
    }

    public void openTransactionHistory(View view) {
        startActivity(new Intent(this, TransactionHistoryActivity.class));
    }

    public void openFundTransfer(View view) {
        startActivity(new Intent(this, FundTransferActivity.class));
    }

    public void openCardManagement(View view) {
        startActivity(new Intent(this, CardManagementActivity.class));
    }

    public void openLoanManagement(View view) {
        startActivity(new Intent(this, LoanManagementActivity.class));
    }

    public void openBeneficiaryManagement(View view) {
        startActivity(new Intent(this, BeneficiaryManagementActivity.class));
    }

    public void openBillPayment(View view) {
        startActivity(new Intent(this, BillPaymentActivity.class));
    }

    public void openDeposit(View view) {
        startActivity(new Intent(this, DepositActivity.class));
    }

    public void openATMLocator(View view) {
        startActivity(new Intent(this, ATMLocatorActivity.class));
    }

    public void openInvestmentPortfolio(View view) {
        startActivity(new Intent(this, InvestmentPortfolioActivity.class));
    }
}