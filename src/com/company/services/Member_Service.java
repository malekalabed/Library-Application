package com.company.services;

<<<<<<< HEAD
import com.company.interfaces.I_LoginService;
import com.company.interfaces.I_Services;
import com.company.models.Member;
import com.company.repositories.Data_Repository;
=======
import LIBRARY.PROJECT.interfaces.I_LoginService;
import LIBRARY.PROJECT.interfaces.I_Services;
import LIBRARY.PROJECT.models.Member;
import LIBRARY.PROJECT.repositories.Data_Repository;
>>>>>>> 51e314804bcb65a2b52d6e8dc983b0e68f876f1d

import java.util.List;

public class Member_Service implements I_Services<Member>,I_LoginService<Member> {

<<<<<<< HEAD
    private List<Member> members;
=======
    private List<Member>members;
>>>>>>> 51e314804bcb65a2b52d6e8dc983b0e68f876f1d

    public Member_Service() {
        Data_Repository repo = new Data_Repository();
        members = repo.getMembersList();
    }


    @Override
    public List<Member> getAll() {
        return members;
    }

    @Override
    public void add(Member obj) {

    }

    @Override
    public void removeObj(String unique_identifier) {

    }

    @Override
    public Member find(String unique_identifier) {
        return null;
    }

    @Override
    public void editInfo(String propertyToEdit, String id, String value1) {
    }


    @Override
    public boolean userAuthentication(Member user) {
        return false;
    }


    public void saveCredentials(Member obj) {}
}
