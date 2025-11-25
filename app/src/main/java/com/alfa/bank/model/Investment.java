package com.alfa.bank.model;

import java.io.Serializable;

public class Investment implements Serializable {
    private String investmentId;
    private String name;
    private String symbol;
    private double purchasePrice;
    private double currentPrice;
    private int quantity;
    private String type;

    public Investment(String investmentId, String name, String symbol, double purchasePrice, double currentPrice, int quantity, String type) {
        this.investmentId = investmentId;
        this.name = name;
        this.symbol = symbol;
        this.purchasePrice = purchasePrice;
        this.currentPrice = currentPrice;
        this.quantity = quantity;
        this.type = type;
    }

    public String getInvestmentId() {
        return investmentId;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getType() {
        return type;
    }

    public double getTotalValue() {
        return currentPrice * quantity;
    }

    public double getGainLoss() {
        return (currentPrice - purchasePrice) * quantity;
    }

    public double getGainLossPercentage() {
        return ((currentPrice - purchasePrice) / purchasePrice) * 100;
    }
}
