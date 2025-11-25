package com.alfa.bank.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alfa.bank.R;
import com.alfa.bank.model.Card;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private List<Card> cards;
    private OnCardActionListener listener;

    public interface OnCardActionListener {
        void onToggleStatus(Card card);
    }

    public CardAdapter(List<Card> cards, OnCardActionListener listener) {
        this.cards = cards;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        Card card = cards.get(position);
        holder.textViewCardType.setText(card.getCardType());
        holder.textViewCardNumber.setText(card.getCardNumber());
        holder.textViewExpiry.setText("Exp: " + card.getExpiryDate());
        holder.textViewStatus.setText(card.isActive() ? "Active" : "Blocked");
        holder.buttonToggle.setText(card.isActive() ? "Block Card" : "Activate Card");

        holder.buttonToggle.setOnClickListener(v -> listener.onToggleStatus(card));
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    static class CardViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCardType, textViewCardNumber, textViewExpiry, textViewStatus;
        Button buttonToggle;

        CardViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCardType = itemView.findViewById(R.id.textViewCardType);
            textViewCardNumber = itemView.findViewById(R.id.textViewCardNumber);
            textViewExpiry = itemView.findViewById(R.id.textViewExpiry);
            textViewStatus = itemView.findViewById(R.id.textViewStatus);
            buttonToggle = itemView.findViewById(R.id.buttonToggleCard);
        }
    }
}
