package mft.view;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import mft.Controller.ManagementController;
import mft.model.entity.UserType;
import java.net.URL;
import java.util.ResourceBundle;

    public class ManagementFrameController implements Initializable {
        @FXML
        private Button removeBtn, saveBtn, findByUsernameAndPasswordBtn;

        @FXML
        private TextField  usernameTxt, nameAndFamilyTxt, passwordTxt;

        @FXML
        private ComboBox<String> userTypeCmb;

        @Override
        public void initialize(URL location, ResourceBundle resources) {
            for (UserType value : UserType.values()) {
                userTypeCmb.getItems().add(value.name());
            }
            userTypeCmb.getSelectionModel().select(0);

            EventHandler saveEventHandler = new EventHandler() {
                @Override
                public void handle(Event event) {

                    try {
                        ManagementController.getController().save(
                                usernameTxt.getText(),
                                nameAndFamilyTxt.getText(),
                                passwordTxt.getText(),
                                userTypeCmb.getValue(),
                                UserType.valueOf(userTypeCmb.getSelectionModel().getSelectedItem()));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Login ");
                }
            };

            saveBtn.setOnAction(saveEventHandler);


            EventHandler romoveEventHandler = new EventHandler() {
                @Override
                public void handle(Event event) {

                    try {
                        ManagementController.getController().remove(
usernameTxt.getText(),
passwordTxt.getText()
                        );
} catch (Exception e) {
throw new RuntimeException(e);
                    }
Alert alert = new Alert(Alert.AlertType.INFORMATION, "Log out");
                }
            };
removeBtn.setOnAction(romoveEventHandler);
            EventHandler findByUsernameAndPasswordEventHandler = new EventHandler() {
                @Override
                public void handle(Event event) {

                    try {
                        ManagementController.getController().findByUsernameAndPassword(
                                usernameTxt.getText(),
                                passwordTxt.getText()
                        );
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Register");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
            }
        };
            findByUsernameAndPasswordBtn.setOnAction(findByUsernameAndPasswordEventHandler);
        }}