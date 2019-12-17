package com.company.models;

import java.io.Serializable;

public class Member implements Serializable {

    private String ssn;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String password;
    private Card memberShipCard;
    private boolean errorOccurred;

    public Member() {
    }

    public Member(String ssn, String firstName, String lastName, String address, String phoneNumber, String password, Card memberShipCard) {
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.memberShipCard = memberShipCard;
        this.errorOccurred = false;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
    }

    public Card getMemberShipCard() {
        return memberShipCard;
    }

    public void setMemberShipCard(Card memberShipCard) {
        this.memberShipCard = memberShipCard;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
            this.password = password;
    }

    public boolean isErrorOccurred() {
        return errorOccurred;
    }

    @Override
    public String toString() {
        return "Member\n  Social security number: " + ssn +"\n  Name: " + firstName+" "+lastName+"\n  Address: " + address +"\n  Phone number: " + phoneNumber;
    }


    public String memberWithCardTostring() {
        return "Member\n  Social security number: " + ssn +"\n  Name: " + firstName +" "+lastName+"\n  Address: " + address +"\n  Phone number: " + phoneNumber+"\n      "+memberShipCard;
    }
}
