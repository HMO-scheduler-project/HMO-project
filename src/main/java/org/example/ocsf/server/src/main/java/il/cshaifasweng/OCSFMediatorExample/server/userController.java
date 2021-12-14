package org.example.ocsf.server.src.main.java.il.cshaifasweng.OCSFMediatorExample.server;


import org.example.Entities.Employee;
import org.example.Entities.Manager;
import org.example.Entities.User;
import org.example.ocsf.entities.src.main.java.il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class userController {
    private static List<User> getAllUsersFromDB() {
        CriteriaBuilder builder = App.session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        query.from(User.class);
        return App.session.createQuery(query).getResultList();
    }

    public static void getUser(Message msg) throws NoSuchAlgorithmException {
        List<User> users = getAllUsersFromDB();
        int flag = 0;
        for (User user : users) {
            if (user.getUsername().equals(msg.getUsername()) && user.checkPassword(msg.getPassword()) && !user.isLoggedIn()) {
                if (user instanceof Manager) {
                    msg.setUserType("Manager");
                    System.out.println(msg.getUserType());
                    user.setLoggedIn(true);
                    msg.setUser(user);
                    flag = 1;
                } else if (user instanceof Employee) {
                    msg.setUserType("Employee");
                    System.out.println(msg.getUserType());
                    user.setLoggedIn(true);
                    msg.setUser(user);
                    flag = 1;
                } else msg.setUserType("null");
            } else if (user.getUsername().equals(msg.getUsername()) && user.checkPassword(msg.getPassword()) && user.isLoggedIn()) {
                msg.setType("you are already logged in");
                flag = 1;
            }

        }
        if (flag == 0) {
            msg.setType("This user does not exist");
        }
    }

    public static void logOut(Message msg) throws NoSuchAlgorithmException {
        List<User> users = getAllUsersFromDB();
        System.out.println("about to log out");
        for (User user : users) {
            if (user.getUsername().equals(msg.getUsername()) && user.checkPassword(msg.getPassword())) {
                if (user instanceof Manager) {
                    user.setLoggedIn(false);

                } else if (user instanceof Employee) {
                    user.setLoggedIn(false);

                }
            } else if (user.getUsername().equals(msg.getUsername()) && user.checkPassword(msg.getPassword()) && user.isLoggedIn()) {
                msg.setUserType("you are already logged out");
            }

        }

    }
}




