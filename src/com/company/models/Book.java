package com.company.models;

import java.io.Serializable;
import java.util.Arrays;

public class Book implements Serializable,Comparable< Book >{
    private String title;
    private String[]authorName;
    private String ISBN_number;
    private String language;
    private String publisher;
    private String category;
    private String numOfPages;
    private String status;

    public Book() {
    }

    public Book(String ISBN_number) {
        this.ISBN_number = ISBN_number;
    }

    public Book(String title, String[] authorName, String ISBN_number,
                String language, String publisher, String category , String numOfPages,String status) {
        this.title = title;
        this.authorName = authorName;
        this.ISBN_number = ISBN_number;
        this.language = language;
        this.publisher = publisher;
        this.category = category;
        this.numOfPages = numOfPages;
        this.status = status;
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

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BOOK\n  Title: " + title +"\n  Author(s): " + Arrays.toString(authorName)+"\n  ISBN number: "+ ISBN_number+"\n  Language: " + language +"\n  Publisher: "+ publisher +"\n  Category: "+ category +"\n  Number Of Pages: " + numOfPages+"\n  Status: "+status;
    }


    public String ISBNToString(){
        return "ISBN: "+ISBN_number;
    }

    @Override
    public int compareTo(Book b) {
        return getTitle().compareTo(b.getTitle());
    }
}
