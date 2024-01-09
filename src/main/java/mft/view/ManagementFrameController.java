package mft.view;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import mft.Controller.ManagementController;
import mft.model.entity.Management;
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

            saveBtn.setOnAction((event) -> {
                try {
                    Management management =ManagementController.getController().save(
                                usernameTxt.getText(),
                                nameAndFamilyTxt.getText(),
                                passwordTxt.getText(),
                                userTypeCmb.getValue(),
                                UserType.valueOf(userTypeCmb.getSelectionModel().getSelectedItem()));
                    if (management != null) {
                            Stage stage = new Stage();
                            Scene scene = new Scene(
                                    FXMLLoader.load(getClass().getResource("AccountStudentFrame.fxml"))
                            );
                            stage.setScene(scene);
                            stage.setTitle("Account Information");
                            stage.show();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR,"Invalid Username/Password");

                              }} catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            }
        });




         /**   EventHandler romoveEventHandler = new EventHandler() {
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
                    Management management = null;
                    try {
                        management = ManagementController.getController().findByUsernameAndPassword(
                                usernameTxt.getText(),
                                passwordTxt.getText()
                        );
                        if (management != null) {
                            System.out.println("Register");
                        } else {
Alert alert = new Alert(Alert.AlertType.ERROR,"Invalid Username/Password");
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            };
          findByUsernameAndPasswordBtn.setOnAction(findByUsernameAndPasswordEventHandler);
          */
            findByUsernameAndPasswordBtn.setOnAction((event) -> {
                try {
                    Management management = ManagementController.getController().findByUsernameAndPassword(
                            usernameTxt.getText(),
                            passwordTxt.getText()
                    );
                    if (management != null) {
                        Stage stage = new Stage();
                        Scene scene = new Scene(
                                FXMLLoader.load(getClass().getResource("AccountStudentFrame.fxml"))
                        );
                        stage.setScene(scene);
                        stage.setTitle("Account Information");
                        stage.show();

                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR,"Invalid Username/Password");

                }} catch (Exception e) {
Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                }
            });
           } }