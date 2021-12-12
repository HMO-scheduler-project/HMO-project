package org.example;

import java.io.IOException;
import java.util.logging.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import org.example.controllers.loginController;

public class LoginManager {
    private Scene scene;

    public LoginManager(Scene scene) {
        this.scene = scene;
    }

    public void authenticated(String sessionID) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            scene.setRoot((Parent) loader.load());
            Login login = loader.<Login>getController();
            login.initSessionID(this, sessionID);
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void logout() {
        showLoginScreen();
    }

    public void showLoginScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            scene.setRoot((Parent) loader.load());
            loginController controller = loader.<loginController>getController();
            controller.initManager(this);
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
