package com.company.models;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Loan extends Book implements Serializable {
    private static DateFormat df = new SimpleDateFormat("YYYY-MM-dd  HH:mm:ss");
    private String issueDate;
    private String  dueDate;
    private String returnDate;
    private String memberSSN;
    private boolean feesApply;


}
