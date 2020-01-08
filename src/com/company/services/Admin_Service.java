package com.company.services;

import com.company.interfaces.I_LoginService;
import com.company.models.Admin;
import com.company.repositories.Data_Repository;
import java.util.List;

public class Admin_Service implements I_LoginService<Admin> {

    private List<Admin>admins;

    public Admin_Service() {
        Data_Repository data_repository = new Data_Repository();
        admins = data_repository.getAdminsList();
    }

    @Override
    public boolean userAuthentication(Admin user) {
        for (Admin m: admins){
            if (m.getSsn().equals(user.getSsn()) && m.getPassword().equals(user.getPassword()) && m.getAdminCode().equalsIgnoreCase(user.getAdminCode())){
                return true;
            }
        }
        return false;
    }
}