import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class AppHelper<T> {
    private static DateFormat df = new SimpleDateFormat("YYYY-MM-dd  HH:mm:ss");
    public static final Date currentDate = new Date();
    private static final int maxNumberOfDay = 5;
    public static final int MIN_PHONE_NUMBER_LENGTH = 6;
    public static final int MAX_PHONE_NUMBER_LENGTH = 12;
    public static final String HISTORY_FILENAME = "books_loan_history.ser";
    public static final Scanner userInput = new Scanner(System.in);


    public static String ISSUE_DATE() {
        return df.format(currentDate);
    }

    public static String DUE_DATE() {

        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.SECOND, maxNumberOfDay);
        return df.format(c.getTime());
    }


    public static String EXTENDED_DUE_DATE() {
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
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
