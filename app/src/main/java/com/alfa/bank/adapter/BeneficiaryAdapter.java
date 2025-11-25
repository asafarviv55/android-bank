package com.alfa.bank.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alfa.bank.R;
import com.alfa.bank.model.Beneficiary;

import java.util.List;

public class BeneficiaryAdapter extends RecyclerView.Adapter<BeneficiaryAdapter.BeneficiaryViewHolder> {

    private List<Beneficiary> beneficiaries;
    private OnBeneficiaryActionListener listener;

    public interface OnBeneficiaryActionListener {
        void onDelete(Beneficiary beneficiary);
    }

    public BeneficiaryAdapter(List<Beneficiary> beneficiaries, OnBeneficiaryActionListener listener) {
        this.beneficiaries = beneficiaries;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BeneficiaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_beneficiary, parent, false);
        return new BeneficiaryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BeneficiaryViewHolder holder, int position) {
        Beneficiary beneficiary = beneficiaries.get(position);
        holder.textViewName.setText(beneficiary.getName());
        holder.textViewNickname.setText(beneficiary.getNickname());
        holder.textViewAccountNumber.setText(beneficiary.getAccountNumber());
        holder.textViewBankName.setText(beneficiary.getBankName());

        holder.buttonDelete.setOnClickListener(v -> listener.onDelete(beneficiary));
    }

    @Override
    public int getItemCount() {
        return beneficiaries.size();
    }

    static class BeneficiaryViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewNickname, textViewAccountNumber, textViewBankName;
        Button buttonDelete;

        BeneficiaryViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewBeneficiaryName);
            textViewNickname = itemView.findViewById(R.id.textViewNickname);
            textViewAccountNumber = itemView.findViewById(R.id.textViewBeneficiaryAccount);
            textViewBankName = itemView.findViewById(R.id.textViewBankName);
            buttonDelete = itemView.findViewById(R.id.buttonDeleteBeneficiary);
        }
    }
}
