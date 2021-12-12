package org.example.Entities;


import java.security.NoSuchAlgorithmException;

public class Manager extends Employee {

    public Manager(String username, String password,int card,String Email,String main_clinic) throws NoSuchAlgorithmException {
        super(username, password,card,Email,main_clinic);
    }

    public String getFullName(){
        return first_name+last_name;
    }

    @Override
    public String toString() {
        return "manager[ "+super.toString() + " ]";
    }

}