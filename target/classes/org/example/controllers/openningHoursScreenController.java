package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;


public class openningHoursScreenController {
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
    private TextField CloseHourTF;
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
    /*
        @FXML
        private MenuItem scheduledAppBtn;
         @FXML
        void pressChangeAppBtn(ActionEvent event) {}

        @FXML
        void pressContactInfoBtn(ActionEvent event) {}

        @FXML
        void pressMainPageBtn(ActionEvent event) {}

        @FXML
        void pressNewAppBtn(ActionEvent event) {}
    */
    @FXML
    void pressOppenningHoursBtn(ActionEvent event) {
        OpenningHoursBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {

            }
        });
    }

    @FXML
    void pressChangeHoursBtn(ActionEvent event) {

        if(visible){
            ChangeHoursBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) {
                    boolean visibleTF = true;

                }
            });
        }
    }

 /*   @FXML
    void pressScheduledAppBtn(ActionEvent event) {
    }
*/
    @FXML
    void initialize() {
        boolean visible =
    }

}

