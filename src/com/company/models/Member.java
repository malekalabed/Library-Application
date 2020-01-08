package com.company.models;

import java.io.Serializable;

public class Member implements Serializable {

    private String ssn;
    private String firstName;
    private String lastName;
    private String homeAddress;
    private String emailAddress;
    private String phoneNumber;
    private String password;

    private double accountBalance = 100;

    public Member() {
    }

    public Member(String ssn, String firstName, String lastName, String homeAddress, String phoneNumber, String password,String emailAddress) {
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.homeAddress = homeAddress;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.emailAddress = emailAddress;
    }


    public Member(String ssn, String password) {
        this.ssn = ssn;
        this.password = password;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double amount) {

        this.accountBalance = accountBalance - amount;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "Member\n  Social security number: " + ssn +"\n  Name: " + firstName+" "+lastName+"\n  Address: " + homeAddress +"\n  Email Address: " + emailAddress+"\n  Phone number: " + phoneNumber+"\n  Account balance: Kr"+accountBalance;
    }
}