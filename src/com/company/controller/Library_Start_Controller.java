package com.company.controller;

import com.company.AppHelper;
import com.company.models.Admin;
import com.company.models.Member;
import com.company.services.Admin_Service;
import com.company.services.Member_Service;

public class Library_Start_Controller{

    private Member_Service member_service = new Member_Service();
    private Admin_Service admin_service = new Admin_Service();
    private Library_Admin_Controller admin_controller = new Library_Admin_Controller();
    private Library_Member_Controller member_controller = new Library_Member_Controller();
    private String username;

    public void mainMenu() throws InterruptedException {

        System.out.println("\n               WELCOME TO OUR LIBRARY MANAGEMENT SYSTEM\n\n");

        String option;
        do {
            System.out.println("\n**************** LOGIN ***************\n");
            System.out.println(" 1 > Login as Admin.\n 2 > Login as Member.\n 3 > Create account");
            System.out.println("/q > EXIT THE PROGRAM\"");
            System.out.print("\nChoose an option to start > ");
            option = AppHelper.userInput.nextLine();

            switch (option) {
                case "1":
                    Admin admin = new Admin();
                    System.out.print("\n------- LOGIN AS ADMIN ------\n");
                    System.out.print("\nEnter your social security number > ");
                    String ssn = AppHelper.userInput.nextLine();
                    admin.setSsn(ssn);
                    Thread.sleep(300);
                    System.out.print("Enter your password > ");
                    String password = AppHelper.userInput.nextLine();
                    admin.setPassword(password);
                    Thread.sleep(300);
                    System.out.print("Enter your admin's code > ");
                    String adminCode = AppHelper.userInput.nextLine();
                    admin.setAdminCode(adminCode);
                    System.out.println("\nPlease wait for verifications. This might take few second...");
                    Thread.sleep(1500);

                    if (admin_service.userAuthentication(admin)) { // Authentication process
                        Member m = member_service.find(ssn);
                        String userName = m.getFirstName()+" "+m.getLastName();
                        String userSSN = m.getSsn();
                        admin_controller.mainMenu(userName,userSSN);
                    } else {
                        System.out.println("\nSORRY! but we couldn't find any record matching your login info." + "\nTry again or Create an account.\n");
                        mainMenu();
                    }
                    break;
                case "2":
                    Member member = new Member();
                    System.out.print("\n------- LOGIN AS MEMBER ------\n");
                    System.out.print("\nEnter your social security number > ");
                    String memberSSn = AppHelper.userInput.nextLine();
                    member.setSsn(memberSSn);
                    Thread.sleep(500);
                    System.out.print("Enter your password > ");
                    String memberPassword = AppHelper.userInput.nextLine();
                    member.setPassword(memberPassword);
                    System.out.println("\nPlease wait for verifications. This might take few second...");
                    Thread.sleep(1500);
                    if (member_service.userAuthentication(member)) {
                        //Member gets access
                        Member m = member_service.find(memberSSn);
                        String userName = m.getFirstName()+" "+m.getLastName();
                        String userSSN = m.getSsn();
                        member_controller.mainMenu(userName,userSSN);
                    } else {
                        System.out.println("\nSORRY! but we couldn't find any record matching your login info." + "\nTry again or Create an account.\n");
                        mainMenu();
                    }
                    break;
                case "3":
                    try {
                        createAccount();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case "4":
                    exit();
                    break;
                default:
                    while (!option.equalsIgnoreCase("1") || !option.equalsIgnoreCase("2") || !option.equalsIgnoreCase("3") || !option.equalsIgnoreCase("4")) {

                        System.out.println("ERROR!!!! The value you have entered was wrong. Choose among below options please.");
                        Thread.sleep(1000);
                        System.out.println("Wait a moment please. You are being redirected to login screen again ...\n");
                        Thread.sleep(3000);

                        mainMenu();
                    }
            }

        } while (option.equalsIgnoreCase("4"));

    }

    private void createAccount() throws InterruptedException {
       /* String command = null;

        Member credMember;
        Member member = new Member();
        System.out.print("\n------- CREATE NEW ACCOUNT ------\n");
        do {
            System.out.print("\nWrite '/q' if you want to abandon.\n     Enter your social security number > ");
            command = AppHelper.userInput.nextLine();
            *//*if (command.equalsIgnoreCase("Done")) {
                mainMenu();
            }*//*
                System.out.println("ERROR!!! Social security number must be numeric " + "and at most " + AppHelper.MAX_SSN_LENGTH + " characters.");

            }while (!AppHelper.isNumeric(command));

                Thread.sleep(500);
                System.out.print("\nWrite '/q' if you want to abandon.\n     Enter your social security number > ");
                command = AppHelper.userInput.nextLine();

            member.setSsn(command);
            Thread.sleep(500);
           *//* while (AppHelper.isNumeric(command) || command.length() <= AppHelper.MAX_SSN_LENGTH) {

            }*//*


            System.out.print("\\nWrite '/q' if you want to abandon.\n     Enter your FirstName > ");
            command = AppHelper.userInput.nextLine();
            if (command.equalsIgnoreCase("done")) {
                mainMenu();
            }
            while (!AppHelper.containsOnlyLetters(command) || command.length() >= AppHelper.MAX_NAME_LENGTH || command.length() <= AppHelper.MIN_NAME_LENGTH) {

                System.err.println("ERROR!!! FirstName can't have other characters than " + "letters and must be between " + AppHelper.MIN_NAME_LENGTH + " " + "to " + AppHelper.MAX_NAME_LENGTH + " letters");

                Thread.sleep(500);
                System.out.print("Write '/q' if you want to abandon.\n     Enter your FirstName > ");
                command = AppHelper.userInput.nextLine();
            }
            member.setFirstName(command);
            Thread.sleep(500);


            System.out.print("Write '/q' if you want to abandon.\n     Enter your LastName > ");
            command = AppHelper.userInput.nextLine();
            if (command.equalsIgnoreCase("Done")) {
                mainMenu();
            }
            while (!AppHelper.containsOnlyLetters(command)) {

                System.err.println("ERROR!!! LastName can't have other characters than " + "letters and must be between " + AppHelper.MIN_NAME_LENGTH + " " + "to " + AppHelper.MAX_NAME_LENGTH + ".");

                Thread.sleep(500);
                System.out.print("Write '/q' if you want to abandon.\n     Enter your LastName > ");
                command = AppHelper.userInput.nextLine();
            }
            member.setLastName(command);
            Thread.sleep(500);


            System.out.print("Write '/q' if you want to abandon.\n     Enter your Address > ");
            command = AppHelper.userInput.nextLine();
            if (command.equalsIgnoreCase("Done")) {
                mainMenu();
            }
            member.setHomeAddress(command);
            Thread.sleep(500);


            System.out.print("Write '/q' if you want to abandon.\n     Enter your PhoneNumber > ");
            command = AppHelper.userInput.nextLine();
            if (command.equalsIgnoreCase("Done")) {
                mainMenu();
            }
            while (!AppHelper.isNumeric(command) || command.length() < AppHelper.MIN_PHONE_NUMBER_LENGTH || command.length() > AppHelper.MAX_PHONE_NUMBER_LENGTH) {

                System.err.println("ERROR!!! Phone number must be numeric and " + "must be between " + AppHelper.MIN_PHONE_NUMBER_LENGTH + " " + "to " + AppHelper.MAX_PHONE_NUMBER_LENGTH + ".");

                Thread.sleep(500);
                System.out.print("Write '/q' if you want to abandon.\n     Enter your PhoneNumber > ");
                command = AppHelper.userInput.nextLine();
            }
            member.setPhoneNumber(command);
            Thread.sleep(500);


            System.out.print("Write '/q' if you want to abandon.\n     Enter your Password > ");
            command = AppHelper.userInput.nextLine();
            if (command.equalsIgnoreCase("Done")) {
                mainMenu();
            }
            while (!AppHelper.containsLettersAndDigits(command) || command.length() < AppHelper.MIN_PASSWORD_LENGTH || command.length() > AppHelper.MAX_PASSWORD_LENGTH) {

                System.err.println("ERROR!!! Password must contains letters and at least one digits and " + "must be between " + AppHelper.MIN_PASSWORD_LENGTH + " " + "to " + AppHelper.MAX_PASSWORD_LENGTH + ".");

                Thread.sleep(500);
                System.out.print("Write 'q/' if you want to abandon.\n     Enter your Password > ");
                command = AppHelper.userInput.nextLine();
            }
            member.setPassword(command);
            Thread.sleep(500);


            System.out.println("\n----- DETAILS PROVIDED ---");
            System.out.println(member);

            System.out.print("\nDo you want to save y/n > ");
            command = AppHelper.userInput.nextLine();
            if (command.equalsIgnoreCase("/q")) {
                mainMenu();
            }
            if (command.equalsIgnoreCase("y")) {
                member_service.add(member);
                credMember = new Member(member.getSsn(), member.getPassword());
                member_service.saveCredentials(credMember);
                System.out.println(member.getFirstName() + " your account have been successful created!!!");
                mainMenu();
            } else {
                mainMenu();
            }

        } while (!command.equalsIgnoreCase("/q "));*/

    }


    public void exit() {
        String answer;
        System.out.print("\nThe program will exit.\n              Are you sure is that you want? y/n > ");
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
