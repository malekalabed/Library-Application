package com.company.controller;

import com.company.AppHelper;
import com.company.interfaces.I_Menu;
import com.company.models.Author;
import com.company.models.Book;
import com.company.models.Loan;
import com.company.models.Member;
import com.company.services.Book_Service;
import com.company.services.Loan_Service;
import com.company.services.Member_Service;

import java.util.ArrayList;
import java.util.List;


public class Library_Admin_Controller implements I_Menu {
    private Member_Service member_service = new Member_Service();
    private Book_Service book_service = new Book_Service();
    private Loan_Service loan_service = new Loan_Service();


    @Override
    public void mainMenu(String userName,String userSSN) throws InterruptedException {

        System.out.println("\n\n\n   **********************************************\n                     LIBRARY MENU\n      ***************************************");
        System.out.println("\n\n                WELCOME "+userName.toUpperCase()+" \n\n   ");
        String option = null;

        do {
            System.out.print("\n                1 > MANAGE BOOKS.");
            System.out.print("\n                2 > MANAGE MEMBERS.");
            System.out.print("\n               /q > EXIT PROGRAM\n");
            System.out.println("\n  ---------------------------------------------");

            System.out.print("\nWhat do you want to do? > ");

            option = AppHelper.userInput.nextLine();
            switch (option){
                case "1":
                    booksManagement();
                    break;
                case "2":
                    membersManagement();
                    break;
                case "/q":
                    exit();
                    break;
                default:
                    mainMenu("","");
                    break;
            }
        }while (!option.equals("/q"));

    }

    @Override
    public void exit() {
        String answer;
        System.out.print("\nThe program will exit.\n              Are sure is that you want? y/n > ");
        answer = AppHelper.userInput.nextLine();

        if (answer.equalsIgnoreCase("y")){
            System.out.println("\nExiting ...");
            try {
                Thread.sleep(2000);
                AppHelper.SMOOTH_EXIT();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            try {
                System.out.println("\nWait a moment ...");
                Thread.sleep(1000);
                mainMenu("","");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }



    /*******************************************************************************************/
    /*********************************************************************/
    /*******************************************/
    /******************************/


    //BOOK MANAGEMENT

    private void booksManagement() throws InterruptedException {
        String option = null;
        do {
            System.out.println("\n\n-------------------------------------------------------    ");
            System.out.println("           ****** BOOK MANAGEMENT MENU *****");
            System.out.print("        1 -----> View all available books");
            System.out.print("\n        2 -----> View available books ordered by category.");
            System.out.print("\n        3 -----> View available books ordered by language.");
            System.out.print("\n        4 -----> View available books ordered by title.");
            System.out.print("\n        5 -----> Search for a particular book.");
            System.out.print("\n        6 -----> Add a book to the library system.");
            System.out.print("\n        7 -----> Edit the information of an existing book.");
            System.out.print("\n        8 -----> Remove a book from the system.");
            System.out.print("\n        /q ----> RETURN");
            System.out.println("\n   --------------------------------------------------------          ");
            Thread.sleep(500);
            System.out.print("\nOption > ");

            option = AppHelper.userInput.nextLine();
            switch (option){
                case "1":
                    getAllBooks();
                    break;
                case "2":
                    booksByCategories();
                    break;
                case "3":
                    booksByLanguages();
                    break;
                case "4":
                    book_service.getOrderedBooksByTitles();
                    break;
                case "5":
                    searchBook();
                    break;
                case "6":
                    addNewBook();
                    break;
                case "7":
                    editBookInfo();
                    break;
                case "8":
                    removeBook();
                    break;
                case "/q":
                    mainMenu("","");
                    break;
                default:
                    mainMenu("","");
                    break;
            }


        }while (!option.equalsIgnoreCase("/q"));

    }



    private void getAllBooks(){
        System.out.println("\n------- ALL BOOK CURRENTLY AVAILABLE ------");
        for (Book b: book_service.getAll()){
            System.out.println(b);
        }
    }

    private void removeBook() throws InterruptedException {
        System.out.println("\n---------- CURRENT BOOKS --------");
        for (Book b: book_service.getAll()){
            System.out.println("[BOOK] Title: "+b.getTitle()+"     ISBN: "+b.getISBN_number());
        }
        System.out.print("\nEnter the ISBN of the book to remove > ");
        String isbn = AppHelper.userInput.nextLine();
        if (validateBook(isbn)){
            if (bookStillOnLoan(isbn)){
                System.out.print("\nThis book is still out on loan\n" +
                        "         By removing it right now, the system will \n" +
                        "                lose track of all copies if more than one.\n" +
                        "                         Are you sure you still want to remove? y/n > ");
                String answer = AppHelper.userInput.nextLine();
                if (answer.equalsIgnoreCase("y")){
                    System.out.println("\nRemoving ...");
                    Thread.sleep(1000);
                    book_service.getAll().removeIf((Book b) -> b.getISBN_number().equals(isbn));
                    loan_service.getAll().removeIf((Loan l) -> l.getISBN_number().equals(isbn));
                    System.out.println("\nBOOK WITH ISBN [ "+isbn+" ] REMOVED!!!\n");
                }else {
                    System.out.println("\nRedirecting to menu ...");
                    Thread.sleep(1000);
                    booksManagement();
                }
            }else {
                System.out.println("\nRemoving ...");
                Thread.sleep(1000);
                book_service.getAll().removeIf((Book b) -> b.getISBN_number().equals(isbn));
                System.out.println("\nBOOK WITH ISBN [ "+isbn+" ] REMOVED!!!\n");
            }
        }
        else System.out.println("\nThe ISBN you have provide is not valid!");
    }

    private boolean bookStillOnLoan(String isbn){
        for (Loan l: loan_service.getAll()){
            if (l.getISBN_number().equals(isbn)){
                return true;
            }
        }
        return false;
    }

    private boolean validateBook(String isbn){
        for (Book b: book_service.getAll()){
            if (b.getISBN_number().equals(isbn) && AppHelper.isNumeric(isbn)){
                return true;
            }
        }
        return false;
    }

    private void editBookInfo() throws InterruptedException {
        String option = null;
        System.out.println("\n\n  ------------ EDIT BOOK ---------");
        System.out.print(" 1 > Edit book's language.");
        System.out.print("  \n2 > Edit book's category.");
        System.out.print("\n/q > RETURN");
        System.out.print("\n\nChoose what property to edit > ");
        option = AppHelper.userInput.nextLine();
        switch (option){
            case "1":
                editLanguage();
                break;
            case "2":
                editCategory();
                break;
            case "/q":
                booksManagement();
                break;
            default:
                System.err.println("Wrong value provided! Try again please.");
                editBookInfo();
                break;
        }
    }

    private void booksByCategories() throws InterruptedException {
        String option;
        System.out.print("\n  --------- BOOKS BY CATEGORIES --------");
        System.out.print("\n         1 > Techs");
        System.out.print("\n         2 > Novels");
        System.out.print("\n         3 > Classics");
        System.out.print("\n         4 > Historical");
        System.out.print("\n         5 > Computer Science");
        System.out.print("\n         6 > Maths");
        System.out.print("\n         7 > Fairy Tales");
        System.out.print("\n        /q > RETURN");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("\n\nChoose a category > ");

        option = AppHelper.userInput.nextLine();

        switch (option){
            case "1":
                book_service.getBooksOrderedByCategory(AppHelper.Category.TECH.toString());
                break;
            case "2":
                book_service.getBooksOrderedByCategory(AppHelper.Category.NOVEL.toString());
                break;
            case "3":
                book_service.getBooksOrderedByCategory(AppHelper.Category.CLASSIC.toString());
                break;
            case "4":
                book_service.getBooksOrderedByCategory(AppHelper.Category.HISTORICAL.toString());
                break;
            case "5":
                book_service.getBooksOrderedByCategory(AppHelper.Category.COMPUTERSCIENCE.toString());
                break;
            case "6":
                book_service.getBooksOrderedByCategory(AppHelper.Category.MATHS.toString());
                break;
            case "7":
                book_service.getBooksOrderedByCategory(AppHelper.Category.FAIRYTALE.toString());
                break;
            case "/q":
                booksManagement();
                break;
            default:
                System.err.println("Wrong value provided! Try again please.");
                booksByCategories();
                break;
        }

    }


    private void booksByLanguages() throws InterruptedException {
        String option;
        System.out.println("\n    ---- BOOKS BY LANGUAGE ---");
        System.out.print("         1 > English");
        System.out.print("\n         2 > French");
        System.out.print("\n         3 > German");
        System.out.print("\n         4 > Russian");
        System.out.print("\n         5 > Swedish");
        System.out.print("\n        /q > RETURN");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("\n\nChoose language > ");

        option = AppHelper.userInput.nextLine();

        switch (option){
            case "1":
                book_service.getBooksOrderedByLanguage(AppHelper.Languages.ENGLISH.toString());
                break;
            case "2":
                book_service.getBooksOrderedByLanguage(AppHelper.Languages.FRENCH.toString());
                break;
            case "3":
                book_service.getBooksOrderedByLanguage(AppHelper.Languages.GERMAN.toString());
                break;
            case "4":
                book_service.getBooksOrderedByLanguage(AppHelper.Languages.RUSSIAN.toString());
                break;
            case "5":
                book_service.getBooksOrderedByLanguage(AppHelper.Languages.SWEDISH.toString());
                break;
            case "/q":
                booksManagement();
                break;
            default:
                System.out.println("Wrong value provided! Try again please.");
                booksByLanguages();
                break;
        }

    }

    private void editCategory() throws InterruptedException {
        String propertyToEdit1 = "Category";

        book_service.getBooksCategories(); // display list of available book titles and categories.

        System.out.print("\nEnter the ISBN of the book you would like to editInfo > ");
        String isbn = AppHelper.userInput.nextLine();
        if (book_service.find(isbn) != null){
            System.out.println("\nCorresponding "+book_service.find(isbn)); // display the chosen book to edit.
            System.out.print("\nEnter the new category. You must choose among :\n            " +
                    " Novel, Maths, Historical, Classic, FairyTale, Tech,Drama, ComputerScience > ");
            String newCategory = AppHelper.userInput.nextLine();
            System.out.println("\nWait please ...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!AppHelper.validCategory(newCategory)){
                System.err.println("\nWARNING!!! You must choose one of the above mentioned category.");
                editBookInfo();
            }else {
                book_service.editInfo(propertyToEdit1,isbn,newCategory); // Edit the book
                System.out.println("\nINFO!!!! The book category has been successfully changed.\n\n");
                editBookInfo();
            }

        }else {
            System.out.println("\nSORRY! No book with the provided ISBN could be found. Try again");
            editCategory();
        }
    }


    private void editLanguage() throws InterruptedException {
        String propertyToEdit1 = "Language";
        book_service.getBooksLanguages(); // display list of available book titles and categories.
        System.out.print("\nEnter the ISBN of the book you would like to editInfo > ");
        String isbn = AppHelper.userInput.nextLine();
        if (book_service.find(isbn) != null){
            System.out.println("\nCorresponding "+book_service.find(isbn)); // display the chosen book to edit.
            System.out.print("\nEnter the new Language > ");
            String newLanguage = AppHelper.userInput.nextLine();
            System.out.println("\nWait please ...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!AppHelper.containsOnlyLetters(newLanguage)){
                System.err.println("\nWARNING!!! Language cannot contain digits.");
                editBookInfo();
            }else {
                book_service.editInfo(propertyToEdit1,isbn,newLanguage); //Edit the book
                System.out.println("\nINFO!!!! The book language has been successfully changed.\n\n");
                editBookInfo();
            }

        }else {
            System.out.println("\nSORRY! No book with the provided ISBN could be found. Try again");
            editLanguage();
        }
    }





    private void searchBook() throws InterruptedException {
        System.out.println("\n---------- LIST OF AVAILABLE BOOK --------\n");
        book_service.getOrderedBooksByTitles();
        System.out.print("\nProvide ISBN for the book your are looking for > ");
        String isbn = AppHelper.userInput.nextLine();
        if (book_service.find(isbn) != null){
            System.out.println("\n"+book_service.find(isbn)+"\n\n");
            booksManagement();
        }else {
            System.out.println("\nSORRY! No book with the provided ISBN was found. Try again");
            booksManagement();
        }
    }


    private void addNewBook() throws InterruptedException {
        String data;
        Book book = new Book();
        ArrayList<Author>authors = new ArrayList<>();

        System.out.println("\n --------- ADD NEW BOOK -----");
        System.out.print("Do you want to add another copy of a book that we already have in stock? y/n> ");
        data = AppHelper.userInput.nextLine();
        if (data.equalsIgnoreCase("y")){
            do {
                System.out.print("Enter the book ISBN > ");
                data = AppHelper.userInput.nextLine();
            }while (!validateISBN(data) || !AppHelper.isNumeric(data));
            addCopyToStock(data);
        }else if(data.equalsIgnoreCase("n")) {
            System.out.print("Enter a title > ");
            data = AppHelper.userInput.nextLine();
            book.setTitle(data);
            do{
                System.out.print("\n provide Author full Name > ");
                data = AppHelper.userInput.nextLine();
                authors.add(new Author(data));
                System.out.print("\nIs there another author? y/n ");
                data = AppHelper.userInput.nextLine();
            }while (data.equalsIgnoreCase("y"));
            book.setAuthors(authors);
            book.setBookAuthors(authors);

            do {
                System.out.print("\n Enter book ISBN > ");
                data = AppHelper.userInput.nextLine();
            }while (isbnAlreadyExist(data) || !AppHelper.isNumeric(data));
            book.setISBN_number(data);

            do {
                System.out.print("\nLanguage > ");
                data = AppHelper.userInput.nextLine();
            }while (!isOnlyLetters(data));
            book.setLanguage(data);

            System.out.print("\nPublisher > ");
            data = AppHelper.userInput.nextLine();
            book.setPublisher(data);

            do {
                System.out.print("\nCategory > ");
                data = AppHelper.userInput.nextLine();
            }while (!isOnlyLetters(data));
            book.setCategory(data);

            do {
                System.out.print("\nNumber of pages > ");
                data = AppHelper.userInput.nextLine();
            }while (!AppHelper.isNumeric(data));
            book.setNumOfPages(data);

            addBook(book);

        }else {
            System.out.println("\nWrong info! Try again maybe?\n");
            booksManagement();
        }



    }

    private void addCopyToStock(String isbn) throws InterruptedException {
        Book book = book_service.find(isbn);
        String  num;
        do {
            System.out.print("\nHow many copy(ies) would you like to add? > ");
            num = AppHelper.userInput.nextLine();
        }while (!AppHelper.isNumeric(num));
        System.out.println("Processing...");
        Thread.sleep(1000);
        book.setNumberOfCopy("1",Integer.parseInt(num));
        System.out.println("\n   "+num+" new copy(ies) of the book ["+isbn+"] have been added to the stock.\n");
    }


    private void addBook(Book book){
        if ( !AppHelper.isNumeric(book.getNumOfPages())
                || !AppHelper.isNumeric(book.getISBN_number())
                || !AppHelper.containsOnlyLetters(book.getLanguage())
                || !AppHelper.containsOnlyLetters(book.getCategory())){
            System.out.println("\nSORRY! THE BOOK CANNOT BE SAVED!");
            System.out.println("\nSome field might have wrong values.\nMake sure that page number and ISBN are digits\nAnd that author's names, categories and languages are all letters. Try again");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                booksManagement();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else {
            book_service.getAll().add(book);
            try {
                System.out.println("Wait a moment please ...");
                Thread.sleep(1500);
                System.out.println("\nINFO!!! Book successfully added!\n\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
                booksManagement();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


    //MEMBERS

    private void membersManagement() throws InterruptedException {
        String option = null;
        do {
            System.out.println("\n\n ------------------------------------------------------------------------");
            System.out.println("            ******************* MEMBER MANAGEMENT MENU ****************");
            System.out.print("         1 ------> View the list of all registered members of the library.");
            System.out.print("\n         2 ------> Add a member to the system");
            System.out.print("\n         3 ------> Members that have not returned book(s) after the due date.");
            System.out.print("\n         4 ------> Issue a book to the member");
            System.out.print("\n         5 ------> Take the book returned from the member");
            System.out.print("\n         6 ------> Renew the book for the member");
            System.out.print("\n         7 ------> View all the books issued to the member");
            System.out.print("\n         8 ------> View the viewLoansHistoryOfAMember of books issued to the member previously");
            System.out.print("\n         9 ------> Edit member's info");
            System.out.print("\n        10 ------> Remove the member from the system");
            System.out.print("\n        /q ------> RETURN");
            System.out.println("\n ------------------------------------------------------------------------");
            System.out.print("Option > ");

            option = AppHelper.userInput.nextLine();
            switch (option){
                case "1":
                    ViewAllMemberRegisterToOurLibrary(); //Done
                    break;
                case "2":
                    addNewMemberToLibrarySystem();
                    break;
                case "3":
                    viewListOfMemberWithCurrentLateReturnBooks();
                    break;
                case "4":
                    issueBookToAMember(); //Done
                    break;
                case "5":
                    takeBackBookFromMember(); //Done
                    break;
                case "6":
                    extendDueDate(); //Done
                    break;
                case "7":
                    viewCurrentBooksIssuedToAMember(); //Done
                    break;
                case "8":
                    viewLoansHistoryOfAMember();
                    break;
                case "9":
                    editMemberInfo();
                    break;
                case "10":
                    removeMemberFromSystem();
                    break;
                case "/q":
                    mainMenu("","");
                    break;
                default:
                    mainMenu("","");
                    break;
            }

        }while (!option.equalsIgnoreCase("/q"));
    }


    private void viewListOfMemberWithCurrentLateReturnBooks(){
        System.out.println("\n -------- MEMBER THAT ARE LATE TO RETURN BOOK(S)------");
        for (Loan l: loan_service.lateReturnMembers()){
            System.out.println(l);
        }
    }

    private void viewLoansHistoryOfAMember(){
        System.out.print("\nEnter the SSN of the member you would like to view the loan history > ");
        String ssn = AppHelper.userInput.nextLine();
        loan_service.memberLoanArchives(ssn);
    }

    private void viewCurrentBooksIssuedToAMember(){
        System.out.print("\n\nEnter the SSN of the member you would like to view current issued book > ");
        String ssn = AppHelper.userInput.nextLine();
        if (!validateSSN(ssn)){
            System.out.println("SORRY THIS MEMBER DOES NOT EXIST!!!");
        }else {
            List<Loan>memberLoans = new ArrayList<>();
            for (Loan loan: loan_service.getAll()){
                if (loan.getMemberSSN().equals(ssn)){
                    memberLoans.add(loan);
                }
            }
            if (memberLoans.size() >= 1){
                for (Loan l: memberLoans){
                    System.out.println(l);
                }
            }else{
                System.out.println("This member has not current loan recorded!\n");
            }
        }

    }

    private void editMemberInfo() throws InterruptedException {
        String option = null;
        System.out.println("\n\n      ------------ EDIT MEMBER ---------");
        System.out.print(" 1 > Edit member's phone number.");
        System.out.print(" \n2 > Edit member's address.");
        System.out.print("\n/q > RETURN");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("\n\nChoose what property to edit > ");
        option = AppHelper.userInput.nextLine();
        switch (option){
            case "1":
                editPhoneNum();
                break;
            case "2":
                editAddress();
                break;
            case "/q":
                membersManagement();
                break;
            default:
                System.err.println("Wrong value provided! Try again please.");
                editMemberInfo();
                break;
        }
    }


    private void editPhoneNum() throws InterruptedException {
        String propertyToEdit1 = "PhoneNumber";
        System.out.print("\nEnter the SSN of the member you would like to editInfo > ");
        String ssn = AppHelper.userInput.nextLine();
        if (member_service.find(ssn) != null){
            System.out.println("\nCorresponding "+member_service.find(ssn));
            System.out.print("\nNow enter the new phone number value > ");
            String newPhoneNum = AppHelper.userInput.nextLine();
            System.out.println("\nWait please ...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!AppHelper.isNumeric(newPhoneNum) || newPhoneNum.length() > AppHelper.MAX_PHONE_NUMBER_LENGTH || newPhoneNum.length() < AppHelper.MIN_PHONE_NUMBER_LENGTH){
                System.err.println("\nERROR!!! Phone number must be numeric and " + "must be between " + AppHelper.MIN_PHONE_NUMBER_LENGTH + " " + "to " + AppHelper.MAX_PHONE_NUMBER_LENGTH + ".");
                editMemberInfo();
            }else {
                member_service.editInfo(propertyToEdit1,ssn,newPhoneNum);
                System.out.println("\nINFO!!!! The phone has been successfully altered.\n\n");
                editMemberInfo();
            }
        }else {
            System.out.println("\nSORRY! There is no member with the provided SSN. Try again");
            editPhoneNum();
        }
    }


    private void editAddress() throws InterruptedException {
        String propertyToEdit1 = "Address";
        System.out.print("\nEnter the SSN of the member you would like to editInfo > ");
        String ssn = AppHelper.userInput.nextLine();
        if (member_service.find(ssn) != null){
            System.out.println("\nCorresponding "+member_service.find(ssn));
            System.out.print("\nEnter the new address value > ");
            String newAddress = AppHelper.userInput.nextLine();
            System.out.println("\nWait please ...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!AppHelper.containsLettersAndDigits(newAddress)){
                System.err.println("\nWARNING!!! ERROR!!! Address must contains letters and digits.");
                editMemberInfo();
            }else {
                member_service.editInfo(propertyToEdit1,ssn,newAddress);
                System.out.println("\nINFO!!!! The Address has been successfully altered.\n\n");
                editMemberInfo();
            }

        }else {
            System.out.println("\nSORRY! There is no member with the provided SSN. Try again");
            editAddress();
        }
    }

    public void ViewAllMemberRegisterToOurLibrary(){
        System.out.println("\n------- REGISTERED MEMBERS ------");
        List<Member> members = member_service.getAll();
        for (Member m: members){
            System.out.println(m);
        }
    }


    private void removeMemberFromSystem() throws InterruptedException {
        System.out.println("\n---------- CURRENT MEMBERS --------");
        for (Member m: member_service.getAll()){
            System.out.println("[MEMBER] NAME: "+m.getFirstName()+" "+ m.getLastName()+"     SSN: "+m.getSsn());
        }
        System.out.print("\nEnter the SSN of the member to remove > ");
        String ssn = AppHelper.userInput.nextLine();
        if (validateSSN(ssn)){
            if (memberHasBook(ssn)){
                System.out.println("'**'This member still have one or more book\n    in his possession.So he can't be remove\n        for now.Make sure a member to check loans\n             list before trying to remove a member.'**'");
                membersManagement();
            }
            else {
                member_service.getAll().removeIf((Member m) -> m.getSsn().equals(ssn));
                System.out.println("\nMEMBER WITH SSN [ "+ssn+" ] REMOVED!!!\n");
            }

        }else System.out.println("The SSN you have provided is not valid!");
    }

    private boolean memberHasBook(String ssn){
        for (Loan l: loan_service.getAll()){
            if (l.getMemberSSN().equals(ssn)){
                return true;
            }
        }
        return false;
    }


    private void extendDueDate() throws InterruptedException {
        System.out.println("\n ----------- ALL BOOKS CURRENTLY ON LOAN --------");
        for (Loan l: loan_service.getAll()){
            System.out.println(l);
        }
        System.out.println("\n");
        System.out.print("\nEnter the book ISBN of the loan you would like to extend > ");
        String isbn = AppHelper.userInput.nextLine();
        for (Loan l: loan_service.getAll()){
            if (l.getISBN_number().equals(isbn)){
                System.out.println("\nProcessing...");
                Thread.sleep(1000);
                if (!isExtendable(l)){
                    String temp = l.getDueDate();
                    l.setIssueDate(AppHelper.ISSUE_DATE());
                    l.setDueDate(AppHelper.EXTENDED_DUE_DATE());
                    System.out.println("\nYou loan have been successfully extended from ["+temp+"] till ["+l.getDueDate()+"]");
                }else {
                    System.out.println("\nSorry! this loan has expired on ["+l.getDueDate()+"].\n             " +
                            " It's therefore cannot be extended anymore.\n" +
                            "                    You much return it first before you can borrow again.\n" +
                            "                             NB: To be able to extend a loan, you must apply for \n" +
                            "                                 extension before the initial return Due date.");
                }
            }
        }
    }

    private boolean isExtendable(Loan l){
        return AppHelper.APPLY_LATE_RETURN_FEE(l);
    }


    private boolean isLoanExit(String isbn){
        return loan_service.getAll().contains(loan_service.find(isbn));
    }



    public void issueBookToAMember(){
        System.out.println("\n ----------- ISSUE A BOOK --------");
        book_service.availableBooks();

        System.out.print("\nChoose a book by entering it ISBN > ");
        String isbn = AppHelper.userInput.nextLine();
        System.out.print("\nChoose the member to issue the book to by entering his/her SSN > ");
        String ssn = AppHelper.userInput.nextLine();

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

    }

    private void takeBackBookFromMember() throws InterruptedException {
        System.out.println("\n ----------- ALL BOOKS CURRENTLY ON LOAN --------");
        for (Loan l: loan_service.getAll()){
            System.out.println(l);
        }
        System.out.println("\n");

        System.out.print("\nEnter the book ISBN you are getting back > ");
        String isbn = AppHelper.userInput.nextLine();
        System.out.print("\nEnter the member's SSN > ");
        String ssn = AppHelper.userInput.nextLine();
        if (isLoanExit(isbn)){
            System.out.print("\nGet this book back? y/n > ");
            String answer = AppHelper.userInput.nextLine();
            if (answer.equalsIgnoreCase("y")){

                for (Member m: member_service.getAll()){
                    if (m.getSsn().equals(loan_service.find(isbn).getMemberSSN())){
                        m.setAccountBalance(10);
                    }
                }

                loan_service.terminateLoan(isbn,ssn);
                Book b = book_service.find(isbn);
                b.setNumberOfCopy("1",1);
                b.setStatus(b.getNumberOfCopy());
                membersManagement();
            }else {
                System.out.println("\nWait a moment...");
                Thread.sleep(1000);
                membersManagement();
            }

        }else {
            System.out.println("\nCorresponding loan details couldn't be found.\n        Check the existing loan list and Try again");
            membersManagement();
        }

    }

    private void addNewMemberToLibrarySystem() throws InterruptedException {
        String data;
        Member member = new Member();

        System.out.println("\n --------- ADD NEW MEMBER -----");
        do {
            System.out.print("\nEnter member's FirstName: ");
            data = AppHelper.userInput.nextLine();
        }while (!isOnlyLetters(data));
        member.setFirstName(data);

        do {
            System.out.print("\nEnter member's LastName: ");
            data = AppHelper.userInput.nextLine();
        }while (!isOnlyLetters(data));
        member.setLastName(data);

        do {
            System.out.print("\nEnter member's SSN: ");
            data = AppHelper.userInput.nextLine();
        }while (ssnAlreadyExist(data) || !AppHelper.isNumeric(data));
        member.setSsn(data);

        do {
            System.out.print("\nEnter member's HomeAddress: ");
            data = AppHelper.userInput.nextLine();
        }while (!isLettersAndDigits(data));
        member.setHomeAddress(data);

        do {
            System.out.print("\nEnter member's PhoneNumber: ");
            data = AppHelper.userInput.nextLine();
        }while (!AppHelper.isNumeric(data));
        member.setPhoneNumber(data);

        do {
            System.out.print("\nEnter member's EmailAddress: ");
            data = AppHelper.userInput.nextLine();
        }while (isEmailExist(data));
        member.setEmailAddress(data);

        do {
            System.out.print("\nEnter member's Password: ");
            data = AppHelper.userInput.nextLine();
        }while (!isLettersAndDigits(data));
        member.setPassword(data);

        System.out.println("\nRegistration processing...");
        Thread.sleep(1500);

        member_service.add(member);
        System.out.println("\n"+member.getFirstName()+" "+member.getLastName()+" have been successfully registered!\n");

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

    private boolean ssnAlreadyExist(String x){
        for (Member m:member_service.getAll()){
            if (m.getSsn().equals(x)){
                System.out.println("A member with SSN ["+x+"] have already been registered!");
                return true;
            }
        }
        return false;
    }

    private boolean isbnAlreadyExist(String x){
        for (Book m:book_service.getAll()){
            if (m.getISBN_number().equals(x)){
                System.out.println("A book with ISBN ["+x+"] have already been registered!");
                return true;
            }
        }
        return false;
    }

    private boolean isEmailExist(String x){
        for (Member m:member_service.getAll()){
            if (m.getEmailAddress().equals(x)){
                System.out.println("This email already exist in the system!");
                return true;
            }
        }
        return false;
    }

    private boolean isOnlyLetters(String str){
        if (!AppHelper.containsOnlyLetters(str)){
            System.out.println("Data provided must be only letters!");
            return false;
        }return true;
    }

    private boolean isLettersAndDigits(String str){
        if (!AppHelper.containsLettersAndDigits(str)){
            System.out.println("Data provided must be letters and digits!");
            return false;
        }return true;
    }



}
