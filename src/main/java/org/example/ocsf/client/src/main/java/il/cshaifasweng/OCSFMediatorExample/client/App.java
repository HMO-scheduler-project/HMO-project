package org.example.ocsf.client.src.main.java.il.cshaifasweng.OCSFMediatorExample.client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;

import org.example.ocsf.entities.src.main.java.il.cshaifasweng.OCSFMediatorExample.entities.Message;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private SimpleClient client;
    private static String username;
    private static String password;
    private static String type;
    private static Object currentController;

    @Override
    public void start(Stage stage) throws IOException {
    	EventBus.getDefault().register(this);
    	client = SimpleClient.getClient();
    	client.openConnection();
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    @Override
	public void stop() throws Exception {
    	EventBus.getDefault().unregister(this);
		super.stop();
	}
    
    @Subscribe
    public void onWarningEvent(WarningEvent event) {
    	Platform.runLater(() -> {
    		Alert alert = new Alert(AlertType.WARNING,
        			String.format("Message: %s\nTimestamp: %s\n",
        					event.getWarning().getMessage(),
        					event.getWarning().getTime().toString())
        	);
        	alert.show();
    	});
    }

    @Subscribe
    public void SetClient(Message msg) throws IOException {
        if(msg.getAction().equals("set client")) {
            client = SimpleClient.getClient();
        }
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        App.password = password;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        App.username = username;
    }

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        App.type = type;
    }

    public static Object getCurrentController() {
        return currentController;
    }

	public static void main(String[] args) {
        launch();
    }

}