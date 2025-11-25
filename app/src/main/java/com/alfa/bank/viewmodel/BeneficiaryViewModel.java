package com.alfa.bank.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.alfa.bank.model.Beneficiary;
import com.alfa.bank.repository.BeneficiaryRepository;

import java.util.List;

public class BeneficiaryViewModel extends ViewModel {
    private BeneficiaryRepository repository;
    private LiveData<List<Beneficiary>> beneficiaries;

    public BeneficiaryViewModel() {
        repository = BeneficiaryRepository.getInstance();
        beneficiaries = repository.getBeneficiaries();
    }

    public LiveData<List<Beneficiary>> getBeneficiaries() {
        return beneficiaries;
    }

    public void addBeneficiary(Beneficiary beneficiary) {
        repository.addBeneficiary(beneficiary);
    }

    public void deleteBeneficiary(String beneficiaryId) {
        repository.deleteBeneficiary(beneficiaryId);
    }
}
