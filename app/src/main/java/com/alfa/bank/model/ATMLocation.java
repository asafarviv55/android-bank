package com.alfa.bank.model;

import java.io.Serializable;

public class ATMLocation implements Serializable {
    private String atmId;
    private String name;
    private String address;
    private String distance;
    private boolean isOpen24Hours;
    private boolean hasCashDeposit;

    public ATMLocation(String atmId, String name, String address, String distance, boolean isOpen24Hours, boolean hasCashDeposit) {
        this.atmId = atmId;
        this.name = name;
        this.address = address;
        this.distance = distance;
        this.isOpen24Hours = isOpen24Hours;
        this.hasCashDeposit = hasCashDeposit;
    }

    public String getAtmId() {
        return atmId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDistance() {
        return distance;
    }

    public boolean isOpen24Hours() {
        return isOpen24Hours;
    }

    public boolean isHasCashDeposit() {
        return hasCashDeposit;
    }
}
