package com.company.services;

import com.company.interfaces.I_LoginService;
import com.company.interfaces.I_Services;
import com.company.models.Member;
import com.company.repositories.Data_Repository;
import java.util.List;

public class Member_Service implements I_Services<Member>,I_LoginService<Member> {

    private List<Member>members;
    private List<Member> registeredMembers;

    public Member_Service() {
        Data_Repository repo = new Data_Repository();
        members = repo.getMembersList();
        registeredMembers = repo.getRegisteredMembersList();
    }


    @Override
    public List<Member> getAll() {
        return members;
    }

    @Override
    public void add(Member obj) {
        for (Member m: members){
            if (m.getSsn().equals(obj.getSsn())){
                System.out.println("WARNING!!! There already an Existing member with the same Social security number!");
            }
        }

        members.add(obj);
    }

    @Override
    public void removeObj(String unique_identifier) {
        for (Member m: members){
            if (m.getSsn().equals(unique_identifier))
                members.remove(m);
            System.out.println("INFO!!! "+m.getFirstName()+" "+m.getLastName()+" has been removed!");
        }

    }

    @Override
    public Member find(String unique_identifier) {
        if (members != null)
            for (Member m: members){
                if (m.getSsn().equals(unique_identifier))
                    return m;
            }
        System.err.println(""+unique_identifier+" MEMBER NOT FOUND!!!");
        return null;
    }

    @Override
    public void editInfo(String propertyToEdit, String id, String value1) {
        for (Member m:members){
            if (m.getSsn().equals(id)){
                if (propertyToEdit.equalsIgnoreCase("Address")){
                    m.setHomeAddress(value1);
                }else if (propertyToEdit.equalsIgnoreCase("PhoneNumber")){
                    m.setPhoneNumber(value1);
                }
            }
        }
    }


    @Override
    public boolean userAuthentication(Member user) {
        for (Member m:members){
            if (m.getSsn().equals(user.getSsn()) && m.getPassword().equals(user.getPassword())){
                return true;
            }
        }
        return false;
    }

}
