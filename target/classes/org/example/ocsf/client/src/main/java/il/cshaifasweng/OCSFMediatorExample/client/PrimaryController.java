package org.example.ocsf.client.src.main.java.il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    void sendWarning(ActionEvent event) {
    	try {
			il.cshaifasweng.OCSFMediatorExample.client.SimpleClient.getClient().sendToServer("#warning");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
