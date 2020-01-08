package com.company.repositories;

import com.company.AppHelper;
import com.company.models.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Data_Repository {

    private List<Book> booksList;
    private List<Member> membersList;
    private List<Loan> loansHistory;
    private List<Admin> adminsList;
    private List<Member> registeredMembersList;


    public Data_Repository() {

        booksList = new ArrayList<>();
        membersList = new ArrayList<>();
        adminsList = new ArrayList<>();
        loansHistory = new ArrayList<>();
        registeredMembersList = new ArrayList<>();


        ArrayList<ArrayList<Author>> authors = new ArrayList<>();


        ArrayList<Author>bookAuthor1 = new ArrayList<>();
        bookAuthor1.add(new Author("Barbara O'Neal"));

        ArrayList<Author>bookAuthor2 = new ArrayList<>();
        bookAuthor2.add(new Author("Miguel de Cervantes Saavedra"));

        ArrayList<Author>bookAuthor3 = new ArrayList<>();
        bookAuthor3.add(new Author("J Ellen Gainor"));

        ArrayList<Author>bookAuthor4 = new ArrayList<>();
        bookAuthor4.add(new Author("Stanton B Garner"));

        ArrayList<Author>bookAuthor5 = new ArrayList<>();
        bookAuthor5.add(new Author("Sophocles"));

        ArrayList<Author>bookAuthor6 = new ArrayList<>();
        bookAuthor6.add(new Author("Richard Powers"));

        ArrayList<Author>bookAuthor7 = new ArrayList<>();
        bookAuthor7.add(new Author("Anthony Doerr"));

        ArrayList<Author>bookAuthor8 = new ArrayList<>();
        bookAuthor8.add(new Author("Hampton Sides"));

        ArrayList<Author>bookAuthor9 = new ArrayList<>();
        bookAuthor9.add(new Author("Brian Kilmeade"));
        bookAuthor9.add(new Author("Don Yaeger"));

        ArrayList<Author>bookAuthor10 = new ArrayList<>();
        bookAuthor10.add(new Author("Julie Sway"));

        ArrayList<Author>bookAuthor11 = new ArrayList<>();
        bookAuthor11.add(new Author("Mr. Kevin P Hare"));
        bookAuthor11.add(new Author("Mr. Pindar Van Arman"));


        authors.add(bookAuthor1);
        authors.add(bookAuthor2);
        authors.add(bookAuthor3);
        authors.add(bookAuthor4);
        authors.add(bookAuthor5);
        authors.add(bookAuthor6);
        authors.add(bookAuthor7);
        authors.add(bookAuthor8);
        authors.add(bookAuthor9);
        authors.add(bookAuthor10);
        authors.add(bookAuthor11);


        booksList.add(new Book("When We Believed in Mermaids",bookAuthor1,"110","Swedish","Lake Union Publishing (July 16, 2019)",String.valueOf(AppHelper.Category.NOVEL),"348"));
        booksList.add(new Book("Don Quixote ",bookAuthor2,"117","German","Linkgua Ediciones 1/1/2014 (2014)",String.valueOf(AppHelper.Category.CLASSIC),"265"));
        booksList.add(new Book("The Norton Anthology of Drama",bookAuthor3,"121","English","W. W. Norton & Company",String.valueOf(AppHelper.Category.DRAMA),"123"));
        booksList.add(new Book("Antigone",bookAuthor4,"122","Swedish","Facsimile Publisher",String.valueOf(AppHelper.Category.DRAMA),"209"));
        booksList.add(new Book("Agricultural Internet of Things and Decision Support for Smart Farming",bookAuthor5,"124","French","Academic Press; 1 edition (January 15, 2020)",String.valueOf(AppHelper.Category.TECH),"380"));
        booksList.add(new Book("One Day in December",bookAuthor6,"128","English","Broadway Books (October 16, 2018)",String.valueOf(AppHelper.Category.NOVEL),"416"));
        booksList.add(new Book("Cyber security Is Everybody's Business",bookAuthor7,"129","English","Scottschober.com Publishing (October 1, 2019)",String.valueOf(AppHelper.Category.TECH),"338"));
        booksList.add(new Book("Five Presidents: My Extraordinary Journey with Eisenhower, Kennedy, Johnson, Nixon, and Ford",bookAuthor8,"132","French","Gallery Books; Reprint edition (May 2, 2017)",String.valueOf(AppHelper.Category.HISTORICAL),"464"));
        booksList.add(new Book("Spectrum Math Workbook",bookAuthor9,"133","Russian","CARSON DELLOSA; Workbook edition (August 15, 2014)",String.valueOf(AppHelper.Category.MATHS),"160"));
        booksList.add(new Book("Compute-IT: Student's Book 1 - Computing for KS3",bookAuthor10,"136","English","Hodder Education (July 4, 2014)",String.valueOf(AppHelper.Category.COMPUTERSCIENCE),"160"));
        booksList.add(new Book("La reine des Neiges",bookAuthor11,"137","French","CreateSpace Independent Publishing Platform (November 3, 2017)",String.valueOf(AppHelper.Category.FAIRYTALE),"62"));



        membersList.add(new Member("001","Stephan", "Jackson","Vagen 23, Göteborg","75765","Stephan1234","stephan@yahoo.com"));
        membersList.add(new Member("002","Alan", "Tombson","Vagen 45, Göteborg","4535365","Alan1234","alan@yahoo.com"));
        membersList.add(new Member("003","Samuel","Jones","Vagen 1, Göteborg","1308080","Samuel1234","samuel@yahoo.com"));
        membersList.add(new Member("004","Brenda","Brown","Vagen 22, Göteborg","076564","Brenda1234","brenda@yahoo.com"));
        membersList.add(new Member("005","Ola","Magnusson","Vagen 41, Göteborg","253678","Ola1234","ola@yahoo.com"));
        membersList.add(new Member("006","Daniel","Bjorn","Vagen 90, Göteborg","1121245","Daniel1234","daniel@yahoo.com"));
        membersList.add(new Member("007","Tobe","Anderson","Vagen 231, Göteborg","99879","Tobe1234","tobe@yahoo.com"));







        registeredMembersList.add(new Member("001","Stephan1234"));
        registeredMembersList.add(new Member("002","Alan1234"));
        registeredMembersList.add(new Member("003","Samuel1234"));
        registeredMembersList.add(new Member("004","Brenda1234"));
        registeredMembersList.add(new Member("005","Ola1234"));
        registeredMembersList.add(new Member("006","Daniel1234"));
        registeredMembersList.add(new Member("007","Tobe1234"));



        adminsList.add(new Admin("001","Stephan001","ADMIN001"));
        adminsList.add(new Admin("007","Tobe007","ADMIN007"));



        loansHistory = archives();

    }


    public List<Book> getBooksList() {
        return booksList;
    }

    public List<Member> getMembersList() {
        return membersList;
    }

    public List<Loan> getLoansHistory() {
        return loansHistory;
    }

    public List<Admin> getAdminsList() {
        return adminsList;
    }

    public List<Member> getRegisteredMembersList() {
        return registeredMembersList;
    }



    //Deserialize method
    private List<Loan> archives() {
        List<Loan> records = new ArrayList<>();
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(AppHelper.HISTORY_FILENAME));
            records = (List<Loan>) in.readObject();
            in.close();
        }catch (Exception e){
            System.out.println();
        }

        return records;
    }

}