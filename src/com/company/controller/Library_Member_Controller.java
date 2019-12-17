package com.company.controller;

import LIBRARY.PROJECT.AppHelper;
import LIBRARY.PROJECT.interfaces.I_Menu;
import LIBRARY.PROJECT.services.Authentication_Services;
import LIBRARY.PROJECT.services.Book_Service;
import LIBRARY.PROJECT.services.Member_Service;


public class Library_Member_Controller implements I_Menu {

    private Member_Service member_service = new Member_Service();
    private Book_Service book_service = new Book_Service();


    @Override
    public void mainMenu() {
        String option = null;
        Authentication_Services auth = new Authentication_Services();
        System.out.println("\n\n                      **************\n   WELCOME TO THE LIBRARY MEMBERS SERVICE ");
        do {
            System.out.println("        1 -----> Books available in the library.");
            System.out.println("        2 -----> Search for a particular book.");
            System.out.println("        3 -----> View the books issued to you.");
            System.out.println("        4 -----> Put a request for a new book.");
            System.err.println("        /q ----> RETURN");

            System.out.print("Choose an option to continue ");

            option = AppHelper.userInput.nextLine();
            switch (option){
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "/q":
                    exit();
                    break;
                    default:
                        break;
            }

        }while (option.equals("/q"));
    }

    @Override
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
            mainMenu();
        }

    }
}
