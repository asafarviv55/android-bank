package com.alfa.bank.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alfa.bank.R;
import com.alfa.bank.model.ATMLocation;

import java.util.List;

public class ATMAdapter extends RecyclerView.Adapter<ATMAdapter.ATMViewHolder> {

    private List<ATMLocation> atmLocations;
    private OnATMActionListener listener;

    public interface OnATMActionListener {
        void onGetDirections(ATMLocation atm);
    }

    public ATMAdapter(List<ATMLocation> atmLocations, OnATMActionListener listener) {
        this.atmLocations = atmLocations;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ATMViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_atm, parent, false);
        return new ATMViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ATMViewHolder holder, int position) {
        ATMLocation atm = atmLocations.get(position);
        holder.textViewName.setText(atm.getName());
        holder.textViewAddress.setText(atm.getAddress());
        holder.textViewDistance.setText(atm.getDistance());
        holder.textView24Hours.setText(atm.isOpen24Hours() ? "24/7" : "Limited Hours");
        holder.textViewCashDeposit.setText(atm.isHasCashDeposit() ? "Cash Deposit Available" : "Cash Withdrawal Only");

        holder.buttonDirections.setOnClickListener(v -> listener.onGetDirections(atm));
    }

    @Override
    public int getItemCount() {
        return atmLocations.size();
    }

    static class ATMViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewAddress, textViewDistance, textView24Hours, textViewCashDeposit;
        Button buttonDirections;

        ATMViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewATMName);
            textViewAddress = itemView.findViewById(R.id.textViewATMAddress);
            textViewDistance = itemView.findViewById(R.id.textViewDistance);
            textView24Hours = itemView.findViewById(R.id.textView24Hours);
            textViewCashDeposit = itemView.findViewById(R.id.textViewCashDeposit);
            buttonDirections = itemView.findViewById(R.id.buttonDirections);
        }
    }
}
