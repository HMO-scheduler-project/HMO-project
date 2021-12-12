package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application{
    @Override
    public void start (Stage stage){
        try{
            Parent root= FXMLLoader.load(getClass().getResource("login.FXML"));
            Scene login = new Scene(root);
            login.getStylesheets().add(getClass().getResource("/login_screen.css").toExternalForm());
            stage.setScene(login);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main (String[] args){
        launch(args);
    }
}