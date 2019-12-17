package com.company.services;

import LIBRARY.PROJECT.interfaces.I_LoginService;
import LIBRARY.PROJECT.interfaces.I_Services;
import LIBRARY.PROJECT.models.Member;
import LIBRARY.PROJECT.repositories.Data_Repository;

import java.util.List;

public class Member_Service implements I_Services<Member>,I_LoginService<Member> {

    private List<Member>members;

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
