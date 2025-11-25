package com.alfa.bank;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alfa.bank.adapter.CardAdapter;
import com.alfa.bank.viewmodel.CardViewModel;

public class CardManagementActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private CardViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_management);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Card Management");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recyclerView = findViewById(R.id.recyclerViewCards);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new ViewModelProvider(this).get(CardViewModel.class);

        viewModel.getCards().observe(this, cards -> {
            if (cards != null && !cards.isEmpty()) {
                adapter = new CardAdapter(cards, card -> {
                    viewModel.toggleCardStatus(card.getCardId());
                    Toast.makeText(this, "Card status updated", Toast.LENGTH_SHORT).show();
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
