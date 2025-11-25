package com.alfa.bank.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.alfa.bank.model.Transaction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TransactionRepository {
    private static TransactionRepository instance;
    private MutableLiveData<List<Transaction>> transactionsLiveData;

    private TransactionRepository() {
        transactionsLiveData = new MutableLiveData<>();
        loadMockData();
    }

    public static TransactionRepository getInstance() {
        if (instance == null) {
            instance = new TransactionRepository();
        }
        return instance;
    }

    public LiveData<List<Transaction>> getTransactions() {
        return transactionsLiveData;
    }

    private void loadMockData() {
        List<Transaction> transactions = new ArrayList<>();
        Calendar cal = Calendar.getInstance();

        transactions.add(new Transaction("TXN001", "ACC001", "Debit", -45.99, "Amazon Purchase", new Date(), "Shopping", "Completed", "Amazon"));

        cal.add(Calendar.DAY_OF_MONTH, -1);
        transactions.add(new Transaction("TXN002", "ACC001", "Credit", 2500.00, "Salary Deposit", cal.getTime(), "Income", "Completed", "Employer Corp"));

        cal.add(Calendar.DAY_OF_MONTH, -2);
        transactions.add(new Transaction("TXN003", "ACC001", "Debit", -120.50, "Grocery Store", cal.getTime(), "Food", "Completed", "Walmart"));

        cal.add(Calendar.DAY_OF_MONTH, -3);
        transactions.add(new Transaction("TXN004", "ACC002", "Transfer", -500.00, "Transfer to Checking", cal.getTime(), "Transfer", "Completed", "Own Account"));

        cal.add(Calendar.DAY_OF_MONTH, -5);
        transactions.add(new Transaction("TXN005", "ACC001", "Debit", -85.00, "Restaurant", cal.getTime(), "Dining", "Completed", "Olive Garden"));

        transactionsLiveData.setValue(transactions);
    }

    public void addTransaction(Transaction transaction) {
        List<Transaction> transactions = transactionsLiveData.getValue();
        if (transactions == null) {
            transactions = new ArrayList<>();
        }
        transactions.add(0, transaction);
        transactionsLiveData.setValue(transactions);
    }

    public List<Transaction> getTransactionsByAccount(String accountId) {
        List<Transaction> allTransactions = transactionsLiveData.getValue();
        List<Transaction> accountTransactions = new ArrayList<>();

        if (allTransactions != null) {
            for (Transaction transaction : allTransactions) {
                if (transaction.getAccountId().equals(accountId)) {
                    accountTransactions.add(transaction);
                }
            }
        }

        return accountTransactions;
    }
}
