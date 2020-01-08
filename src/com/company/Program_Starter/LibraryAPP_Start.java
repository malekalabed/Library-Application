package com.company.Program_Starter;

import com.company.controller.Library_Admin_Controller;


public class LibraryAPP_Start {

    public static  void  main(String []args) throws InterruptedException {

        Library_Admin_Controller admin_controller = new Library_Admin_Controller();

        admin_controller.mainMenu();

    }
}
