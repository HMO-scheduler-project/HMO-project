package org.example.Entities;


import java.security.NoSuchAlgorithmException;

public class Employee extends User {
    protected String Email;
    protected String main_clinic;

    public Employee(String username, String password,int card,String Email,String main_clinic) throws NoSuchAlgorithmException {
        super(username, password,card);
        this.Email = Email;
        this.main_clinic = main_clinic;
    }

    @Override
    public String toString() {
        return "employee[ "+super.toString() + "Email: "+Email+"main clinic: "+main_clinic+" ]";
    }

}