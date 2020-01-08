package com.company.models;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class Loan implements Serializable {
    private static DateFormat df = new SimpleDateFormat("YYYY-MM-dd  HH:mm:ss");
    private String issueDate;
    private String  dueDate;
    private String returnDate;
    private String memberSSN;
    private String bookTitle;
    private String borrowerName;
    private String ISBN_number;


    public Loan(String bookTitle,String borrowerName,String ISBN_number, String issueDate, String dueDate, String memberSSN) {
        this.ISBN_number = ISBN_number;
        this.borrowerName = borrowerName;
        this.bookTitle = bookTitle;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.returnDate = "Awaiting...";
        this.memberSSN = memberSSN;
    }


    public Loan() {
        this.returnDate = "Awaiting...";
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getMemberSSN() {
        return memberSSN;
    }

    public void setMemberSSN(String memberSSN) {
        this.memberSSN = memberSSN;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public String getISBN_number() {
        return ISBN_number;
    }

    public void setISBN_number(String ISBN_number) {
        this.ISBN_number = ISBN_number;
    }

    @Override
    public String toString() {
        return "\n     Book ISBN: "+ISBN_number+"\n     Book Title: "+bookTitle+"\n     Borrower Name: "+borrowerName+"\n     Issued Date: " + issueDate+"\n     Due Date: " + dueDate +"\n     Return Date: " + returnDate+"\n     Borrowed by: "+memberSSN;
    }

}
