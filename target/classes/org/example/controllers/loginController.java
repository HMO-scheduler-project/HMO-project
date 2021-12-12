package org.example.controllers;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.Entities.User;
import org.example.LoginManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/** Controls the login screen */
public class loginController {
    @FXML private TextField userTF;
    @FXML private TextField passwordTF;
    @FXML private Button loginButton;

    private static int sessionID = 0;
    public loginController() {}
    public void initialize() {}
    public void initManager(final LoginManager loginManager) {
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                String sessionID = null;
                try {
                    sessionID = authorize();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                if (sessionID != null) {
                    loginManager.authenticated(sessionID);
                }
            }
        });
    }
    /** Check authorization credentials.
     * If accepted, return a sessionID for the authorized session
     * otherwise, return null.*/
    private String authorize() throws NoSuchAlgorithmException {
        User user = findUserInDB(userTF.getText());
        if (user == null){
            System.out.println("Error! can't find username. Please try again or contact main office.");
        }
        return user.checkPassword(passwordTF.getText())? generateSessionID(): null;
    }

    private String generateSessionID() {
        sessionID++;
        return "session: " + sessionID;
    }

    private List<User> getAllUsers() {
        Session session = SessionFactory.getCurrentSession();
        session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        query.from(User.class);
        return session.createQuery(query).getResultList();
    }
    private User findUserInDB(String username){
        List<User> users = getAllUsers();
        for(User user:users){
            if(user.getUsername().equals(username)){
                return user;
            }
        }

        return null;
    }
}

