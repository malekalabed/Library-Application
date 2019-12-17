package com.company.services;

import LIBRARY.PROJECT.interfaces.I_LoginService;
import LIBRARY.PROJECT.models.Admin;
import LIBRARY.PROJECT.repositories.Data_Repository;

import java.util.List;

public class Admin_Service implements I_LoginService<Admin> {

    private List<Admin>admins;
    private Data_Repository data_repository = new Data_Repository();


    public Admin_Service() {

    }


    public List<Admin> getAdmins() {
        return admins;
    }

    @Override
    public boolean userAuthentication(Admin user) {
        return false;
    }
}
