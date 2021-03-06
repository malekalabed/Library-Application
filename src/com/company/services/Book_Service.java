package com.company.services;

import com.company.AppHelper;
import com.company.interfaces.I_Services;
import com.company.models.Book;
import com.company.repositories.Data_Repository;
import java.util.Collections;
import java.util.List;

public class Book_Service implements I_Services<Book> {
    private List<Book>books;

    public Book_Service() {
        Data_Repository repo = new Data_Repository();
        books = repo.getBooksList();
    }


    @Override
    public List<Book> getAll() {
        return books;
    }

    @Override
    public void add(Book obj) {
        if ( !valid(obj)||!String.valueOf(obj.getNumOfPages()).matches(".*[0-9].*")
                || !AppHelper.isNumeric(obj.getISBN_number())
                || !AppHelper.containsOnlyLetters(obj.getLanguage())
                || !AppHelper.containsOnlyLetters(obj.getCategory())){
            System.out.println("Seems like the given ISBN is already linked to another \nbook with different details than the ones you gave.\n" +
                    "Check the details you have provided and try again please.\n");
            System.out.println("SORRY! THE BOOK CANNOT BE SAVED!");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("INFO!!! Book successfully added!\n\n");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void removeObj(String unique_identifier) {
        getAll().removeIf((Book b) -> b.getISBN_number().equals(unique_identifier));
        System.out.println("\nTHE BOOK WITH ISBN [ "+unique_identifier+" ] WAS SUCCESSFULLY REMOVED!!!\n");
    }

    @Override
    public Book find(String unique_identifier) {
        if (getAll() != null)
            for (Book book: getAll()){
                if (book.getISBN_number().equals(unique_identifier))
                    return book;
            }
        System.out.println(""+unique_identifier+" BOOK NOT FOUND!!!");
        return null;
    }

    @Override
    public void editInfo(String propertyToEdit, String id, String value1) {
        for (Book b: getAll()){
            if (b.getISBN_number().equals(id)){
                if (propertyToEdit.equals("Language")){
                    b.setLanguage(value1);
                }else if (propertyToEdit.equals("Category")){
                    b.setCategory(value1);
                }
                //And so on. But i think those two are what could be edited on a book.
            }
        }

    }


    public void getBooksOrderedByCategory(String category){
        for (Book b: getAll()){
            if (b.getCategory().equalsIgnoreCase(category)){
                System.out.println(b);
            }
        }
    }

    public void availableBooks(){
        for (Book b: getAll()){
            if (b.getStatus().equals("AVAILABLE")){
                System.out.println("[BOOK]- TITLE: "+b.getTitle()+"         ISBN: "+b.getISBN_number());
            }
        }

    }

    public void getBooksCategories(){
        System.out.println("\n------- Available books and their categories ------");
        for (Book b: getAll()){
            System.out.println("[BOOK]- TITLE: "+b.getTitle()+".        CATEGORY: "+b.getCategory()+"\n");
        }
    }


    public void getBooksOrderedByLanguage(String language){
        for (Book b: getAll()){
            if (b.getLanguage().equalsIgnoreCase(language)){
                System.out.println(b);
            }
        }
    }

    public void getBooksLanguages(){
        System.out.println("\n------- AVAILABLE BOOKS ORDERED BY LANGUAGES ------");
        for (Book b: getAll()){
            System.out.println("[BOOK]- TITLE: "+b.getTitle()+"        LANGUAGE: "+b.getLanguage()+"\n");
        }
    }


    public void getOrderedBooksByTitles(){
        System.out.println("\n --------- AVAILABLE BOOKS ORDERED BY TITLES  -------");
        Collections.sort(getAll());
        for (Book b: getAll()){
            System.out.println("[BOOK]- TITLE: "+b.getTitle()+"         ISBN: "+b.getISBN_number());
        }
    }

    private boolean valid(Book obj){
        for (int i=0;i<getAll().size();i++){
            if (getAll().get(i).getISBN_number().equalsIgnoreCase(obj.getISBN_number())
                    && !getAll().get(i).getTitle().equalsIgnoreCase(obj.getTitle())
                    || !getAll().get(i).getCategory().equalsIgnoreCase(obj.getCategory())
                    || !getAll().get(i).getAuthorName().equals(obj.getAuthorName())){
                return false;
            }
        }
        return true;
    }



}