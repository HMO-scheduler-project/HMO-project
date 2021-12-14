package org.example.ocsf.client.src.main.java.il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.example.Entities.Clinic;
import org.example.ocsf.entities.src.main.java.il.cshaifasweng.OCSFMediatorExample.entities.Message;

import java.io.IOException;
import java.sql.Time;
import java.util.List;


public class openningHoursScreenController {
    Message clientMsg = new Message();
    boolean manager;
    /*
    @FXML
    private MenuItem ChangeAppBtn;
*/
    @FXML
    private TableColumn<Time, Time> OpenningHourColumn;
    @FXML
    private TableColumn<Time, Time> ClosingHourColumn;
    @FXML
    private Button ChangeHoursBtn;

    @FXML
    private Menu ClinicsBtn;

    @FXML
    private ChoiceBox<String> ClinicsList;

    @FXML
    private TextField closeHourTF;
    /*
        @FXML
        private Menu MainPageBtn;
    */
    @FXML
    private MenuItem OpenningHoursBtn;

    @FXML
    private Button submitChangeHoursBtn;
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
        ChangeHoursBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                openHourTF.setVisible(true);
                closeHourTF.setVisible(true);
            }
        });
    }

    @FXML
    void pressSubmitChangeHoursBtn(ActionEvent event){
        submitChangeHoursBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                msg.setAction("change hours");
                try{
                    if(!openHourTF.getText().equals("")) {
                        msg.setOpenningHour(openHourTF.getText());
                    }
                    if(!closeHourTF.getText().equals("")) {
                        msg.setClosingHour(closeHourTF.getText());
                    }
                    OpenningHourColumn.setText(String.valueOf(msg.getClinic().getOpenningHour()));
                    ClosingHourColumn.setText(String.valueOf(msg.getClosingHour().getClosingHour()));
                    SimpleClient.getClient().sendToServer(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
       void pressScheduledAppBtn(ActionEvent event) {
       }

    @FXML
    void initialize() {
        manager = App.getType().equals("Manager");
    }
    @FXML
    void ShowClinics(MouseEvent event) {
            ClinicsList.onMouseClickedProperty(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    // the messege need to stay the same so ill be able to change it with input- meaby
                    msg.setAction("GetAllClinics");
                    try {
                        //not sure if this is right --I want to send the msg to server-yoni
                        SimpleClient.getClient().sendToServer(msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    List<Clinic> clinics = Message.getClinicList();
                    for (Clinic clinic : clinics) {
                        ClinicsList.getItems().add(clinic.getName());
                    }
                    String chosenClinic = ClinicsList.getSelectionModel().getSelectedItem();
                    if(chosenClinic!=null){
                        clientMsg.setClinicName(chosenClinic);
                        clientMsgsg.setAction("GetClinicFromName");
                        try {
                            //not sure if this is right --I want to send the msg to server-yoni
                            SimpleClient.getClient().sendToServer(msg);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        OpenningHourColumn.setText(String.valueOf(msg.getClinic().getOpenningHour()));
                        ClosingHourColumn.setText(String.valueOf(msg.getClosingHour().getClosingHour()));
                        if(manager) {
                            ChangeHoursBtn.setVisible(true);
                        }
                        clientMsg.setOpenningHour(null);
                        clientMsg.setClosingHour(null);
                    }
                }
            });
    }

}

