package com.alfa.bank.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alfa.bank.R;
import com.alfa.bank.model.Account;

import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder> {

    private List<Account> accounts;
    private OnAccountClickListener listener;

    public interface OnAccountClickListener {
        void onAccountClick(Account account);
    }

    public AccountAdapter(List<Account> accounts, OnAccountClickListener listener) {
        this.accounts = accounts;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_account, parent, false);
        return new AccountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {
        Account account = accounts.get(position);
        holder.textViewAccountType.setText(account.getAccountType());
        holder.textViewAccountNumber.setText(account.getAccountNumber());
        holder.textViewBalance.setText(String.format("$%.2f %s", account.getBalance(), account.getCurrency()));
        holder.itemView.setOnClickListener(v -> listener.onAccountClick(account));
    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }

    static class AccountViewHolder extends RecyclerView.ViewHolder {
        TextView textViewAccountType, textViewAccountNumber, textViewBalance;

        AccountViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAccountType = itemView.findViewById(R.id.textViewAccountType);
            textViewAccountNumber = itemView.findViewById(R.id.textViewAccountNumber);
            textViewBalance = itemView.findViewById(R.id.textViewBalance);
        }
    }
}
