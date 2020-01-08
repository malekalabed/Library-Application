package com.company;


import com.company.models.Loan;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class AppHelper <T> {
    private static DateFormat df = new SimpleDateFormat("YYYY-MM-dd  HH:mm:ss");
    private static final double LATE_RETURN_FEE = 10;
    private static final int maxNumberOfDay = 60;
    public static final int MIN_NAME_LENGTH = 2;
    public static final int MAX_NAME_LENGTH = 25;
    public static final int MAX_SSN_LENGTH = 12;
    public static final int MIN_PASSWORD_LENGTH = 3;
    public static final int MAX_PASSWORD_LENGTH = 16;
    public static final int MIN_PHONE_NUMBER_LENGTH = 6;
    public static final int MAX_PHONE_NUMBER_LENGTH = 12;
    public static final String HISTORY_FILENAME = "loans_archives.ser";
    public static final String ADMINS_FILENAME = "system_admins.ser";
    public static final String MEMBERS_CREDENTIALS_FILENAME = "members_credentials_details.ser";
    public static final String MEMBERS_FILENAME = "members.ser";
    public static final String SYSTEM_PASSWORD = "#XWZADAMIN2019";
    public static final Scanner userInput = new Scanner(System.in);


    public static String ISSUE_DATE() {
        return df.format(new Date());
    }

    public static String DUE_DATE() {

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.SECOND, maxNumberOfDay);
        return df.format(c.getTime());
    }

    public static boolean APPLY_LATE_RETURN_FEE(Loan loan) {
        //return DUE_DATE().compareTo(df.format(new Date())) < 0;
        return loan.getDueDate().compareTo(df.format(new Date())) < 0;
    }

    public static String EXTENDED_DUE_DATE() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.SECOND, maxNumberOfDay);
        return df.format(c.getTime());
    }

    public static void SMOOTH_EXIT() {
        System.out.println("\nThank for your time and till next time.\n                BYE.\n\n");
        System.exit(1);
    }


    public static boolean isNumeric(String strNum) {
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            System.out.println("Data provided must contain only digits!");
            return false;
        }
        return true;
    }


//    public static boolean stringIsNumeric(String str) {
//        return str.matches("[0-9]+");
//    }

    public static boolean containsOnlyLetters(String str) {
        return str.matches("[a-zA-Z]+");
    }


    public static boolean containsLettersAndDigits(String s) {
        String n = ".*[0-9].*";
        String a = ".*[a-z].*";
        return s.matches(n) && s.matches(a);
    }


    public enum Category {
        NOVEL, FAIRYTALE, DRAMA, TECH, CLASSIC, MATHS, HISTORICAL, COMPUTERSCIENCE
    }

    public enum Languages {
        ENGLISH, FRENCH, GERMAN, RUSSIAN, SWEDISH
    }


    public static boolean validCategory(String choice) {
        return (choice.equalsIgnoreCase("NOVEL") || choice.equalsIgnoreCase("FAIRYTALE") || choice.equalsIgnoreCase("DRAMA") || choice.equalsIgnoreCase("TECH") || choice.equalsIgnoreCase("CLASSIC") || choice.equalsIgnoreCase("MATHS") || choice.equalsIgnoreCase("HISTORICAL") || choice.equalsIgnoreCase("COMPUTERSCIENCE"));
    }

}


//        booksList.add(new Book("Web Design. the Evolution of the Digital World 1990-Today",authors[15],"125","English","TASCHEN (December 7, 2019)",String.valueOf(AppHelper.Category.TECH),"640","AVAILABLE"));
//        booksList.add(new Book("The Overstory",authors[16],"126","English","W. W. Norton & Company; Reprint edition (April 2, 2019)",String.valueOf(AppHelper.Category.NOVEL),"512","AVAILABLE"));
//        booksList.add(new Book("All the Light We Cannot See",authors[17],"127","English","Scribner; Reprint edition (April 4, 2017)",String.valueOf(AppHelper.Category.NOVEL),"544","AVAILABLE"));
//        booksList.add(new Book("Artificial Intelligence, Healthcare and the Law",authors[13],"123","German","Routledge; 1 edition (November 21, 2019)",String.valueOf(AppHelper.Category.TECH),"276","AVAILABLE"));
//        booksList.add(new Book("Hamlet",authors[8],"118","English","Linkgua Ediciones 1/1/2014 (2014)",String.valueOf(AppHelper.Category.DRAMA),"197","AVAILABLE"));
//        booksList.add(new Book("Waiting for Godot: A Tragicomedy in Two Acts",authors[9],"119","German","Faber & Faber",String.valueOf(AppHelper.Category.DRAMA),"288","AVAILABLE"));
//        booksList.add(new Book("The Oresteia: Agamemnon, The Libation Bearers, The Eumenides",authors[10],"120","English","Penguin Classics",String.valueOf(AppHelper.Category.DRAMA),"310","AVAILABLE"));
//        booksList.add(new Book("Lord of the Flies",authors[2],"112","English","Penguin Books (2003)",String.valueOf(AppHelper.Category.CLASSIC),"411","AVAILABLE"));
//        booksList.add(new Book("ANALOG Science Fiction/ Science Fact",authors[3],"113","French","Davis Publications, NY, 1981",String.valueOf(AppHelper.Category.CLASSIC),"119","AVAILABLE"));
//        booksList.add(new Book("Treasure Island",authors[4],"114","German","Bantam Books # 142, dated February ",String.valueOf(AppHelper.Category.CLASSIC),"610","AVAILABLE"));
//        booksList.add(new Book("Real West, True Tales of the American Frontier",authors[5],"115","English","Charlton Publications, Inc, Derby, Connecticut, U.S.A., 1972",String.valueOf(AppHelper.Category.CLASSIC),"417","AVAILABLE"));
//        booksList.add(new Book("Oliver Twist",authors[6],"116","English","Random House Books for Young R (1990)",String.valueOf(AppHelper.Category.CLASSIC),"320","AVAILABLE"));
