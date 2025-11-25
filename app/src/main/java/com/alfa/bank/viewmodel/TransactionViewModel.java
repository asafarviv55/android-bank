package com.alfa.bank.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.alfa.bank.model.Transaction;
import com.alfa.bank.repository.TransactionRepository;

import java.util.List;

public class TransactionViewModel extends ViewModel {
    private TransactionRepository repository;
    private LiveData<List<Transaction>> transactions;

    public TransactionViewModel() {
        repository = TransactionRepository.getInstance();
        transactions = repository.getTransactions();
    }

    public LiveData<List<Transaction>> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        repository.addTransaction(transaction);
    }

    public List<Transaction> getTransactionsByAccount(String accountId) {
        return repository.getTransactionsByAccount(accountId);
    }
}
