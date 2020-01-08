package com.company.models;

import java.io.Serializable;

public class Admin extends Member implements Serializable {

    private String adminCode;


    public Admin() {
    }

    public Admin(String ssn, String password, String adminCode) {
        super(ssn, password);
        this.adminCode = adminCode;
    }

    public String getAdminCode() {
        return adminCode;
    }

    public void setAdminCode(String adminCode) {
        this.adminCode = adminCode;
    }


    @Override
    public String toString() {
        return super.toString()+"Admin{" + "adminCode='" + adminCode + '\'' + '}';
    }
}
