package org.example.ocsf.client.src.main.java.il.cshaifasweng.OCSFMediatorExample.client;
import javafx.application.Platform;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.ocsf.entities.src.main.java.il.cshaifasweng.OCSFMediatorExample.entities.Message;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;


/** Controls the login screen */
public class loginController {
    private SimpleClient client;
    private static int sessionID = 0;

    @FXML
    private TextField userTF;
    @FXML
    private TextField passwordTF;
    @FXML
    private Button loginButton;
    @FXML
    private Label emptyUsernameWarning;
    @FXML
    private Label emptyPasswordWarning;
    @FXML
    private Label loginFailedWarning;

    public void initialize() {
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String sessionID = null;
                try {
                    sessionID = authorize();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Check authorization credentials.
     * If accepted, return a sessionID for the authorized session
     * otherwise, return null.
     */
    private String authorize() throws NoSuchAlgorithmException {
        try {
            hideWarnings();
            String username = userTF.getText();
            if (username.equals("")) {
                emptyUsernameWarning.setVisible(true);
                return null;
            }
            String password = passwordTF.getText();
            if (password.equals("")) {
                emptyPasswordWarning.setVisible(true);
                return null;
            }
            sendMessageToServer(username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return generateSessionID();
    }

    private String generateSessionID() {
        sessionID++;
        return "session: " + sessionID;
    }

    void hideWarnings() {
        emptyUsernameWarning.setVisible(false);
        emptyPasswordWarning.setVisible(false);
        loginFailedWarning.setVisible(false);
    }

    public void sendMessageToServer(String username, String password) {
        try {
            Message msg = new Message();
            msg.setUsername(username);
            msg.setPassword(password);
            msg.setAction("login");
            SimpleClient.getClient().sendToServer(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void onMessageEvent(Message msg) throws Exception {
        if (msg.getAction().equals("login")) {
            Platform.runLater(() -> {
                String type = msg.getType();
                if (type != null) {
                    if (type.equals("you are already logged in")) {
                        loginFailedWarning.setText("Login Failed- user is already logged in");
                        loginFailedWarning.setVisible(true);
                        return;
                    }
                    if (type.equals("This user does not exist")) {
                        loginFailedWarning.setText("Login Failed- incorrect username or password.Please try again or go to the main office");
                        loginFailedWarning.setVisible(true);
                        return;
                    }

                    App.setUsername(msg.getUsername());
                    App.setPassword(msg.getPassword());
                    App.setType(msg.getUserType());
                }
            });
        }
    }
}

