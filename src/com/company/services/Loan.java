package com.company.services;

import com.company.AppHelper;
import com.company.interfaces.I_Services;
import com.company.models.Loan;
import com.company.models.Member;
import com.company.repositories.Data_Repository;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Loan_Service implements I_Services<Loan> {
    private List<Loan> onGoingLoans;
    private List<Loan> loansHistory;



    public Loan_Service() {
        Data_Repository repo = new Data_Repository();
        onGoingLoans = new ArrayList<>();
        loansHistory = repo.getLoansHistory();
    }

    public List<Loan> lateReturnLoans(){
        System.out.println("\n -------- CURRENT LATE RETURN BOOK ------");
        List<Loan> lateReturns = new ArrayList<>();
        for (Loan l:onGoingLoans){
            if (isFeeApplies(l)){
                lateReturns.add(l);
            }
        }
        return lateReturns;
    }


    public List<Loan> lateReturnMembers(){
        List<Loan> lateReturnsMembers = new ArrayList<>();
        for (Loan l: onGoingLoans){
            if (isFeeApplies(l)){
                lateReturnsMembers.add(l);
            }
        }
        return lateReturnsMembers;
    }

    private boolean isFeeApplies(Loan l){
        return AppHelper.APPLY_LATE_RETURN_FEE(l);
    }

    public void terminateLoan(String bookISBN, String memberSSN) throws InterruptedException {
        Member_Service member_service = new Member_Service();
        Member m = member_service.find(memberSSN);
        for (Loan l:getAll()){
            System.out.println(l);
        }

        archiveLoan(find(bookISBN));
        System.out.println("\nBook return processing...");
        Thread.sleep(1500);
        System.out.println("\nThe book with title '"+find(bookISBN).getBookTitle()+"' is now available.\n");
        Thread.sleep(500);
        System.out.println("\nChecking if fees applies...");
        Thread.sleep(1000);
        Loan loan = find(bookISBN);
        if (isFeeApplies(loan)){
            System.out.println("You were supposed to return the book ["+loan.getDueDate()+"].\n" +
                    "                    But you returning it ["+new Date()+"]\n" +
                    "                                Your library card will then be debited the sum of 10Kr");
        }else {
            System.out.println("\nBook returned in time.\n");
        }

        onGoingLoans.removeIf((Loan l) -> l.getISBN_number().equals(bookISBN));

    }

    public void memberLoanArchives(String member_ssn){
        System.out.println("\n-------- LOAN HISTORY FOR "+member_ssn.toUpperCase()+" -------");
        for (Loan l: loansHistory){
            if (l.getMemberSSN().equals(member_ssn)){
                System.out.println(l);
            }
        }

    }


    private void archiveLoan(Loan l) {
        l.setReturnDate("Returned");
        loansHistory.add(l);
        try {
            FileOutputStream fileOut = new FileOutputStream(AppHelper.HISTORY_FILENAME);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(loansHistory);
            out.close();
            fileOut.close();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Loan> getAll() {
        return onGoingLoans;
    }

    @Override
    public void add(Loan obj) {
        onGoingLoans.add(obj);
    }

    @Override
    public void removeObj(String unique_identifier) {
        getAll().removeIf((Loan l) -> l.getISBN_number().equals(unique_identifier));
    }

    @Override
    public Loan find(String unique_identifier) {
        if (onGoingLoans != null)
            for (Loan m: onGoingLoans){
                if (m.getISBN_number().equals(unique_identifier))
                    return m;
            }
        System.out.println("NOT FOUND!!!");
        return null;
    }

    @Override
    public void editInfo(String propertyToEdit, String id, String value1) {

    }
}
