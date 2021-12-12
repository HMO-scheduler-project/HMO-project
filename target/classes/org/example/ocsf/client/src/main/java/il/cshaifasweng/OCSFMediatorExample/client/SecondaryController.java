package org.example.ocsf.client.src.main.java.il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}