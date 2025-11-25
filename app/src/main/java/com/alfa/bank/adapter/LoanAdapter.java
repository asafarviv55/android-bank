package com.alfa.bank.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alfa.bank.R;
import com.alfa.bank.model.Loan;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class LoanAdapter extends RecyclerView.Adapter<LoanAdapter.LoanViewHolder> {

    private List<Loan> loans;
    private OnLoanActionListener listener;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.US);

    public interface OnLoanActionListener {
        void onMakePayment(Loan loan);
    }

    public LoanAdapter(List<Loan> loans, OnLoanActionListener listener) {
        this.loans = loans;
        this.listener = listener;
    }

    @NonNull
    @Override
    public LoanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loan, parent, false);
        return new LoanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoanViewHolder holder, int position) {
        Loan loan = loans.get(position);
        holder.textViewLoanType.setText(loan.getLoanType());
        holder.textViewLoanAmount.setText(String.format("Loan Amount: $%.2f", loan.getLoanAmount()));
        holder.textViewRemainingBalance.setText(String.format("Remaining: $%.2f", loan.getRemainingBalance()));
        holder.textViewMonthlyPayment.setText(String.format("Monthly: $%.2f", loan.getMonthlyPayment()));
        holder.textViewNextPayment.setText("Next Payment: " + dateFormat.format(loan.getNextPaymentDate()));
        holder.textViewInterestRate.setText(String.format("Rate: %.2f%%", loan.getInterestRate()));

        holder.buttonMakePayment.setOnClickListener(v -> listener.onMakePayment(loan));
    }

    @Override
    public int getItemCount() {
        return loans.size();
    }

    static class LoanViewHolder extends RecyclerView.ViewHolder {
        TextView textViewLoanType, textViewLoanAmount, textViewRemainingBalance;
        TextView textViewMonthlyPayment, textViewNextPayment, textViewInterestRate;
        Button buttonMakePayment;

        LoanViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewLoanType = itemView.findViewById(R.id.textViewLoanType);
            textViewLoanAmount = itemView.findViewById(R.id.textViewLoanAmount);
            textViewRemainingBalance = itemView.findViewById(R.id.textViewRemainingBalance);
            textViewMonthlyPayment = itemView.findViewById(R.id.textViewMonthlyPayment);
            textViewNextPayment = itemView.findViewById(R.id.textViewNextPayment);
            textViewInterestRate = itemView.findViewById(R.id.textViewInterestRate);
            buttonMakePayment = itemView.findViewById(R.id.buttonMakePayment);
        }
    }
}
