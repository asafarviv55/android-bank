package com.alfa.bank.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.alfa.bank.model.Beneficiary;

import java.util.ArrayList;
import java.util.List;

public class BeneficiaryRepository {
    private static BeneficiaryRepository instance;
    private MutableLiveData<List<Beneficiary>> beneficiariesLiveData;

    private BeneficiaryRepository() {
        beneficiariesLiveData = new MutableLiveData<>();
        loadMockData();
    }

    public static BeneficiaryRepository getInstance() {
        if (instance == null) {
            instance = new BeneficiaryRepository();
        }
        return instance;
    }

    public LiveData<List<Beneficiary>> getBeneficiaries() {
        return beneficiariesLiveData;
    }

    private void loadMockData() {
        List<Beneficiary> beneficiaries = new ArrayList<>();
        beneficiaries.add(new Beneficiary("BEN001", "Sarah Johnson", "9876543210", "Chase Bank", "CHB001", "Mom", true));
        beneficiaries.add(new Beneficiary("BEN002", "Michael Brown", "1234567890", "Bank of America", "BOA001", "Brother", true));
        beneficiaries.add(new Beneficiary("BEN003", "Emily Davis", "5555666677", "Wells Fargo", "WF001", "Friend", false));
        beneficiariesLiveData.setValue(beneficiaries);
    }

    public void addBeneficiary(Beneficiary beneficiary) {
        List<Beneficiary> beneficiaries = beneficiariesLiveData.getValue();
        if (beneficiaries == null) {
            beneficiaries = new ArrayList<>();
        }
        beneficiaries.add(beneficiary);
        beneficiariesLiveData.setValue(beneficiaries);
    }

    public void deleteBeneficiary(String beneficiaryId) {
        List<Beneficiary> beneficiaries = beneficiariesLiveData.getValue();
        if (beneficiaries != null) {
            beneficiaries.removeIf(b -> b.getBeneficiaryId().equals(beneficiaryId));
            beneficiariesLiveData.setValue(beneficiaries);
        }
    }
}
