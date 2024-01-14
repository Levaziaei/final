package mft.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import mft.Controller.ManagementController;
import mft.model.entity.Management;

import java.net.URL;
import java.util.ResourceBundle;

    public class ManagementFrameController implements Initializable {
        @FXML
        private Button removeBtn, saveBtn, findByUsernameAndPasswordBtn,registerForAdminBtn;

        @FXML
        private TextField  usernameTxt, nameAndFamilyTxt, passwordTxt;
        @Override
        public void initialize(URL location, ResourceBundle resources) {
        saveBtn.setOnAction((event) -> {
                try {
                    Management management = ManagementController.getController().save(
                            usernameTxt.getText(),
                            nameAndFamilyTxt.getText(),
                            passwordTxt.getText()
                    );

                    Stage stage = new Stage();
                    Scene scene = new Scene(
                            FXMLLoader.load(getClass().getClassLoader().getResource("BookFrame.fxml"))
                    );
                    stage.setScene(scene);
                    stage.setTitle("Librarian");
                    stage.show();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Your welcome");
                    alert.show();
                    saveBtn.getParent().getScene().getWindow().hide();
                    } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Username/Password");
                    alert.show();
                } });
            removeBtn.setOnAction((event) -> {
                    try {
                        ManagementController.getController().remove(
                                usernameTxt.getText(),
                                passwordTxt.getText()
                        );
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Log out");
                        alert.show();
                        removeBtn.getParent().getScene().getWindow().hide();
                    } catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Username/Password");
                        alert.show();
                    }
                });

findByUsernameAndPasswordBtn.setOnAction((event) -> {
try {
Management management = ManagementController.getController().findByUsernameAndPassword(
usernameTxt.getText(),
passwordTxt.getText()
);
if (management != null) {
    Stage stage = new Stage();
    Scene scene = new Scene(
    FXMLLoader.load(getClass().getClassLoader().getResource("BookFrame.fxml"))
 );
                        stage.setScene(scene);
                        stage.setTitle("Librarian");
                        stage.show();
    Alert alert = new Alert(Alert.AlertType.INFORMATION, "your welcome");

    findByUsernameAndPasswordBtn.getParent().getScene().getWindow().hide();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Username/Password");
                         alert.show();
                    }

                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                    alert.show();
                }});

       registerForAdminBtn.setOnAction((event) -> {
    try {
// TODO: Password and username admin == 'admin'
Management management = ManagementController.getController().registerForAdmin(
                usernameTxt.getText(),
                passwordTxt.getText());
           if (management != null) {
            Stage stage = new Stage();
            Scene scene = new Scene(
FXMLLoader.load(getClass().getClassLoader().getResource("accountStaffFrame.fxml"))
            );
            stage.setScene(scene);
            stage.setTitle("Account-Staff Information");
            stage.show();
               registerForAdminBtn.getParent().getScene().getWindow().hide();
        } }catch (Exception e) {
        throw new RuntimeException(e);
    }
 });

        }}
