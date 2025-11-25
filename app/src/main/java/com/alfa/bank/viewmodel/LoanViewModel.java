package com.alfa.bank.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.alfa.bank.model.Loan;
import com.alfa.bank.repository.LoanRepository;

import java.util.List;

public class LoanViewModel extends ViewModel {
    private LoanRepository repository;
    private LiveData<List<Loan>> loans;

    public LoanViewModel() {
        repository = LoanRepository.getInstance();
        loans = repository.getLoans();
    }

    public LiveData<List<Loan>> getLoans() {
        return loans;
    }

    public void makePayment(String loanId, double amount) {
        repository.makePayment(loanId, amount);
    }
}
