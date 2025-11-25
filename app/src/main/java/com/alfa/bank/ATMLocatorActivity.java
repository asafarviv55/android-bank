package com.alfa.bank;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alfa.bank.adapter.ATMAdapter;
import com.alfa.bank.model.ATMLocation;

import java.util.ArrayList;
import java.util.List;

public class ATMLocatorActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ATMAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atm_locator);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("ATM Locator");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recyclerView = findViewById(R.id.recyclerViewATMs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<ATMLocation> atmLocations = getMockATMLocations();
        adapter = new ATMAdapter(atmLocations, atm -> {
            Toast.makeText(this, "Getting directions to " + atm.getName(), Toast.LENGTH_SHORT).show();
        });
        recyclerView.setAdapter(adapter);
    }

    private List<ATMLocation> getMockATMLocations() {
        List<ATMLocation> locations = new ArrayList<>();
        locations.add(new ATMLocation("ATM001", "Main Street Branch", "123 Main St, Downtown", "0.5 mi", true, true));
        locations.add(new ATMLocation("ATM002", "Shopping Mall", "456 Mall Ave, North", "1.2 mi", true, false));
        locations.add(new ATMLocation("ATM003", "Airport Terminal", "789 Airport Rd", "3.5 mi", false, true));
        locations.add(new ATMLocation("ATM004", "Central Station", "321 Station Blvd", "0.8 mi", true, true));
        locations.add(new ATMLocation("ATM005", "Business District", "555 Corporate Dr", "2.1 mi", true, false));
        return locations;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
