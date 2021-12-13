package org.example.ocsf.entities.src.main.java.il.cshaifasweng.OCSFMediatorExample.entities;
import org.example.Entities.User;
import java.io.Serializable;

public class Message implements Serializable {
    /* ---------- Message Necessary Info ---------- */
    private int id;
    private String action;
    private String type;
    private String error;
    /* ---------- Handling users ---------- */
    private User user;
    private String username;
    private String password;
    private String userType;
    /*----------Handling clinics----*/
    private Clinic clinic;
    private Time openning_hour;
    private Time closing_hour;


    public Message() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public void setOpenningHour (Time openning_hour){
        this.openning_hour = openning_hour;
    }

    public Time getOpenningHour(){
        return openning_hour;
    }

    public void setClosingHour (Time closing_hour){
        this.closing_hour = closing_hour;
    }

    public Time getClosingHour(){
        return closing_hour;
    }
}
