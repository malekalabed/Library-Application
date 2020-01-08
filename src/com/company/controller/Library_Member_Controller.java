package com.company.controller;

import com.company.AppHelper;
import com.company.interfaces.I_Menu;
import com.company.models.Book;
import com.company.models.Loan;
import com.company.models.Member;
import com.company.services.Book_Service;
import com.company.services.Loan_Service;
import com.company.services.Member_Service;

public class Library_Member_Controller implements I_Menu {

    private Loan_Service loan_service = new Loan_Service();
    private Book_Service book_service = new Book_Service();
    private Member_Service member_service = new Member_Service();


    public void mainMenu(String userName,String userSSN) throws InterruptedException {
        System.out.println("\n\n                           WELCOME TO THE LIBRARY MEMBERS SERVICE "+userName.toUpperCase()+" \n                                      **************\n   ");
        memberControl(userSSN);
    }

    private void memberControl(String data) throws InterruptedException {
        String option = null;

        do {
            System.out.println("\n\n         ***************  MEMBER'S MENU  ***********\n");
            System.out.println("        1 -----> Books available in the library.");
            System.out.println("        2 -----> Search for a particular book.");
            System.out.println("        3 -----> View the books issued to you.");
            System.out.println("        4 -----> Put a request for a new book.");
            System.out.println("        5 -----> GO BACK TO LOGIN PAGE");
            System.out.println("        /q ----> EXIT PROGRAM\n");

            System.out.print("Choose an option to continue ");

            option = AppHelper.userInput.nextLine();
            switch (option){
                case "1":
                    getAllBooks();
                    break;
                case "2":
                    searchBook();
                    break;
                case "3":
                    bookIssuedToYou(data);
                    break;
                case "4":
                    System.out.println("Future work...");
                    break;
                case "/q":
                    exit();
                    break;
                default:
                    break;
            }

        }while (option.equals("/q"));
    }

    public void exit() {
        String answer;
        System.out.print("\nThe program will exit.\n              Are sure is that you want? y/n > ");
        answer = AppHelper.userInput.nextLine();

        if (answer.equalsIgnoreCase("y")){
            System.out.println("\nWait a moment...");
            try {
                Thread.sleep(2000);
                AppHelper.SMOOTH_EXIT();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            try {
                mainMenu("","");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void getAllBooks() throws InterruptedException {
        System.out.println("\n------- ALL BOOK CURRENTLY AVAILABLE ------");
        for (Book b: book_service.getAll()){
            System.out.println(b);
        }
        memberControl("");
    }

    public void searchBook() throws InterruptedException {
        System.out.println("\n---------- LIST OF AVAILABLE BOOK --------\n");
        book_service.getOrderedBooksByTitles();
        System.out.print("\nProvide ISBN for the book your are looking for > ");
        String isbn = AppHelper.userInput.nextLine();
        if (book_service.find(isbn) != null){
            System.out.println("\n"+book_service.find(isbn)+"\n\n");
            memberControl("");
        }else {
            System.out.println("\nSORRY! No book with the provided ISBN was found. Try again");
            memberControl("");
        }
    }

    private void bookIssuedToYou(String ssn) throws InterruptedException {
        System.out.println("-------- Book issued to you ------");
        for (Loan l: loan_service.getAll()){
            if (l.getMemberSSN().equals(ssn)){
                System.out.println(l);
            }else System.out.println("This member has currently no loan associated to him.\n");
        }
        memberControl("");
    }


    private void issueBookToAMember(String ssn) throws InterruptedException {
        System.out.println("\n ----------- REQUEST A BOOK --------");
        book_service.availableBooks();

        System.out.print("\nProvide book ISBN > ");
        String isbn = AppHelper.userInput.nextLine();
//        System.out.print("\nChoose the member to issue the book to by entering his/her SSN > ");
//        String ssn = AppHelper.userInput.nextLine();

        if (validateISBN(isbn) && validateSSN(ssn)){
            Book bookBorrowed = book_service.find(isbn);
            Member borrower = member_service.find(ssn);
            System.out.println("\nIssuing...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (bookBorrowed.getNumberOfCopy() < 1){
                System.out.println("Sorry! The book is currently not available\n");
            }else {
                Loan loanObj = new Loan();
                loanObj.setISBN_number(isbn);
                loanObj.setMemberSSN(ssn);
                loanObj.setBookTitle(bookBorrowed.getTitle());
                loanObj.setBorrowerName(borrower.getFirstName()+" "+borrower.getLastName());
                loanObj.setIssueDate(AppHelper.ISSUE_DATE());
                loanObj.setDueDate(AppHelper.DUE_DATE());

                loan_service.add(loanObj);
                Book b = book_service.find(loanObj.getISBN_number());
                b.setNumberOfCopy("2",1);
                b.setStatus(b.getNumberOfCopy());

                System.out.println("\nBOOK with title: "+loanObj.getBookTitle()+" have been issued to : Mr/Madame "+loanObj.getBorrowerName()+"\n             Must be returned at latest: "+loanObj.getDueDate()+"\n");
            }
        }else {
            System.out.println("\nSome info are wrong. It's either this member don't exit anymore \n      or that book have been removed from our system.\n");
        }

        memberControl("");

    }

    private boolean validateISBN(String x){
        for (Book b:book_service.getAll()){
            if (b.getISBN_number().equals(x) && AppHelper.isNumeric(x)){
                return true;
            }
        }
        System.out.println("No book corresponds to the ISBN ["+x+"]!\n");
        return false;
    }

    private boolean validateSSN(String x){
        for (Member m:member_service.getAll()){
            if (m.getSsn().equals(x) && AppHelper.isNumeric(x)){
                return true;
            }
        }
        return false;
    }

}
