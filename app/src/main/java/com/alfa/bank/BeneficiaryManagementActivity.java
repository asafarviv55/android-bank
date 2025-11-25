package com.alfa.bank;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alfa.bank.adapter.BeneficiaryAdapter;
import com.alfa.bank.model.Beneficiary;
import com.alfa.bank.viewmodel.BeneficiaryViewModel;

public class BeneficiaryManagementActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BeneficiaryAdapter adapter;
    private BeneficiaryViewModel viewModel;
    private Button buttonAddBeneficiary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beneficiary_management);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Manage Beneficiaries");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recyclerView = findViewById(R.id.recyclerViewBeneficiaries);
        buttonAddBeneficiary = findViewById(R.id.buttonAddBeneficiary);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new ViewModelProvider(this).get(BeneficiaryViewModel.class);

        viewModel.getBeneficiaries().observe(this, beneficiaries -> {
            if (beneficiaries != null) {
                adapter = new BeneficiaryAdapter(beneficiaries, beneficiary -> {
                    viewModel.deleteBeneficiary(beneficiary.getBeneficiaryId());
                    Toast.makeText(this, "Beneficiary removed", Toast.LENGTH_SHORT).show();
                });
                recyclerView.setAdapter(adapter);
            }
        });

        buttonAddBeneficiary.setOnClickListener(v -> showAddBeneficiaryDialog());
    }

    private void showAddBeneficiaryDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Beneficiary");

        final EditText input = new EditText(this);
        input.setHint("Enter beneficiary name");
        builder.setView(input);

        builder.setPositiveButton("Add", (dialog, which) -> {
            String name = input.getText().toString();
            if (!name.isEmpty()) {
                Beneficiary beneficiary = new Beneficiary(
                        "BEN" + System.currentTimeMillis(),
                        name,
                        "0000000000",
                        "New Bank",
                        "NB001",
                        name,
                        false
                );
                viewModel.addBeneficiary(beneficiary);
                Toast.makeText(this, "Beneficiary added", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
