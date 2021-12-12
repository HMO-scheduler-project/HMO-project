package org.example;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Login {
        @FXML private Button logoutButton;
        @FXML private Label  sessionLabel;
        public void initialize() {}
        public void initSessionID(final LoginManager loginManager, String sessionID) {
            sessionLabel.setText(sessionID);
            logoutButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) {
                    loginManager.logout();
                }
            });
        }
    }
