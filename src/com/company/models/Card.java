package com.company.models;

import java.io.Serializable;

public class Card implements Serializable {

    private String ownerName;
    private String ownerSSN;
    private double balance;

    public Card(String ownerName, String ownerSSN) {
        this.ownerName = ownerName;
        this.ownerSSN = ownerSSN;
        this.balance = 100;
    }


    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerSSN() {
        return ownerSSN;
    }

    public void setOwnerSSN(String ownerSSN) {
        this.ownerSSN = ownerSSN;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "MEMBERSHIP CARD\n  Owner: " + ownerName +"\n  Owner SSN: " + ownerSSN +"\n  balance: Kr" + balance;
    }
}
