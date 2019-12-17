package com.company.controller;

import LIBRARY.PROJECT.AppHelper;
import LIBRARY.PROJECT.interfaces.I_Menu;
import LIBRARY.PROJECT.models.Book;
import LIBRARY.PROJECT.models.Member;
import LIBRARY.PROJECT.services.Book_Service;
import LIBRARY.PROJECT.services.Member_Service;


public class Library_Admin_Controller implements I_Menu {
private Member_Service member_service = new Member_Service();
private Book_Service book_service = new Book_Service();


 @Override
 public void mainMenu() throws InterruptedException {
     System.out.println("\n\n\n  **********************************************\n                     LIBRARY MENU\n    ******************************************");
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
                     mainMenu();
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
             mainMenu();
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
     }

 }



 private void booksManagement() throws InterruptedException {
     String option = null;
     do {
         System.out.println("\n\n-------------------------------------------------------    ");
         System.out.println("           ****** BOOK MANAGEMENT MENU *****");
         System.out.print("          1 -----> View all available books");
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
                 System.out.println("NO YET IMPLEMENTED!");
                 break;
             case "4":
                 book_service.getOrderedBooksByTitles();
                 break;
             case "5":
                 searchBook();
                 break;
             case "6":
                 System.out.println("NO YET IMPLEMENTED!");
                 break;
             case "7":
                 System.out.println("NO YET IMPLEMENTED!");
                 break;
             case "8":
                 System.out.println("NO YET IMPLEMENTED!");
                 break;
             case "/q":
                 mainMenu();
                 break;
             default:
                 mainMenu();
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

 private void searchBook() throws InterruptedException {
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












 /*********************************************************************************************/
           /***********************************************************************/
                      /**************************************************/
                             /***************************************/
                                /********************************/

                                     //MEMBERS

 private void membersManagement() throws InterruptedException {
     String option = null;
     do {
         System.out.println("\n\n ------------------------------------------------------------------------");
         System.out.println("            ******************* MEMBER MANAGEMENT MENU ****************");
         System.out.print("           1 ------> View the list of all registered members of the library.");
         System.out.print("\n         2 ------> Add a member to the system");
         System.out.print("\n         3 ------> Members that have not returned book(s) after the due date.");
         System.out.print("\n         4 ------> Issue a book to the member");
         System.out.print("\n         5 ------> Take the book returned from the member");
         System.out.print("\n         6 ------> Renew the book for the member");
         System.out.print("\n         7 ------> View all the books issued to the member");
         System.out.print("\n         8 ------> View the history of books issued to the member previously");
         System.out.print("\n         9 ------> Edit member's info");
         System.out.print("\n        10 ------> Remove the member from the system");
         System.out.print("\n        /q ------> RETURN");
         System.out.println("\n ------------------------------------------------------------------------");
         System.out.print("Option > ");

         option = AppHelper.userInput.nextLine();
         switch (option){
             case "1":
                 getAllMembers();
                 break;
             case "2":
                 System.out.println("NO YET IMPLEMENTED!");
                 break;
             case "3":
                 System.out.println("NO YET IMPLEMENTED!");
                 break;
             case "4":
                 System.out.println("NO YET IMPLEMENTED!");
                 break;
             case "5":
                 System.out.println("NO YET IMPLEMENTED!");
                 break;
             case "6":
                 System.out.println("NO YET IMPLEMENTED!");
                 break;
             case "7":
                 System.out.println("NO YET IMPLEMENTED!");
                 break;
             case "8":
                 System.out.println("NO YET IMPLEMENTED!");
                 break;
             case "9":
                 System.out.println("NO YET IMPLEMENTED!");
                 break;
             case "10":
                 System.out.println("NO YET IMPLEMENTED!");
                 break;
             case "/q":
                 mainMenu();
                 break;
             default:
                 mainMenu();
                 break;
         }

     }while (!option.equalsIgnoreCase("/q"));
 }



 private void getAllMembers(){
     System.out.println("\n------- REGISTERED MEMBERS ------");
     for (Member m: member_service.getAll()){
         System.out.println(m);
     }
     //private void
 }





}
