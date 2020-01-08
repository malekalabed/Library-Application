package com.company.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Book implements Serializable,Comparable< Book >{
    private String title;
    private String[]authorName;
    private String ISBN_number;
    private String language;
    private String publisher;
    private String category;
    private String numOfPages;
    private String status = "AVAILABLE";
    private int numberOfCopy = 1;
    private ArrayList<Author> authors;
    private String[] bookAuthors;

    public Book() {
    }


    public Book(String title, ArrayList<Author> authors, String ISBN_number,
                String language, String publisher, String category , String numOfPages) {
        this.title = title;
        this.authors = authors;
        this.ISBN_number = ISBN_number;
        this.language = language;
        this.publisher = publisher;
        this.category = category;
        this.numOfPages = numOfPages;
        this.numberOfCopy = 1;
        this.bookAuthors = new String[authors.size()];
        int i = 0;
        for (Author author: authors){
            bookAuthors[i] = author.getAuthorName();
            i++;
        }
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String[] authorName) {
        this.authorName = authorName;
    }

    public String getISBN_number() {
        return ISBN_number;
    }

    public void setISBN_number(String ISBN_number) {
        this.ISBN_number = ISBN_number;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getNumOfPages() {
        return numOfPages;
    }

    public void setNumOfPages(String numOfPages) {
        this.numOfPages = numOfPages;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
    }

    public String[] getBookAuthors() {
        return bookAuthors;
    }

    public void setBookAuthors(ArrayList<Author> authors) {
        String[]bookAuthors = new String[authors.size()];
        int i = 0;
        for (Author author: authors){
            bookAuthors[i] = author.getAuthorName();
            i++;
        }
        this.bookAuthors = bookAuthors;
    }

    public void setStatus(int numberOfCopy) {
        String status;
        if (numberOfCopy < 1){
            status = "NOT AVAILABLE";
        }else {
            status = "AVAILABLE";
        }
        this.status = status;
    }

    public int getNumberOfCopy() {
        return numberOfCopy;
    }

    public void setNumberOfCopy(String command, int numberOfCopyToAddOrRemove) {
        if (command.equals("1")){
            this.numberOfCopy = numberOfCopy + numberOfCopyToAddOrRemove;
        }else if (command.equals("2")){
            this.numberOfCopy = numberOfCopy - numberOfCopyToAddOrRemove;
        }
    }


    @Override
    public String toString() {
        return "BOOK\n  Title: " + title +"\n  Author(s): " + Arrays.toString(bookAuthors)+"\n  ISBN number: "+ ISBN_number+"\n  Language: " + language +"\n  Publisher: "+ publisher +"\n  Category: "+ category +"\n  Number Of Pages: " + numOfPages+"\n  Status: "+status+"\n  Number Of Copy in Stock: "+numberOfCopy;
    }

    @Override
    public int compareTo(Book b) {
        return getTitle().compareTo(b.getTitle());
    }
}