package com.alfa.bank.model;

import java.io.Serializable;

public class Beneficiary implements Serializable {
    private String beneficiaryId;
    private String name;
    private String accountNumber;
    private String bankName;
    private String bankCode;
    private String nickname;
    private boolean isFavorite;

    public Beneficiary(String beneficiaryId, String name, String accountNumber, String bankName, String bankCode, String nickname, boolean isFavorite) {
        this.beneficiaryId = beneficiaryId;
        this.name = name;
        this.accountNumber = accountNumber;
        this.bankName = bankName;
        this.bankCode = bankCode;
        this.nickname = nickname;
        this.isFavorite = isFavorite;
    }

    public String getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(String beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
