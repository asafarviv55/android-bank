package com.alfa.bank.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.alfa.bank.model.Loan;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LoanRepository {
    private static LoanRepository instance;
    private MutableLiveData<List<Loan>> loansLiveData;

    private LoanRepository() {
        loansLiveData = new MutableLiveData<>();
        loadMockData();
    }

    public static LoanRepository getInstance() {
        if (instance == null) {
            instance = new LoanRepository();
        }
        return instance;
    }

    public LiveData<List<Loan>> getLoans() {
        return loansLiveData;
    }

    private void loadMockData() {
        List<Loan> loans = new ArrayList<>();
        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.YEAR, -2);
        Date startDate1 = cal.getTime();
        cal.add(Calendar.MONTH, 1);
        Date nextPayment1 = cal.getTime();

        loans.add(new Loan("LOAN001", "Home Loan", 250000.00, 3.5, 240, 1250.00, 225000.00, startDate1, nextPayment1, "Active"));

        cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);
        Date startDate2 = cal.getTime();
        cal.add(Calendar.MONTH, 1);
        Date nextPayment2 = cal.getTime();

        loans.add(new Loan("LOAN002", "Auto Loan", 35000.00, 4.2, 60, 650.00, 28000.00, startDate2, nextPayment2, "Active"));

        loansLiveData.setValue(loans);
    }

    public void makePayment(String loanId, double amount) {
        List<Loan> loans = loansLiveData.getValue();
        if (loans != null) {
            for (Loan loan : loans) {
                if (loan.getLoanId().equals(loanId)) {
                    loan.setRemainingBalance(loan.getRemainingBalance() - amount);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(loan.getNextPaymentDate());
                    cal.add(Calendar.MONTH, 1);
                    loan.setNextPaymentDate(cal.getTime());
                    loansLiveData.setValue(loans);
                    break;
                }
            }
        }
    }
}
