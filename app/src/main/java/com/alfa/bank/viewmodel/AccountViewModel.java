package com.alfa.bank.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.alfa.bank.model.Account;
import com.alfa.bank.repository.AccountRepository;

import java.util.List;

public class AccountViewModel extends ViewModel {
    private AccountRepository repository;
    private LiveData<List<Account>> accounts;

    public AccountViewModel() {
        repository = AccountRepository.getInstance();
        accounts = repository.getAccounts();
    }

    public LiveData<List<Account>> getAccounts() {
        return accounts;
    }

    public Account getAccountById(String accountId) {
        return repository.getAccountById(accountId);
    }

    public void updateAccount(Account account) {
        repository.updateAccount(account);
    }
}
