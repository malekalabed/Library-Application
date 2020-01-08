package com.company.repositories;

import com.company.AppHelper;
import com.company.models.*;
import java.util.ArrayList;
import java.util.List;


public class Data_Repository {

    private List<Book> booksList;
    private List<Member> membersList;


    public Data_Repository() {

        booksList = new ArrayList<>();
        membersList = new ArrayList<>();



        String [][]authors = {
                {"Barbara O'Neal"},{"Stephanie Dray","Laura Kamoie","Cassandra Campbell"},{"Golding William"},
                {"Ian Stewart","Joseph Green"},{"Stevenson Robert Louis"},{"Masulli Patrick"},{"Charles Dickens"},
                {"Miguel de Cervantes Saavedra"},{"William Shakespeare"},{"Samuel Beckett"},{"Aeschylus W. Stanford","Robert Fagles"},
                {"J Ellen Gainor"},{"Stanton B Garner"},{"Martin Puchner"},{"Sophocles"},{"Eduard Fosch Villaronga"},
                {"Annamaria Castrignano","Buttafuoco G.","Mouazen A"},{"Rob Ford","Julius Wiedemann "},{"Richard Powers"},
                {"Anthony Doerr "},{"Josie Silver"},{"Scott N Schober","Craig W Schober"},{"Hampton Sides"},{"Brian Kilmeade","Don Yaeger"},
                {"Clint Hill","Lisa McCubbin"},{"Spectrum "},{"Julie Sway"},{"Mr. Kevin P Hare","Mr. Pindar Van Arman"},
                {"Mark Dorling","George Rouse"},{"Hans Christian Andersen","Svetlana Bagdasaryan "},{"Anna Milbourne"}
        };


        booksList.add(new Book("When We Believed in Mermaids",authors[0],"110","Swedish","Lake Union Publishing (July 16, 2019)",String.valueOf(AppHelper.Category.NOVEL),"348","AVAILABLE"));
        booksList.add(new Book("Don Quixote ",authors[7],"117","German","Linkgua Ediciones 1/1/2014 (2014)",String.valueOf(AppHelper.Category.CLASSIC),"265","AVAILABLE"));
        booksList.add(new Book("The Norton Anthology of Drama",authors[11],"121","English","W. W. Norton & Company",String.valueOf(AppHelper.Category.DRAMA),"123","AVAILABLE"));
        booksList.add(new Book("Antigone",authors[12],"122","Swedish","Facsimile Publisher",String.valueOf(AppHelper.Category.DRAMA),"209","AVAILABLE"));
        booksList.add(new Book("Agricultural Internet of Things and Decision Support for Smart Farming",authors[14],"124","French","Academic Press; 1 edition (January 15, 2020)",String.valueOf(AppHelper.Category.TECH),"380","AVAILABLE"));
        booksList.add(new Book("One Day in December",authors[18],"128","English","Broadway Books (October 16, 2018)",String.valueOf(AppHelper.Category.NOVEL),"416","AVAILABLE"));
        booksList.add(new Book("Cybersecurity Is Everybody's Business",authors[19],"129","English","Scottschober.com Publishing (October 1, 2019)",String.valueOf(AppHelper.Category.TECH),"338","AVAILABLE"));
        booksList.add(new Book("Five Presidents: My Extraordinary Journey with Eisenhower, Kennedy, Johnson, Nixon, and Ford",authors[22],"132","French","Gallery Books; Reprint edition (May 2, 2017)",String.valueOf(AppHelper.Category.HISTORICAL),"464","AVAILABLE"));
        booksList.add(new Book("Spectrum Math Workbook",authors[23],"133","Russian","CARSON DELLOSA; Workbook edition (August 15, 2014)",String.valueOf(AppHelper.Category.MATHS),"160","AVAILABLE"));
        booksList.add(new Book("Compute-IT: Student's Book 1 - Computing for KS3",authors[26],"136","English","Hodder Education (July 4, 2014)",String.valueOf(AppHelper.Category.COMPUTERSCIENCE),"160","AVAILABLE"));
        booksList.add(new Book("La reine des neiges",authors[27],"137","French","CreateSpace Independent Publishing Platform (November 3, 2017)",String.valueOf(AppHelper.Category.FAIRYTALE),"62","AVAILABLE"));






        membersList.add(new Member("001","Stephan", "Ngaha","Vagen 23, Göteborg","75765","Stephan1234",new Author("Ngaha","001")));
        membersList.add(new Member("002","Alan", "Tombson","Vagen 45, Göteborg","4535365","Alan1234",new Author("Tombson","002")));
        membersList.add(new Member("003","Samuel","Jones","Vagen 1, Göteborg","1308080","Samuel1234",new Author("Jones","003")));
        membersList.add(new Member("004","Brenda","Brown","Vagen 22, Göteborg","076564","Brenda1234",new Author("Brown","004")));
        membersList.add(new Member("005","Ola","Magnusson","Vagen 41, Göteborg","253678","Ola1234",new Author("Magnusson","005")));
        membersList.add(new Member("006","Daniel","Bjorn","Vagen 90, Göteborg","1121245","Daniel1234",new Author("Bjorn","006")));
        membersList.add(new Member("007","Tobe","Anderson","Vagen 231, Göteborg","99879","Tobe1234",new Author("Tobe","007")));






    }


    public List<Book> getBooksList() {
        return booksList;
    }

    public List<Member> getMembersList() {
        return membersList;
    }


}
