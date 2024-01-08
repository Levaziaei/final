package mft.view;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import mft.Controller.AccountStudentController;
import mft.Controller.BookController;
import mft.Controller.ManagementController;
import mft.model.entity.UserType;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountStudentFrameController implements Initializable {
    @FXML
    private Button  saveBtn, editBtn;

    @FXML
    private TextField idTxt, nameAndFamilyTxt, yourSuggestionTxt;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EventHandler saveEventHandler = new EventHandler() {
            @Override
            public void handle(Event event) {

                try {
                    BookController.getController().save(
                            Integer.valueOf(idTxt.getText()),
                            nameAndFamilyTxt.getText(),
                            yourSuggestionTxt.getText());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Login ");
            }
        };

        saveBtn.setOnAction(saveEventHandler);


    }
}
