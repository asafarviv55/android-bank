package com.alfa.bank;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alfa.bank.adapter.InvestmentAdapter;
import com.alfa.bank.model.Investment;

import java.util.ArrayList;
import java.util.List;

public class InvestmentPortfolioActivity extends AppCompatActivity {

    private TextView textViewTotalValue, textViewTotalGain;
    private RecyclerView recyclerView;
    private InvestmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment_portfolio);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Investment Portfolio");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        textViewTotalValue = findViewById(R.id.textViewTotalValue);
        textViewTotalGain = findViewById(R.id.textViewTotalGain);
        recyclerView = findViewById(R.id.recyclerViewInvestments);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Investment> investments = getMockInvestments();
        adapter = new InvestmentAdapter(investments);
        recyclerView.setAdapter(adapter);

        calculateTotals(investments);
    }

    private List<Investment> getMockInvestments() {
        List<Investment> investments = new ArrayList<>();
        investments.add(new Investment("INV001", "Apple Inc.", "AAPL", 150.25, 175.50, 50, "Stock"));
        investments.add(new Investment("INV002", "Microsoft Corp.", "MSFT", 280.00, 320.75, 30, "Stock"));
        investments.add(new Investment("INV003", "S&P 500 Index", "SPY", 400.00, 445.25, 25, "ETF"));
        investments.add(new Investment("INV004", "Corporate Bonds", "BOND", 1000.00, 1050.00, 10, "Bond"));
        investments.add(new Investment("INV005", "Tech Mutual Fund", "TECH", 125.00, 142.30, 40, "Mutual Fund"));
        return investments;
    }

    private void calculateTotals(List<Investment> investments) {
        double totalValue = 0;
        double totalGain = 0;

        for (Investment investment : investments) {
            double currentValue = investment.getCurrentPrice() * investment.getQuantity();
            double purchaseValue = investment.getPurchasePrice() * investment.getQuantity();
            totalValue += currentValue;
            totalGain += (currentValue - purchaseValue);
        }

        textViewTotalValue.setText(String.format("Total Portfolio Value: $%.2f", totalValue));
        textViewTotalGain.setText(String.format("Total Gain/Loss: $%.2f (%.2f%%)",
                totalGain, (totalGain / (totalValue - totalGain)) * 100));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
