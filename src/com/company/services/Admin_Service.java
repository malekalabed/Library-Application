package com.company.services;

import com.company.interfaces.I_LoginService;
import com.company.models.Admin;
import com.company.repositories.Data_Repository;

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
