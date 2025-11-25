package com.alfa.bank.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.alfa.bank.model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountRepository {
    private static AccountRepository instance;
    private MutableLiveData<List<Account>> accountsLiveData;

    private AccountRepository() {
        accountsLiveData = new MutableLiveData<>();
        loadMockData();
    }

    public static AccountRepository getInstance() {
        if (instance == null) {
            instance = new AccountRepository();
        }
        return instance;
    }

    public LiveData<List<Account>> getAccounts() {
        return accountsLiveData;
    }

    private void loadMockData() {
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account("ACC001", "4532-7890-1234-5678", "Checking", 15750.50, "USD", "John Smith"));
        accounts.add(new Account("ACC002", "4532-7890-1234-9999", "Savings", 45200.00, "USD", "John Smith"));
        accounts.add(new Account("ACC003", "4532-7890-1234-7777", "Investment", 125000.00, "USD", "John Smith"));
        accountsLiveData.setValue(accounts);
    }

    public Account getAccountById(String accountId) {
        List<Account> accounts = accountsLiveData.getValue();
        if (accounts != null) {
            for (Account account : accounts) {
                if (account.getAccountId().equals(accountId)) {
                    return account;
                }
            }
        }
        return null;
    }

    public void updateAccount(Account account) {
        List<Account> accounts = accountsLiveData.getValue();
        if (accounts != null) {
            for (int i = 0; i < accounts.size(); i++) {
                if (accounts.get(i).getAccountId().equals(account.getAccountId())) {
                    accounts.set(i, account);
                    accountsLiveData.setValue(accounts);
                    break;
                }
            }
        }
    }
}
