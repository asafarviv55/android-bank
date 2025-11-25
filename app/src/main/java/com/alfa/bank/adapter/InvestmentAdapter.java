package com.alfa.bank.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alfa.bank.R;
import com.alfa.bank.model.Investment;

import java.util.List;

public class InvestmentAdapter extends RecyclerView.Adapter<InvestmentAdapter.InvestmentViewHolder> {

    private List<Investment> investments;

    public InvestmentAdapter(List<Investment> investments) {
        this.investments = investments;
    }

    @NonNull
    @Override
    public InvestmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_investment, parent, false);
        return new InvestmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InvestmentViewHolder holder, int position) {
        Investment investment = investments.get(position);
        holder.textViewName.setText(investment.getName());
        holder.textViewSymbol.setText(investment.getSymbol());
        holder.textViewType.setText(investment.getType());
        holder.textViewQuantity.setText("Qty: " + investment.getQuantity());
        holder.textViewCurrentPrice.setText(String.format("$%.2f", investment.getCurrentPrice()));
        holder.textViewTotalValue.setText(String.format("Value: $%.2f", investment.getTotalValue()));

        double gainLoss = investment.getGainLoss();
        double gainLossPercent = investment.getGainLossPercentage();

        holder.textViewGainLoss.setText(String.format("$%.2f (%.2f%%)", gainLoss, gainLossPercent));

        if (gainLoss >= 0) {
            holder.textViewGainLoss.setTextColor(holder.itemView.getContext().getResources().getColor(android.R.color.holo_green_dark));
        } else {
            holder.textViewGainLoss.setTextColor(holder.itemView.getContext().getResources().getColor(android.R.color.holo_red_dark));
        }
    }

    @Override
    public int getItemCount() {
        return investments.size();
    }

    static class InvestmentViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewSymbol, textViewType, textViewQuantity;
        TextView textViewCurrentPrice, textViewTotalValue, textViewGainLoss;

        InvestmentViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewInvestmentName);
            textViewSymbol = itemView.findViewById(R.id.textViewSymbol);
            textViewType = itemView.findViewById(R.id.textViewInvestmentType);
            textViewQuantity = itemView.findViewById(R.id.textViewQuantity);
            textViewCurrentPrice = itemView.findViewById(R.id.textViewCurrentPrice);
            textViewTotalValue = itemView.findViewById(R.id.textViewTotalValue);
            textViewGainLoss = itemView.findViewById(R.id.textViewGainLoss);
        }
    }
}
