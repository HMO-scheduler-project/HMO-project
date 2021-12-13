package org.example.ocsf.client.src.main.java.il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import org.example.ocsf.client.src.main.java.il.cshaifasweng.OCSFMediatorExample.client.App;


public class openningHoursScreenController {
    boolean visible;
    /*
    @FXML
    private MenuItem ChangeAppBtn;
*/
    @FXML
    private Button ChangeHoursBtn;

    @FXML
    private Menu ClinicsBtn;

    @FXML
    private ChoiceBox<?> ClinicsList;

    @FXML
    private TextField closeHourTF;
    /*
        @FXML
        private Menu MainPageBtn;
    */
    @FXML
    private MenuItem OpenningHoursBtn;
    /*
        @FXML
        private MenuItem contactInfoBtn;

        @FXML
        private MenuItem newAppBtn;
    */
    @FXML
    private TextField openHourTF;
    /* @FXML
        private MenuItem scheduledAppBtn;
     */
         @FXML
        void pressChangeAppBtn(ActionEvent event) {}

        @FXML
        void pressContactInfoBtn(ActionEvent event) {}

        @FXML
        void pressMainPageBtn(ActionEvent event) {}

        @FXML
        void pressNewAppBtn(ActionEvent event) {}
    @FXML
    void pressOpenningHoursBtn(ActionEvent event) {
        OpenningHoursBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {

            }
        });
    }

    @FXML
    void pressChangeHoursBtn(ActionEvent event) {
        openHourTF.setVisible(true);
        closeHourTF.setVisible(true);
        ChangeHoursBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {

            }
        });
    }

 /*   @FXML
    void pressScheduledAppBtn(ActionEvent event) {
    }
*/
    @FXML
    void initialize() {
        visible = App.getType().equals("Manager");
        if(visible){
            ChangeHoursBtn.setVisible(true);
        }
    }

}

